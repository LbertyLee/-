package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Id;

/**
 * 【请填写功能名称】对象 project_info
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public class ProjectInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Id
    private Long id;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 评审时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评审时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewTime;

    /** 专家人数 */
    @Excel(name = "专家人数")
    private Long judgeNum;

    /** 专家类别[1,2,3,4,5] */
    @Excel(name = "专家类别[1,2,3,4,5]")
    private String categorys;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }

    public void setReviewTime(Date reviewTime) 
    {
        this.reviewTime = reviewTime;
    }

    public Date getReviewTime() 
    {
        return reviewTime;
    }

    public void setJudgeNum(Long judgeNum) 
    {
        this.judgeNum = judgeNum;
    }

    public Long getJudgeNum() 
    {
        return judgeNum;
    }

    public void setCategorys(String categorys) 
    {
        this.categorys = categorys;
    }

    public String getCategorys() 
    {
        return categorys;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectName", getProjectName())
            .append("reviewTime", getReviewTime())
            .append("judgeNum", getJudgeNum())
            .append("categorys", getCategorys())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
