package com.ruoyi.system.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.domain.JudgeInfo;
import com.ruoyi.system.domain.JudgeInfoCategory;
import com.ruoyi.system.domain.ProjectJudge;
import com.ruoyi.system.domain.vo.JudgeInfoVo;
import com.ruoyi.system.service.IJudgeInfoCategoryService;
import com.ruoyi.system.service.IJudgeInfoService;
import com.ruoyi.system.service.IProjectJudgeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ProjectInfoMapper;
import com.ruoyi.system.domain.ProjectInfo;
import com.ruoyi.system.service.IProjectInfoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
@Service
public class ProjectInfoServiceImpl implements IProjectInfoService 
{
    @Autowired
    private ProjectInfoMapper projectInfoMapper;
    @Autowired
    private IJudgeInfoCategoryService judgeInfoCategoryService;
    @Autowired
    private IJudgeInfoService judgeInfoService;
    @Autowired
    private IProjectJudgeService projectJudgeService;
    @Autowired
    private WordService wordService;


    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ProjectInfo selectProjectInfoById(Long id)
    {
        return projectInfoMapper.selectProjectInfoById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param projectInfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ProjectInfo> selectProjectInfoList(ProjectInfo projectInfo)
    {
        return projectInfoMapper.selectProjectInfoList(projectInfo);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param projectInfo 【请填写功能名称】
     * @return 结果
     */
    @Transactional
    @Override
    public ProjectInfo insertProjectInfo(ProjectInfo projectInfo)
    {
        //项目于基础信息
        projectInfo.setCreateTime(DateUtils.getNowDate());
        projectInfo.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
        projectInfo.setId(IdUtil.getSnowflakeNextId());
        projectInfoMapper.insertProjectInfo(projectInfo);
        //盲选评委
        String categorys = projectInfo.getCategorys();
        List<Long> categoryList = extractNumbers(categorys);
        //查询出所有评委（有重复的情况）
        ArrayList<JudgeInfoCategory> judgeInfoCategoriesList = new ArrayList<>();
        for (Long categoryId : categoryList){
            List<JudgeInfoCategory> judgeInfoCategors = judgeInfoCategoryService.selectJudgeInfoCategoryByCategoryId(categoryId);
            judgeInfoCategoriesList.addAll(judgeInfoCategors);
        }
        //根据评委id去重
        Set<Long> seen = new HashSet<>();
        List<JudgeInfoCategory> filteredList = judgeInfoCategoriesList.stream()
                .filter(judgeInfoCategory -> seen.add(judgeInfoCategory.getJudgeId()))
                .collect(Collectors.toList());
        //随机抽取评委
        Random random = new Random();
        List<Long> judgeIds = new ArrayList<>(seen);
        List<Long> selectedJudgeIds = new ArrayList<>();
        int count = (int) Math.min(projectInfo.getJudgeNum(), judgeIds.size());
        while (selectedJudgeIds.size() < count) {
            int randomIndex = random.nextInt(judgeIds.size());
            Long selectedId = judgeIds.remove(randomIndex);
            selectedJudgeIds.add(selectedId);
        }
        //获取抽取出来的评委信息
        ArrayList<JudgeInfo> judgeInfos = new ArrayList<>();
        for (Long selectedJudgeId : selectedJudgeIds) {
            JudgeInfo judgeInfo = judgeInfoService.selectJudgeInfoById(selectedJudgeId);
            judgeInfos.add(judgeInfo);
        }
        //将评委信息插入到project_judge表中
        for (JudgeInfo judgeInfo : judgeInfos){
            ProjectJudge projectJudge = new ProjectJudge();
            projectJudge.setProjectId(projectInfo.getId());
            projectJudge.setJudgeId(judgeInfo.getId());
            projectJudge.setState(0);
            projectJudgeService.insertProjectJudge(projectJudge);
        }
        return projectInfo;
    }

    public static List<Long> extractNumbers(String input) {
        List<Long> result = new ArrayList<>();
        // 去除字符串首尾的方括号
        String trimmed = input.replace("[", "").replace("]", "");
        // 按逗号分割字符串
        String[] parts = trimmed.split(",");
        for (String part : parts) {
            try {
                // 将每个部分转换为整数并添加到列表中
                result.add(Long.parseLong(part.trim()));
            } catch (NumberFormatException e) {
                // 处理非数字的情况
                System.err.println("输入包含非数字字符: " + part);
            }
        }
        return result;
    }
    /**
     * 修改【请填写功能名称】
     * 
     * @param projectInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateProjectInfo(ProjectInfo projectInfo)
    {
        projectInfo.setUpdateTime(DateUtils.getNowDate());
        return projectInfoMapper.updateProjectInfo(projectInfo);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteProjectInfoByIds(Long[] ids)
    {
        return projectInfoMapper.deleteProjectInfoByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteProjectInfoById(Long id)
    {
        return projectInfoMapper.deleteProjectInfoById(id);
    }

    @Override
    public List<JudgeInfoVo> getJudgeList(Long id) {
        ProjectJudge projectJudge = new ProjectJudge();
        projectJudge.setProjectId(id);
        List<ProjectJudge> projectJudges = projectJudgeService.selectProjectJudgeList(projectJudge);
        ArrayList<JudgeInfoVo> judgeInfoVos = new ArrayList<>();
        for (ProjectJudge judge :projectJudges){
            Long judgeId = judge.getJudgeId();
            JudgeInfo judgeInfo = judgeInfoService.selectJudgeInfoById(judgeId);
            JudgeInfoVo judgeInfoVo = new JudgeInfoVo();
            BeanUtils.copyProperties(judgeInfo,judgeInfoVo);
            judgeInfoVo.setStatus(judge.getState());
            judgeInfoVo.setRemark(judge.getRemarks());
            judgeInfoVos.add(judgeInfoVo);
        }
        return judgeInfoVos;
    }

    /**
     * 补充评委
     * @param projectInfo
     * @return
     */
    @Override
    public int supplementalJudge(ProjectInfo projectInfo) {
        String categorys = projectInfo.getCategorys();
        List<Long> categoryList = extractNumbers(categorys);
        //查询出现有评委id
        ProjectJudge projectJudge = new ProjectJudge();
        projectJudge.setProjectId(projectInfo.getId());
        List<ProjectJudge> projectJudges = projectJudgeService.selectProjectJudgeList(projectJudge);
        // 现有评委id集合
        List<Long> collect = projectJudges.stream().map(ProjectJudge::getJudgeId).collect(Collectors.toList());
        Set<Long> collectSet = new HashSet<>(collect); // 转换为 Set 方便快速查找

        // 查询出所有评委（有重复的情况）
        ArrayList<JudgeInfoCategory> judgeInfoCategoriesList = new ArrayList<>();
        for (Long categoryId : categoryList) {
            List<JudgeInfoCategory> judgeInfoCategors = judgeInfoCategoryService.selectJudgeInfoCategoryByCategoryId(categoryId);
            judgeInfoCategoriesList.addAll(judgeInfoCategors);
        }
        // 只记录未出现过的 ID，并且确保不在 collect 里
        Set<Long> seen = new HashSet<>();
        List<JudgeInfoCategory> filteredList = judgeInfoCategoriesList.stream()
                .filter(judgeInfoCategory ->
                        !collectSet.contains(judgeInfoCategory.getJudgeId())// 不在 collect 里
                        && seen.add(judgeInfoCategory.getJudgeId())) // 确保唯一
                .collect(Collectors.toList());

        //随机抽取评委
        Random random = new Random();
        List<Long> judgeIds = new ArrayList<>(seen);
        List<Long> selectedJudgeIds = new ArrayList<>();
        int count = (int) Math.min(projectInfo.getJudgeNum(), judgeIds.size());
        while (selectedJudgeIds.size() < count) {
            int randomIndex = random.nextInt(judgeIds.size());
            Long selectedId = judgeIds.remove(randomIndex);
            selectedJudgeIds.add(selectedId);
        }
        //保存项目评委信息
        for (Long selectedJudgeId : selectedJudgeIds) {
            ProjectJudge projectJudges1 = new ProjectJudge();
            projectJudges1.setJudgeId(selectedJudgeId);
            projectJudges1.setProjectId(projectInfo.getId());
            projectJudges1.setState(0);
            projectJudges1.setRemarks("补录");
            projectJudgeService.insertProjectJudge(projectJudges1);
        }
        return 1;
    }

    /**
     * 批量导入专家信息
     * @param file
     * @param updateSupport
     * @param operName
     * @return
     */
    @Override
    public String importData(MultipartFile file, boolean updateSupport, String operName) {
        return "";
    }

    /**
     * 下载项目确认书
     * @param response
     * @param projectInfo
     */
    @Override
    public void download(HttpServletResponse response, ProjectInfo projectInfo) {
        Long id = projectInfo.getId();
        ProjectInfo projectInfo1 = projectInfoMapper.selectProjectInfoById(id);
        //项目评委信息
        ProjectJudge projectJudge = new ProjectJudge();
        projectJudge.setProjectId(id);
        List<ProjectJudge> projectJudges = projectJudgeService.selectProjectJudgeList(projectJudge);

        List<JudgeInfoVo> judgeInfoVos = new ArrayList<>();
        for (ProjectJudge projectJudge1 : projectJudges) {
            JudgeInfoVo judgeInfoVo = new JudgeInfoVo();
            JudgeInfo judgeInfo = judgeInfoService.selectJudgeInfoById(projectJudge1.getJudgeId());
            judgeInfoVo.setJudgeName(judgeInfo.getJudgeName());
            judgeInfoVo.setWorkLocation(judgeInfo.getWorkLocation());
            judgeInfoVo.setContactInformation(judgeInfo.getContactInformation());
            judgeInfoVo.setStatus(projectJudge1.getState());
            judgeInfoVo.setRemark(projectJudge1.getRemarks());
            judgeInfoVos.add(judgeInfoVo);
        }
        wordService.createWordDocument(response,projectInfo1,judgeInfoVos);
    }
}
