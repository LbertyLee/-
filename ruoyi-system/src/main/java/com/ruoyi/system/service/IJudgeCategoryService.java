package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.JudgeCategory;

/**
 * 专家类别Service接口
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public interface IJudgeCategoryService 
{
    /**
     * 查询专家类别
     * 
     * @param id 专家类别主键
     * @return 专家类别
     */
    public JudgeCategory selectJudgeCategoryById(Long id);

    /**
     * 查询专家类别列表
     * 
     * @param judgeCategory 专家类别
     * @return 专家类别集合
     */
    public List<JudgeCategory> selectJudgeCategoryList(JudgeCategory judgeCategory);

    /**
     * 新增专家类别
     * 
     * @param judgeCategory 专家类别
     * @return 结果
     */
    public int insertJudgeCategory(JudgeCategory judgeCategory);

    /**
     * 修改专家类别
     * 
     * @param judgeCategory 专家类别
     * @return 结果
     */
    public int updateJudgeCategory(JudgeCategory judgeCategory);

    /**
     * 批量删除专家类别
     * 
     * @param ids 需要删除的专家类别主键集合
     * @return 结果
     */
    public int deleteJudgeCategoryByIds(Long[] ids);

    /**
     * 删除专家类别信息
     * 
     * @param id 专家类别主键
     * @return 结果
     */
    public int deleteJudgeCategoryById(Long id);
}
