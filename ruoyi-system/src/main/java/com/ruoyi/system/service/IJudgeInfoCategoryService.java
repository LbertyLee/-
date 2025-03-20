package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.JudgeInfoCategory;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public interface IJudgeInfoCategoryService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param judgeId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public JudgeInfoCategory selectJudgeInfoCategoryByJudgeId(Long judgeId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param judgeInfoCategory 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<JudgeInfoCategory> selectJudgeInfoCategoryList(JudgeInfoCategory judgeInfoCategory);

    /**
     * 新增【请填写功能名称】
     * 
     * @param judgeInfoCategory 【请填写功能名称】
     * @return 结果
     */
    public int insertJudgeInfoCategory(JudgeInfoCategory judgeInfoCategory);

    /**
     * 修改【请填写功能名称】
     * 
     * @param judgeInfoCategory 【请填写功能名称】
     * @return 结果
     */
    public int updateJudgeInfoCategory(JudgeInfoCategory judgeInfoCategory);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param judgeIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteJudgeInfoCategoryByJudgeIds(Long[] judgeIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param judgeId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteJudgeInfoCategoryByJudgeId(Long judgeId);

    /**
     * 根据类别id查询【请填写功能名称】
     * @param categoryId
     * @return
     */
    List<JudgeInfoCategory> selectJudgeInfoCategoryByCategoryId(Long categoryId);
}
