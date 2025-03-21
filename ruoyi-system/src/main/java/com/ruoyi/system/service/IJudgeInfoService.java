package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.JudgeInfo;
import com.ruoyi.system.domain.bo.JudgeInfoBO;
import com.ruoyi.system.domain.vo.JudgeInfoVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public interface IJudgeInfoService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public JudgeInfo selectJudgeInfoById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param judgeInfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<JudgeInfo> selectJudgeInfoList(JudgeInfo judgeInfo);
    public  List<JudgeInfo> selectJudgeInfoVOList(JudgeInfoBO judgeInfoBO);
    /**
     * 新增【请填写功能名称】
     * 
     * @param judgeInfo 【请填写功能名称】
     * @return 结果
     */
    public int insertJudgeInfo(JudgeInfo judgeInfo);

    /**
     * 修改【请填写功能名称】
     * 
     * @param judgeInfo 【请填写功能名称】
     * @return 结果
     */
    public int updateJudgeInfo(JudgeInfoBO judgeInfo);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteJudgeInfoByIds(Long ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteJudgeInfoById(Long id);

    /**
     * 导入【专家信息】数据
     * @param judgeInfoBO
     * @param updateSupport
     * @param operName
     * @return
     */
    String importData(List<JudgeInfoBO>  judgeInfoBO, boolean updateSupport, String operName);

    /**
     * 新增专家
     * @param judgeInfo
     * @return
     */
    int insertJudgeInfoBO(JudgeInfoBO judgeInfo);

    List<JudgeInfo> selectJudgeInfoByIds(List<Long> collect);


    JudgeInfoVo selectJudgeInfoVOById(Long id);


}
