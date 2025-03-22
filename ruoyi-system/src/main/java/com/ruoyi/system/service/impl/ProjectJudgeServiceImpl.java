package com.ruoyi.system.service.impl;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ProjectJudgeMapper;
import com.ruoyi.system.domain.ProjectJudge;
import com.ruoyi.system.service.IProjectJudgeService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
@Service
public class ProjectJudgeServiceImpl implements IProjectJudgeService 
{
    @Autowired
    private ProjectJudgeMapper projectJudgeMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param projectId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public List<ProjectJudge> selectProjectJudgeByProjectId(Long projectId)
    {
        return projectJudgeMapper.selectProjectJudgeByProjectId(projectId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param projectJudge 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ProjectJudge> selectProjectJudgeList(ProjectJudge projectJudge)
    {
        return projectJudgeMapper.selectProjectJudgeList(projectJudge);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param projectJudge 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertProjectJudge(ProjectJudge projectJudge)
    {
        return projectJudgeMapper.insertProjectJudge(projectJudge);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param projectJudge 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateProjectJudge(ProjectJudge projectJudge)
    {
        return projectJudgeMapper.updateProjectJudge(projectJudge);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param projectIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteProjectJudgeByProjectIds(Long[] projectIds)
    {
        return projectJudgeMapper.deleteProjectJudgeByProjectIds(projectIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param projectId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteProjectJudgeByProjectId(Long projectId)
    {
        return projectJudgeMapper.deleteProjectJudgeByProjectId(projectId);
    }

    @Override
    public List<ProjectJudge> selectProjectAllJudgeList(ProjectJudge projectJudge) {
        return projectJudgeMapper.selectProjectAllJudgeList(projectJudge);
    }
}
