package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.JudgeInfoCategory;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public interface JudgeInfoCategoryMapper 
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
     * 删除【请填写功能名称】
     * 
     * @param judgeId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteJudgeInfoCategoryByJudgeId(Long judgeId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param judgeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJudgeInfoCategoryByJudgeIds(Long[] judgeIds);

    List<JudgeInfoCategory> selectJudgeInfoCategoryByCategoryId(Long categoryId);

}
