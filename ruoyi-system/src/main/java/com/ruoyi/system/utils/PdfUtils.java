package com.ruoyi.system.utils;

import cn.hutool.core.date.DateUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.ProjectInfo;
import com.ruoyi.system.domain.vo.JudgeInfoVo;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

public class PdfUtils {

    public static void createPdf(HttpServletResponse response, ProjectInfo projectInfo, List<JudgeInfoVo> judgeInfoVos) {
        try {
            // 1. 创建PDF文档
            Document document = new Document(PageSize.A4);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" +
                    new String((projectInfo.getProjectName() + ".pdf").getBytes(), "ISO8859-1"));

            OutputStream out = response.getOutputStream();
            PdfWriter.getInstance(document, out);
            document.open();

            // 2. 设置字体（支持中文）
            BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font titleFont = new Font(baseFont, 16, Font.BOLD);
            Font textFont = new Font(baseFont, 12, Font.NORMAL);

            // 3. 添加标题
            Paragraph title = new Paragraph(projectInfo.getProjectName() + " 确认书", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10f);
            document.add(title);

            // 4. 创建 **两列表格**
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(0f);
            table.setSpacingAfter(0f);
            table.setWidths(new float[]{2f, 3f}); // 第一列 2 份宽度，第二列 3 份宽度

            // 5. **添加 "项目名称" 和其值**
            addCell(table, "项目名称", textFont, Element.ALIGN_CENTER);
            addCell(table, projectInfo.getProjectName(), textFont, Element.ALIGN_CENTER);

            // 6. **添加 "评审时间" 和其值**
            addCell(table, "评审时间", textFont, Element.ALIGN_CENTER);
            addCell(table, DateUtils.dateToStr(projectInfo.getReviewTime()), textFont, Element.ALIGN_CENTER);

            // 8. **添加 "抽取结果"**
            addCell(table, "抽取结果", textFont, Element.ALIGN_CENTER, 2);

            // 9. **切换为 5 列表格**
            PdfPTable fiveColumnTable = new PdfPTable(5);
            fiveColumnTable.setWidthPercentage(100);
            table.setSpacingBefore(0f);
            table.setSpacingAfter(0f);
            fiveColumnTable.setWidths(new float[]{2f, 3f, 3f, 2f, 2f});

            // 10. **添加列名**
            addCell(fiveColumnTable, "姓名", textFont, Element.ALIGN_CENTER);
            addCell(fiveColumnTable, "单位", textFont, Element.ALIGN_CENTER);
            addCell(fiveColumnTable, "联系电话", textFont, Element.ALIGN_CENTER);
            addCell(fiveColumnTable, "是否确认", textFont, Element.ALIGN_CENTER);
            addCell(fiveColumnTable, "是否补录", textFont, Element.ALIGN_CENTER);

            // 11. **填充专家信息**
            for (JudgeInfoVo judge : judgeInfoVos) {
                addCell(fiveColumnTable, judge.getJudgeName(), textFont, Element.ALIGN_CENTER);
                addCell(fiveColumnTable, judge.getWorkLocation(), textFont, Element.ALIGN_CENTER);
                addCell(fiveColumnTable, judge.getContactInformation(), textFont, Element.ALIGN_CENTER);
                addCell(fiveColumnTable, judge.getStatus() == 1 ? "是" : "否", textFont, Element.ALIGN_CENTER);
                addCell(fiveColumnTable, judge.getRemark()==null?"否":"是", textFont, Element.ALIGN_CENTER);
            }
            // 12. **签名区域**
            PdfPTable signatureTable = new PdfPTable(5); // 5列布局
            signatureTable.setWidthPercentage(100);
            table.setSpacingBefore(0f);
            table.setSpacingAfter(0f);
            signatureTable.setWidths(new float[]{2f, 3f, 3f, 2f, 2f}); // 定义列宽比例
            // **第一行：抽取人签名 和 单位负责人签名**
            addLargeCell(signatureTable, "抽取人签名：", textFont, 2);
            addLargeCell(signatureTable, "单位负责人签名：", textFont, 3);
            // **第二行：参与人员签名 和 单位组织签名**
            addLargeCell(signatureTable, "参与人员签名：", textFont, 2);
            addLargeCell(signatureTable, "单位组织签名：", textFont, 3);

            // 13. **添加表格到 PDF**
            document.add(table);
            document.add(fiveColumnTable);
            document.add(signatureTable);
            document.close();
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException("PDF 生成失败", e);
        }
    }

    /**
     * 添加大单元格（左上角对齐，跨列）
     */
    private static void addLargeCell(PdfPTable table, String text, Font font, int colspan) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT); // 靠左对齐
        cell.setVerticalAlignment(Element.ALIGN_TOP);   // 文字靠上
        cell.setColspan(colspan);  // 跨多列
        cell.setFixedHeight(200f);  // 增加单元格高度
        cell.setPadding(5f);      // 增加内边距
        table.addCell(cell);
    }

    /**
     * 添加表格单元格（默认单列）
     */
    private static void addCell(PdfPTable table, String text, Font font, int align) {
        addCell(table, text, font, align, 1);
    }

    /**
     * 添加表格单元格（支持合并列）
     */
    private static void addCell(PdfPTable table, String text, Font font, int align, int colspan) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setHorizontalAlignment(align);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(colspan);
        cell.setPadding(5f);
        table.addCell(cell);
    }
}
