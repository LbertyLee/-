package com.ruoyi.system.domain.bo;

import com.ruoyi.common.annotation.Excel;

public class JudgeInfoBO {

    /**专家id*/
    @Excel(name = "专家id")
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

    /**专业类别*/
    @Excel(name = "专业类别[1,2,3,4]，请查看系统")
    private String judgeCategory;

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

    public String getJudgeCategory() {
        return judgeCategory;
    }

    public void setJudgeCategory(String judgeCategory) {
        this.judgeCategory = judgeCategory;
    }
}
