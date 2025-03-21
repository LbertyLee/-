package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.JudgeInfo;
import com.ruoyi.system.domain.ProjectInfo;
import com.ruoyi.system.domain.vo.JudgeInfoVo;
import com.ruoyi.system.domain.vo.ProjectInfoVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public interface IProjectInfoService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ProjectInfoVo selectProjectInfoById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param projectInfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ProjectInfo> selectProjectInfoList(ProjectInfo projectInfo);


    List<ProjectInfo> selectProjectInVOfoList(ProjectInfo projectInfo);
    /**
     * 新增【请填写功能名称】
     * 
     * @param projectInfo 【请填写功能名称】
     * @return 结果
     */
    public ProjectInfo insertProjectInfo(ProjectInfo projectInfo);

    /**
     * 修改【请填写功能名称】
     * 
     * @param projectInfo 【请填写功能名称】
     * @return 结果
     */
    public int updateProjectInfo(ProjectInfo projectInfo);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteProjectInfoByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteProjectInfoById(Long id);

    List<JudgeInfoVo>  getJudgeList(Long id);

    /**
     * 补录评审信息
     * @param projectInfo
     * @return
     */
    int supplementalJudge(ProjectInfo projectInfo);

    /**
     * 批量导入专家信息
     * @param file
     * @param updateSupport
     * @param operName
     * @return
     */
    String importData(MultipartFile file, boolean updateSupport, String operName);

    void download(HttpServletResponse response, ProjectInfo projectInfo);


}
