package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 project_judge
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public class ProjectJudge extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目id */
    private Long projectId;

    /** 专家id */
    private Long judgeId;

    /** 到场状态（1：到场 0：能到场 2:不能到场） */
    @Excel(name = "到场状态", readConverterExp = "1：到场 0：能到场 2:不能到场")
    private Integer state;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }

    public void setJudgeId(Long judgeId) 
    {
        this.judgeId = judgeId;
    }

    public Long getJudgeId() 
    {
        return judgeId;
    }

    public void setState(Integer state) 
    {
        this.state = state;
    }

    public Integer getState() 
    {
        return state;
    }

    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("judgeId", getJudgeId())
            .append("state", getState())
            .append("remarks", getRemarks())
            .toString();
    }
}
