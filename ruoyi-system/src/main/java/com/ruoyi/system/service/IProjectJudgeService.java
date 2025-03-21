package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ProjectJudge;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public interface IProjectJudgeService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param projectId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public List<ProjectJudge> selectProjectJudgeByProjectId(Long projectId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param projectJudge 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ProjectJudge> selectProjectJudgeList(ProjectJudge projectJudge);

    /**
     * 新增【请填写功能名称】
     * 
     * @param projectJudge 【请填写功能名称】
     * @return 结果
     */
    public int insertProjectJudge(ProjectJudge projectJudge);

    /**
     * 修改【请填写功能名称】
     * 
     * @param projectJudge 【请填写功能名称】
     * @return 结果
     */
    public int updateProjectJudge(ProjectJudge projectJudge);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param projectIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteProjectJudgeByProjectIds(Long[] projectIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param projectId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteProjectJudgeByProjectId(Long projectId);

    List<ProjectJudge> selectProjectAllJudgeList(ProjectJudge projectJudge);
}
