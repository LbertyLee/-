package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.JudgeInfo;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public interface JudgeInfoMapper 
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


    /**
     * 查询【请填写功能名称】
     *
     * @param judgeInfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public JudgeInfo selectJudgeInfoByJudgeInfo(JudgeInfo judgeInfo);

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
    public int updateJudgeInfo(JudgeInfo judgeInfo);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteJudgeInfoById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJudgeInfoByIds(Long[] ids);

    void updateByJudgeInfo(JudgeInfo judgeInfo);


    List<JudgeInfo> selectJudgeInfoByIds(List<Long> collect);
}
