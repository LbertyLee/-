package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.domain.JudgeCategory;
import com.ruoyi.system.service.IJudgeCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 专家类别Controller
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
@RestController
@RequestMapping("/system/judge_category")
public class JudgeCategoryController extends BaseController
{
    @Autowired
    private IJudgeCategoryService judgeCategoryService;

    /**
     * 查询专家类别列表
     */
    @GetMapping("/list")
    public TableDataInfo list(JudgeCategory judgeCategory)
    {
        startPage();
        List<JudgeCategory> list = judgeCategoryService.selectJudgeCategoryList(judgeCategory);
        return getDataTable(list);
    }

    /**
     * 导出专家类别列表
     */
    @Log(title = "专家类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JudgeCategory judgeCategory)
    {
        List<JudgeCategory> list = judgeCategoryService.selectJudgeCategoryList(judgeCategory);
        ExcelUtil<JudgeCategory> util = new ExcelUtil<JudgeCategory>(JudgeCategory.class);
        util.exportExcel(response, list, "专家类别数据");
    }

    /**
     * 获取专家类别详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(judgeCategoryService.selectJudgeCategoryById(id));
    }

    /**
     * 新增专家类别
     */
    @Log(title = "专家类别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JudgeCategory judgeCategory)
    {
        return toAjax(judgeCategoryService.insertJudgeCategory(judgeCategory));
    }

    /**
     * 修改专家类别
     */
    @Log(title = "专家类别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JudgeCategory judgeCategory)
    {
        return toAjax(judgeCategoryService.updateJudgeCategory(judgeCategory));
    }

    /**
     * 删除专家类别
     */
    @Log(title = "专家类别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(judgeCategoryService.deleteJudgeCategoryByIds(ids));
    }
}
