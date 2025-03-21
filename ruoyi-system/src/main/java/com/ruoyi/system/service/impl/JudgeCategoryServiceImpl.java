package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.JudgeCategoryMapper;
import com.ruoyi.system.domain.JudgeCategory;
import com.ruoyi.system.service.IJudgeCategoryService;

/**
 * 专家类别Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
@Service
public class JudgeCategoryServiceImpl implements IJudgeCategoryService 
{
    @Autowired
    private JudgeCategoryMapper judgeCategoryMapper;

    /**
     * 查询专家类别
     * 
     * @param id 专家类别主键
     * @return 专家类别
     */
    @Override
    public JudgeCategory selectJudgeCategoryById(Long id)
    {
        return judgeCategoryMapper.selectJudgeCategoryById(id);
    }

    /**
     * 查询专家类别列表
     * 
     * @param judgeCategory 专家类别
     * @return 专家类别
     */
    @Override
    public List<JudgeCategory> selectJudgeCategoryList(JudgeCategory judgeCategory)
    {
        return judgeCategoryMapper.selectJudgeCategoryList(judgeCategory);
    }

    /**
     * 新增专家类别
     * 
     * @param judgeCategory 专家类别
     * @return 结果
     */
    @Override
    public int insertJudgeCategory(JudgeCategory judgeCategory)
    {
        judgeCategory.setCreateBy(SecurityUtils.getUsername());
        judgeCategory.setCreateTime(DateUtils.getNowDate());
        return judgeCategoryMapper.insertJudgeCategory(judgeCategory);
    }

    /**
     * 修改专家类别
     * 
     * @param judgeCategory 专家类别
     * @return 结果
     */
    @Override
    public int updateJudgeCategory(JudgeCategory judgeCategory)
    {
        judgeCategory.setUpdateTime(DateUtils.getNowDate());
        return judgeCategoryMapper.updateJudgeCategory(judgeCategory);
    }

    /**
     * 批量删除专家类别
     * 
     * @param ids 需要删除的专家类别主键
     * @return 结果
     */
    @Override
    public int deleteJudgeCategoryByIds(Long[] ids)
    {
        return judgeCategoryMapper.deleteJudgeCategoryByIds(ids);
    }

    /**
     * 删除专家类别信息
     * 
     * @param id 专家类别主键
     * @return 结果
     */
    @Override
    public int deleteJudgeCategoryById(Long id)
    {
        return judgeCategoryMapper.deleteJudgeCategoryById(id);
    }
}
