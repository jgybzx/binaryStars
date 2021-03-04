package com.jgybzx.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Prp_Template_Conf {

    @JsonProperty("patch_memo")
    private String patchMemo; //

    @JsonProperty("template_name")
    private String templateName; // 模版名称

    @JsonProperty("template_state")
    private String templateState; // 模版状态 01 有效 02 失效 03 停用

    @JsonProperty("template_type")
    private String templateType; // 模版类型 01 个人计划书 02 家庭计划书

    @JsonProperty("template_uuid")
    private String templateUuid; // 模版UUID

    public Prp_Template_Conf() {
    }

    public String getPatchMemo() {
        return patchMemo;
    }

    public void setPatchMemo(String patchMemo) {
        this.patchMemo = patchMemo;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateState() {
        return templateState;
    }

    public void setTemplateState(String templateState) {
        this.templateState = templateState;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getTemplateUuid() {
        return templateUuid;
    }

    public void setTemplateUuid(String templateUuid) {
        this.templateUuid = templateUuid;
    }

    @Override
    public String toString() {
        return "Prp_Template_Conf{" +
                "patchMemo='" + patchMemo + '\'' +
                ", templateName='" + templateName + '\'' +
                ", templateState='" + templateState + '\'' +
                ", templateType='" + templateType + '\'' +
                ", templateUuid='" + templateUuid + '\'' +
                '}';
    }
}