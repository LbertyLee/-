package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.ProjectInfo;
import com.ruoyi.system.domain.ProjectJudge;
import com.ruoyi.system.domain.vo.JudgeInfoVo;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.List;

@Service
public class WordService {

    public void createWordDocument(HttpServletResponse response, ProjectInfo projectInfo, List<JudgeInfoVo> judgeInfoVos) {
        try (XWPFDocument document = new XWPFDocument()) {
            // 创建标题段落
            XWPFParagraph titleParagraph = document.createParagraph();
            titleParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = titleParagraph.createRun();
            //项目名称
            String projectName = projectInfo.getProjectName();
            titleRun.setText(projectName + " 确认书");
            titleRun.setFontSize(16);
            titleRun.setBold(true);
            //专家个数
            int expertCount = judgeInfoVos.size();
            //总行数
            int totalRows = 6 + expertCount;
            // 创建表格
            XWPFTable table = document.createTable(totalRows, 5);

            // 设置表格宽度
            CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();
            width.setType(STTblWidth.DXA);
            width.setW(BigInteger.valueOf(9000));

            // 设置第一行内容（项目名称）
            XWPFTableRow row0 = table.getRow(0);
            row0.getCell(0).setText("项目名称");
            mergeCellsHorizontal(table, 0, 1, 4);
            // 设置项目名称
            row0.getCell(1).setText(projectName);

            // 设置第二行内容（评审时间）
            XWPFTableRow row1 = table.getRow(1);
            row1.getCell(0).setText("评审时间");
            mergeCellsHorizontal(table, 1, 1, 4);
            // 设置评审时间
            row1.getCell(1).setText(DateUtils.dateToStr(projectInfo.getReviewTime()));

            // 设置第三行内容（抽取结果）
            XWPFTableRow row2 = table.getRow(2);
            row2.getCell(0).setText("抽取结果");
            mergeCellsHorizontal(table, 2, 0, 4);

            // 设置第四行内容（表头）
            XWPFTableRow row3 = table.getRow(3);
            row3.getCell(0).setText("姓名");
            row3.getCell(1).setText("单位");
            row3.getCell(2).setText("联系电话");
            row3.getCell(3).setText("是否确认");
            row3.getCell(4).setText("是否补录");
            // 设置专家信息
            for (int i = 0; i < expertCount; i++) {
                XWPFTableRow row = table.getRow(4 + i);
                JudgeInfoVo judgeInfoVo = judgeInfoVos.get(i);
                row.getCell(0).setText(judgeInfoVo.getJudgeName());
                row.getCell(1).setText(judgeInfoVo.getWorkLocation());
                row.getCell(2).setText(judgeInfoVo.getContactInformation());
                row.getCell(3).setText(judgeInfoVo.getStatus() == 1 ? "是" : "否");
                row.getCell(4).setText(judgeInfoVo.getRemark());
            }
            int signatureStartRow = totalRows - 2;

            // 抽取人签名
            XWPFTableRow signRow1 = table.getRow(signatureStartRow);
            signRow1.getCell(0).setText("抽取人签名：");
            mergeCellsHorizontal(table, signatureStartRow, 0, 1);
            signRow1.getCell(2).setText("单位负责人签名：");
            mergeCellsHorizontal(table, signatureStartRow, 2, 4);
            // 参与人员签名
            XWPFTableRow signRow2 = table.getRow(signatureStartRow + 1);
            signRow2.getCell(0).setText("参与人员签名：");
            mergeCellsHorizontal(table, signatureStartRow + 1, 0, 1);
            signRow2.getCell(2).setText("单位组织签名：");
            mergeCellsHorizontal(table, signatureStartRow + 1, 2, 4);

            // 设置表格样式
            setTableStyle(table);
            // 保存文档
            try {
                // 生成文件名（防止特殊字符影响文件名，可以替换掉特殊字符）
                String fileName = projectName.replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5]", "") + ".docx";
                response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                document.write(response.getOutputStream());
                response.getOutputStream().flush();
            } catch (Exception e) {
                throw new RuntimeException("导出失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 合并水平单元格的辅助方法
    private void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    // 合并垂直单元格的辅助方法
    private void mergeCellsVertical(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if (rowIndex == fromRow) {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    private void setTableStyle(XWPFTable table) {
        // 设置表格边框
        table.setBottomBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table.setTopBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table.setLeftBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table.setRightBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");

        int signatureStartRow = table.getNumberOfRows() - 2; // 倒数第二行是签名区域

        // 设置每一行的高度
        for (int i = 0; i < table.getRows().size(); i++) {
            XWPFTableRow row = table.getRow(i);
            if (i == signatureStartRow || i == signatureStartRow + 1) {
                // 抽取人签名 & 参与人员签名行设置更大高度
                row.setHeight(3000);
            } else {
                row.setHeight(600);
            }
        }

        // 设置单元格样式
        for (int i = 0; i < table.getRows().size(); i++) {
            XWPFTableRow row = table.getRow(i);
            for (XWPFTableCell cell : row.getTableCells()) {
                XWPFParagraph paragraph = cell.getParagraphs().get(0);
                if (i == signatureStartRow || i == signatureStartRow + 1) {
                    paragraph.setAlignment(ParagraphAlignment.LEFT); // 水平居中
                    cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.TOP); // 仅对倒数第二行生效
                } else {
                    // 其他行的单元格：垂直居中，水平居中
                    paragraph.setAlignment(ParagraphAlignment.CENTER);
                    cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                }
            }
        }
        // 设置倒数第一行和倒数第二行的单元格宽度
        setSignatureRowColumnWidths(table, signatureStartRow);
    }

    private void setSignatureRowColumnWidths(XWPFTable table, int signatureStartRow) {
        // 获取倒数第一行和倒数第二行的列数
        XWPFTableRow signRow1 = table.getRow(signatureStartRow);
        XWPFTableRow signRow2 = table.getRow(signatureStartRow + 1);
        int totalColumns = signRow1.getTableCells().size();

        // 设置倒数第一行和倒数第二行的每个单元格的宽度（平均分配）
        for (int i = 0; i < totalColumns; i++) {
            // 获取这两行的单元格
            XWPFTableCell cell1 = signRow1.getCell(i);
            XWPFTableCell cell2 = signRow2.getCell(i);

            // 设置单元格宽度，这里假设宽度为固定值，根据需要调整
            String width = "7000"; // 设置宽度为1800（可以调整）
            cell1.getCTTc().getTcPr().addNewTcW().setW(new BigInteger(width));
            cell2.getCTTc().getTcPr().addNewTcW().setW(new BigInteger(width));
        }
    }


}
