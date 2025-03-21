package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.vo.ProjectInfoVo;
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
import com.ruoyi.system.domain.ProjectInfo;
import com.ruoyi.system.service.IProjectInfoService;
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
@RequestMapping("/system/project_info")
public class ProjectInfoController extends BaseController
{
    @Autowired
    private IProjectInfoService projectInfoService;

    /**
     * 查询项目列表
     */
    @GetMapping("/list")
    @Log(title = "查询项目列表", businessType = BusinessType.EXPORT)
    public TableDataInfo list(ProjectInfo projectInfo)
    {
        startPage();
        List<ProjectInfo> list = projectInfoService.selectProjectInVOfoList(projectInfo);
        return getDataTable(list);
    }

    /**
     * 导出项目列表
     */
    @Log(title = "导出项目列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectInfo projectInfo)
    {
        List<ProjectInfo> list = projectInfoService.selectProjectInfoList(projectInfo);
        ExcelUtil<ProjectInfo> util = new ExcelUtil<ProjectInfo>(ProjectInfo.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取项目详细信息
     */
    @GetMapping(value = "/{id}")
    @Log(title = "获取项目详细信息", businessType = BusinessType.EXPORT)
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(projectInfoService.selectProjectInfoById(id));
    }

    /**
     * 新增项目
     */
    @Log(title = "新增项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectInfo projectInfo)
    {
        return success(projectInfoService.insertProjectInfo(projectInfo));
    }

    /**
     * 修改项目
     */
    @Log(title = "修改项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectInfo projectInfo)
    {
        return toAjax(projectInfoService.updateProjectInfo(projectInfo));
    }

    /**
     * 删除项目
     */
    @Log(title = "删除项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(projectInfoService.deleteProjectInfoByIds(ids));
    }

    /**
     * 查看项目评委列表
     */
    @GetMapping("/judge/{id}")
    public AjaxResult getJudgeList(@PathVariable("id") Long id){
        return success(projectInfoService.getJudgeList(id));
    }
    /**
     * 评委补录
     */
    @PostMapping("/supplemental")
    @Log(title = "评委补录", businessType = BusinessType.INSERT)
    public AjaxResult supplementalJudge(@RequestBody ProjectInfo projectInfo){
        return success(projectInfoService.supplementalJudge(projectInfo));
    }
    /**
     * 下载项目确认书
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response, ProjectInfo projectInfo){
        projectInfoService.download(response,projectInfo);
    }




}
