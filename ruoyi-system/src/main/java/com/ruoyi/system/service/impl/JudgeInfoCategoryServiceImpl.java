package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.JudgeInfoCategoryMapper;
import com.ruoyi.system.domain.JudgeInfoCategory;
import com.ruoyi.system.service.IJudgeInfoCategoryService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
@Service
public class JudgeInfoCategoryServiceImpl implements IJudgeInfoCategoryService 
{
    @Autowired
    private JudgeInfoCategoryMapper judgeInfoCategoryMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param judgeId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public JudgeInfoCategory selectJudgeInfoCategoryByJudgeId(Long judgeId)
    {
        return judgeInfoCategoryMapper.selectJudgeInfoCategoryByJudgeId(judgeId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param judgeInfoCategory 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<JudgeInfoCategory> selectJudgeInfoCategoryList(JudgeInfoCategory judgeInfoCategory)
    {
        return judgeInfoCategoryMapper.selectJudgeInfoCategoryList(judgeInfoCategory);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param judgeInfoCategory 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertJudgeInfoCategory(JudgeInfoCategory judgeInfoCategory)
    {
        judgeInfoCategory.setCreateTime(DateUtils.getNowDate());
        return judgeInfoCategoryMapper.insertJudgeInfoCategory(judgeInfoCategory);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param judgeInfoCategory 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateJudgeInfoCategory(JudgeInfoCategory judgeInfoCategory)
    {
        return judgeInfoCategoryMapper.updateJudgeInfoCategory(judgeInfoCategory);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param judgeIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteJudgeInfoCategoryByJudgeIds(Long[] judgeIds)
    {
        return judgeInfoCategoryMapper.deleteJudgeInfoCategoryByJudgeIds(judgeIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param judgeId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteJudgeInfoCategoryByJudgeId(Long judgeId)
    {
        return judgeInfoCategoryMapper.deleteJudgeInfoCategoryByJudgeId(judgeId);
    }

    @Override
    public List<JudgeInfoCategory> selectJudgeInfoCategoryByCategoryId(Long categoryId) {
        return judgeInfoCategoryMapper.selectJudgeInfoCategoryByCategoryId(categoryId);
    }
}
