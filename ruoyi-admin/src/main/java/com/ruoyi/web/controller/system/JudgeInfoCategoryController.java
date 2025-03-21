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
import com.ruoyi.system.domain.JudgeInfoCategory;
import com.ruoyi.system.service.IJudgeInfoCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
@RestController
@RequestMapping("/system/judge_info_category")
public class JudgeInfoCategoryController extends BaseController
{
    @Autowired
    private IJudgeInfoCategoryService judgeInfoCategoryService;

    /**
     * 查询专家分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/list")
    @Log(title = "专家分类列表", businessType = BusinessType.OTHER)
    public TableDataInfo list(JudgeInfoCategory judgeInfoCategory)
    {
        startPage();
        List<JudgeInfoCategory> list = judgeInfoCategoryService.selectJudgeInfoCategoryList(judgeInfoCategory);
        return getDataTable(list);
    }

    /**
     * 导出【专家分类】列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:export')")
    @Log(title = "导出【专家分类】列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JudgeInfoCategory judgeInfoCategory)
    {
        List<JudgeInfoCategory> list = judgeInfoCategoryService.selectJudgeInfoCategoryList(judgeInfoCategory);
        ExcelUtil<JudgeInfoCategory> util = new ExcelUtil<JudgeInfoCategory>(JudgeInfoCategory.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取专家分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{judgeId}")
    @Log(title = "获取专家分类详细信息", businessType = BusinessType.EXPORT)
    public AjaxResult getInfo(@PathVariable("judgeId") Long judgeId)
    {
        return success(judgeInfoCategoryService.selectJudgeInfoCategoryByJudgeId(judgeId));
    }

    /**
     * 新增专家分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "新增专家分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JudgeInfoCategory judgeInfoCategory)
    {
        return toAjax(judgeInfoCategoryService.insertJudgeInfoCategory(judgeInfoCategory));
    }

    /**
     * 修改专家分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "修改专家分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JudgeInfoCategory judgeInfoCategory)
    {
        return toAjax(judgeInfoCategoryService.updateJudgeInfoCategory(judgeInfoCategory));
    }

    /**
     * 删除专家分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "删除专家分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{judgeIds}")
    public AjaxResult remove(@PathVariable Long[] judgeIds)
    {
        return toAjax(judgeInfoCategoryService.deleteJudgeInfoCategoryByJudgeIds(judgeIds));
    }
}
