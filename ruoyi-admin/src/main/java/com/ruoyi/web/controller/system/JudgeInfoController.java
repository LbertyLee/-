package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.ProjectInfo;
import com.ruoyi.system.domain.bo.JudgeInfoBO;
import com.ruoyi.system.domain.vo.JudgeInfoVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.JudgeInfo;
import com.ruoyi.system.service.IJudgeInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
@RestController
@RequestMapping("/system/judge_info")
public class JudgeInfoController extends BaseController
{
    @Autowired
    private IJudgeInfoService judgeInfoService;

    /**
     * 查询专家信息列表
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/list")
    public TableDataInfo list(JudgeInfoBO judgeInfoBO)
    {
        startPage();
//        List<JudgeInfoVo> list = judgeInfoService.selectJudgeInfoVOList(judgeInfoBO);
        List<JudgeInfo> list = judgeInfoService.selectJudgeInfoVOList(judgeInfoBO);
        return getDataTable(list);
    }

    /**
     * 导出专家信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "专家信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JudgeInfo judgeInfo)
    {
        List<JudgeInfo> list = judgeInfoService.selectJudgeInfoList(judgeInfo);
        ExcelUtil<JudgeInfo> util = new ExcelUtil<JudgeInfo>(JudgeInfo.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取专家信息详细信息
     */
    @Log(title = "获取专家信息", businessType = BusinessType.EXPORT)
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(judgeInfoService.selectJudgeInfoVOById(id));
    }

    /**
     * 新增专家信息
     */
    @Log(title = "新增专家信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JudgeInfoBO judgeInfo)
    {
        return toAjax(judgeInfoService.insertJudgeInfoBO(judgeInfo));
    }

    /**
     * 修改专家信息
     */
    @Log(title = "修改专家信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JudgeInfoBO judgeInfoBO)
    {
        return toAjax(judgeInfoService.updateJudgeInfo(judgeInfoBO));
    }

    /**
     * 删除专家信息
     */
    @Log(title = "删除专家信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long ids)
    {
        return toAjax(judgeInfoService.deleteJudgeInfoByIds(ids));
    }
    /**
     * 批量导入专家信息
     */
    @PostMapping("/importData")
    @Log(title = "批量导入专家信息", businessType = BusinessType.IMPORT)
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception{
        ExcelUtil<JudgeInfoBO> util = new ExcelUtil<JudgeInfoBO>(JudgeInfoBO.class);
        List<JudgeInfoBO> userList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message=judgeInfoService.importData(userList, updateSupport,operName);
        return success(message);
    }

    @PostMapping("/importTemplate")
    @Log(title = "下载导入专家信息模板", businessType = BusinessType.EXPORT)
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<JudgeInfoBO> util = new ExcelUtil<JudgeInfoBO>(JudgeInfoBO.class);
        util.importTemplateExcel(response, "专家数据");
    }
}
