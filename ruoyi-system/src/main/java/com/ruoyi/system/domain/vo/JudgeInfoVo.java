package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;

public class JudgeInfoVo {
    /** $column.columnComment */
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
    /** 状态*/
    private Integer status;
    /**备注*/
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
