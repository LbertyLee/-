package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.system.domain.JudgeCategory;
import com.ruoyi.system.domain.JudgeInfoCategory;
import com.ruoyi.system.domain.bo.JudgeInfoBO;
import com.ruoyi.system.domain.vo.JudgeInfoVo;
import com.ruoyi.system.service.IJudgeCategoryService;
import com.ruoyi.system.service.IJudgeInfoCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.JudgeInfoMapper;
import com.ruoyi.system.domain.JudgeInfo;
import com.ruoyi.system.service.IJudgeInfoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validator;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2025-03-19
 */
@Service
public class JudgeInfoServiceImpl implements IJudgeInfoService {

    private static final Logger log = LoggerFactory.getLogger(JudgeInfoServiceImpl.class);

    @Autowired
    private JudgeInfoMapper judgeInfoMapper;
    @Autowired
    protected Validator validator;
    @Autowired
    private IJudgeInfoCategoryService judgeInfoCategoryService;
    @Autowired
    private IJudgeCategoryService judgeCategoryService;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public JudgeInfo selectJudgeInfoById(Long id) {
        return judgeInfoMapper.selectJudgeInfoById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param judgeInfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<JudgeInfo> selectJudgeInfoList(JudgeInfo judgeInfo) {
        return judgeInfoMapper.selectJudgeInfoList(judgeInfo);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param judgeInfoBO
     * @return
     */
//    @Override
//    public List<JudgeInfoVo> selectJudgeInfoVOList(JudgeInfoBO judgeInfoBO) {
//        JudgeInfo judgeInfo = new JudgeInfo();
//        BeanUtils.copyBeanProp(judgeInfo, judgeInfoBO);
//        List<JudgeInfo> judgeInfos = judgeInfoMapper.selectJudgeInfoList(judgeInfo);
//        if (!StringUtils.isNull(judgeInfoBO.getJudgeCategory())) {
//            ArrayList<Long> longs = new ArrayList<>();
//            for (Long l : extractNumbers(judgeInfoBO.getJudgeCategory())) {
//                List<JudgeInfoCategory> judgeInfoCategories = judgeInfoCategoryService.selectJudgeInfoCategoryByCategoryId(l);
//                judgeInfoCategories.stream().map(JudgeInfoCategory::getJudgeId).forEach(longs::add);
//            }
//            return judgeInfos.stream()
//                    .filter(judgeInfo1 -> longs.contains(judgeInfo1.getId()))
//                    .map(judgeInfo1 -> {
//                        JudgeInfoVo judgeInfoVo = new JudgeInfoVo();
//                        BeanUtils.copyBeanProp(judgeInfoVo, judgeInfo1);
//                        judgeInfoVo.setJudgeCategoryList(extractNumbers(judgeInfo1));
//                        return judgeInfoVo;
//                    })
//                    .collect(Collectors.toList());
//        }
//        return judgeInfos.stream().map(judgeInfo1 -> {
//            JudgeInfoVo judgeInfoVo = new JudgeInfoVo();
//            BeanUtils.copyBeanProp(judgeInfoVo, judgeInfo1);
//            judgeInfoVo.setJudgeCategoryList(extractNumbers(judgeInfo1));
//            return judgeInfoVo;
//        }).collect(Collectors.toList());
//
//    }
    @Override
    public List<JudgeInfo> selectJudgeInfoVOList(JudgeInfoBO judgeInfoBO) {
        JudgeInfo judgeInfo = new JudgeInfo();
        BeanUtils.copyBeanProp(judgeInfo, judgeInfoBO);
        List<JudgeInfo> judgeInfos = judgeInfoMapper.selectJudgeInfoList(judgeInfo);
        if (!StringUtils.isNull(judgeInfoBO.getJudgeCategory())) {
            ArrayList<Long> longs = new ArrayList<>();
            for (Long l : extractNumbers(judgeInfoBO.getJudgeCategory())) {
                List<JudgeInfoCategory> judgeInfoCategories = judgeInfoCategoryService.selectJudgeInfoCategoryByCategoryId(l);
                judgeInfoCategories.stream().map(JudgeInfoCategory::getJudgeId).forEach(longs::add);
            }
            return judgeInfos.stream()
                    .filter(judgeInfo1 -> longs.contains(judgeInfo1.getId()))
                    .collect(Collectors.toList());
        }
        return judgeInfos;

    }

    /**
     * 获取专家类别
     */
    private List<JudgeCategory> extractNumbers(JudgeInfo judgeInfo) {
        JudgeInfoCategory judgeInfoCategory = new JudgeInfoCategory();
        judgeInfoCategory.setJudgeId(judgeInfo.getId());
        List<JudgeInfoCategory> judgeInfoCategories = judgeInfoCategoryService.selectJudgeInfoCategoryList(judgeInfoCategory);
        List<Long> CategoriesList = judgeInfoCategories.stream().map(JudgeInfoCategory::getCategoryId).collect(Collectors.toList());
        return CategoriesList.stream().map(b -> {
            return judgeCategoryService.selectJudgeCategoryById(b);
        }).collect(Collectors.toList());
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param judgeInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertJudgeInfo(JudgeInfo judgeInfo) {
        judgeInfo.setCreateTime(DateUtils.getNowDate());
        return judgeInfoMapper.insertJudgeInfo(judgeInfo);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param judgeInfoBO 【请填写功能名称】
     * @return 结果
     */
    @Override
    @Transactional
    public int updateJudgeInfo(JudgeInfoBO judgeInfoBO) {
        JudgeInfo judgeInfo = new JudgeInfo();
        BeanUtils.copyBeanProp(judgeInfo, judgeInfoBO);
        judgeInfo.setUpdateTime(DateUtils.getNowDate());
        judgeInfo.setUpdateBy(SecurityUtils.getUsername());
        int i = judgeInfoMapper.updateJudgeInfo(judgeInfo);
        //修改专家类别
        if (!StringUtils.isNull(judgeInfoBO.getJudgeCategory())) {
            //删除原来的类别
            judgeInfoCategoryService.deleteJudgeInfoCategoryByJudgeId(judgeInfoBO.getId());
            String judgeCategory = judgeInfoBO.getJudgeCategory();
            List<Long> longs = extractNumbers(judgeCategory);
            for (Long aLong : longs) {
                JudgeInfoCategory judgeInfoCategory = new JudgeInfoCategory();
                judgeInfoCategory.setJudgeId(judgeInfo.getId());
                judgeInfoCategory.setCategoryId(aLong);
                judgeInfoCategoryService.insertJudgeInfoCategory(judgeInfoCategory);
            }
        }
        return i;
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteJudgeInfoByIds(Long ids) {
        int i = judgeInfoMapper.deleteJudgeInfoById(ids);
        //删除专家类别
        judgeInfoCategoryService.deleteJudgeInfoCategoryByJudgeId(ids);
        return i;
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteJudgeInfoById(Long id) {
        return judgeInfoMapper.deleteJudgeInfoById(id);
    }

    /**
     * 导入【专家信息】数据
     *
     * @param judgeInfoBOs
     * @param updateSupport
     * @param operName
     * @return
     */
    @Transactional
    @Override
    public String importData(List<JudgeInfoBO> judgeInfoBOs, boolean updateSupport, String operName) {
        if (StringUtils.isNull(judgeInfoBOs) || judgeInfoBOs.size() == 0) {
            throw new ServiceException("导入专家数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (JudgeInfoBO judgeInfoBO : judgeInfoBOs) {
            try {
                // 验证是否存在这个专家
                JudgeInfo judgeInfo1 = new JudgeInfo();
                judgeInfo1.setJudgeName(judgeInfoBO.getJudgeName());
                judgeInfo1.setContactInformation(judgeInfoBO.getContactInformation());
                JudgeInfo j = judgeInfoMapper.selectJudgeInfoByJudgeInfo(judgeInfo1);
                if (StringUtils.isNull(j)) {
                    BeanValidators.validateWithException(validator, judgeInfoBO);
                    String judgeCategory = judgeInfoBO.getJudgeCategory();
                    List<Long> longs = extractNumbers(judgeCategory);
                    //新增专家信息
                    JudgeInfo judgeInfo = new JudgeInfo();
                    judgeInfo.setJudgeName(judgeInfoBO.getJudgeName());
                    judgeInfo.setContactInformation(judgeInfoBO.getContactInformation());
                    judgeInfo.setWorkLocation(judgeInfoBO.getWorkLocation());
                    judgeInfo.setCreateTime(DateUtils.getNowDate());
                    judgeInfo.setCreateBy(operName);
//                    judgeInfo.setId(IdUtil.getSnowflakeNextId());
                    judgeInfoMapper.insertJudgeInfo(judgeInfo);
                    //新增专家类别关联表
                    for (Long aLong : longs) {
                        JudgeInfoCategory judgeInfoCategory = new JudgeInfoCategory();
                        judgeInfoCategory.setJudgeId(judgeInfo.getId());
                        judgeInfoCategory.setCategoryId(aLong);
                        judgeInfoCategory.setCreateBy(operName);
                        judgeInfoCategoryService.insertJudgeInfoCategory(judgeInfoCategory);
                    }
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、专家 " + judgeInfoBO.getJudgeName() + " 导入成功");
                } else if (updateSupport) {
                    BeanValidators.validateWithException(validator, judgeInfoBO);
                    JudgeInfo judgeInfo = new JudgeInfo();
                    judgeInfo.setJudgeName(judgeInfoBO.getJudgeName());
                    judgeInfo.setContactInformation(judgeInfoBO.getContactInformation());
                    judgeInfo.setWorkLocation(judgeInfoBO.getWorkLocation());
                    judgeInfo.setUpdateTime(DateUtils.getNowDate());
                    judgeInfo.setUpdateBy(operName);
                    judgeInfo.setId(j.getId());
                    judgeInfoMapper.updateJudgeInfo(judgeInfo);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、专家 " + judgeInfoBO.getJudgeName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、专家 " + judgeInfoBO.getJudgeName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + judgeInfoBO.getJudgeName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 新增专家
     *
     * @param judgeInfo
     * @return
     */
    @Transactional
    @Override
    public int insertJudgeInfoBO(JudgeInfoBO judgeInfo) {
        String judgeCategory = judgeInfo.getJudgeCategory();
        List<Long> longs = extractNumbers(judgeCategory);
        JudgeInfo judgeInfo1 = new JudgeInfo();
        judgeInfo1.setJudgeName(judgeInfo.getJudgeName());
        judgeInfo1.setContactInformation(judgeInfo.getContactInformation());
        judgeInfo1.setWorkLocation(judgeInfo.getWorkLocation());
        judgeInfo1.setCreateTime(DateUtils.getNowDate());
        judgeInfo1.setCreateBy(SecurityUtils.getUsername());
//        judgeInfo1.setId(IdUtil.getSnowflakeNextId());
        int insert = judgeInfoMapper.insertJudgeInfo(judgeInfo1);
//        Long id = judgeInfo1.getId();
        for (Long aLong : longs) {
            JudgeInfoCategory judgeInfoCategory = new JudgeInfoCategory();
            judgeInfoCategory.setJudgeId(judgeInfo1.getId());
            judgeInfoCategory.setCategoryId(aLong);
            judgeInfoCategory.setCreateBy(SecurityUtils.getUsername());
            judgeInfoCategory.setCreateTime(DateUtils.getNowDate());
            judgeInfoCategoryService.insertJudgeInfoCategory(judgeInfoCategory);
        }
        return insert;
    }

    @Override
    public List<JudgeInfo> selectJudgeInfoByIds(List<Long> collect) {
        return judgeInfoMapper.selectJudgeInfoByIds(collect);
    }

    /**
     * 获取专家详情
     *
     * @param id
     * @return
     */
    @Override
    public JudgeInfoVo selectJudgeInfoVOById(Long id) {
        JudgeInfo judgeInfo = judgeInfoMapper.selectJudgeInfoById(id);
        JudgeInfoCategory judgeInfoCategory = new JudgeInfoCategory();
        judgeInfoCategory.setJudgeId(id);
        List<JudgeInfoCategory> judgeInfoCategoryList = judgeInfoCategoryService.selectJudgeInfoCategoryList(judgeInfoCategory);
        //专家类别ids
        JudgeInfoVo judgeInfoVo = new JudgeInfoVo();
        judgeInfoVo.setId(judgeInfo.getId());
        judgeInfoVo.setJudgeName(judgeInfo.getJudgeName());
        judgeInfoVo.setContactInformation(judgeInfo.getContactInformation());
        judgeInfoVo.setWorkLocation(judgeInfo.getWorkLocation());
        List<Long> collect = judgeInfoCategoryList.stream().map(JudgeInfoCategory::getCategoryId).collect(Collectors.toList());
        ArrayList<JudgeCategory> judgeCategories = new ArrayList<>();
        for (Long l : collect) {
            JudgeCategory judgeCategory = judgeCategoryService.selectJudgeCategoryById(l);
            judgeCategories.add(judgeCategory);
        }
        judgeInfoVo.setJudgeCategoryList(judgeCategories);
        return judgeInfoVo;
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
}
