package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Id;

/**
 * 【请填写功能名称】对象 judge_info
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public class JudgeInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Id
    private Long id;

    /** 专家名称 */
    @Excel(name = "专家名称")
    private String judgeName;

    /** 工作地点 */
    @Excel(name = "工作地点")
    private String workLocation;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contactInformation;


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setJudgeName(String judgeName) 
    {
        this.judgeName = judgeName;
    }

    public String getJudgeName() 
    {
        return judgeName;
    }

    public void setWorkLocation(String workLocation) 
    {
        this.workLocation = workLocation;
    }

    public String getWorkLocation() 
    {
        return workLocation;
    }

    public void setContactInformation(String contactInformation) 
    {
        this.contactInformation = contactInformation;
    }

    public String getContactInformation() 
    {
        return contactInformation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("judgeName", getJudgeName())
            .append("workLocation", getWorkLocation())
            .append("contactInformation", getContactInformation())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
