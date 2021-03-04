package com.jgybzx.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Prp_Show_Component {

    @JsonProperty("belong_page")
    private String belongPage; // 组件归属页面 01 制作页 02 展示页 03 后台产品配置页

    @JsonProperty("check_default")
    private String checkDefault; // 默认是否勾选 Y 是 N 否

    @JsonProperty("component_name")
    private String componentName; // 组件名称

    @JsonProperty("component_no")
    private String componentNo; // 组件编码

    @JsonProperty("patch_memo")
    private String patchMemo; //

    @JsonProperty("show_order")
    private String showOrder; // 显示顺序

    @JsonProperty("template_uuid")
    private String templateUuid; // 模版UUID

    @JsonProperty("ui_type")
    private String uiType; // 展示类型 input 录入 radio 单选 checkbox 多选

    @JsonProperty("useable")
    private String useable; // 是否可用 Y 可用 N 不可用

    public Prp_Show_Component() {
    }


    @Override
    public String toString() {
        return "Prp_Show_Component{" +
                "belongPage='" + belongPage + '\'' +
                ", checkDefault='" + checkDefault + '\'' +
                ", componentName='" + componentName + '\'' +
                ", componentNo='" + componentNo + '\'' +
                ", patchMemo='" + patchMemo + '\'' +
                ", showOrder='" + showOrder + '\'' +
                ", templateUuid='" + templateUuid + '\'' +
                ", uiType='" + uiType + '\'' +
                ", useable='" + useable + '\'' +
                '}';
    }

    public String getBelongPage() {
        return belongPage;
    }

    public void setBelongPage(String belongPage) {
        this.belongPage = belongPage;
    }

    public String getCheckDefault() {
        return checkDefault;
    }

    public void setCheckDefault(String checkDefault) {
        this.checkDefault = checkDefault;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentNo() {
        return componentNo;
    }

    public void setComponentNo(String componentNo) {
        this.componentNo = componentNo;
    }

    public String getPatchMemo() {
        return patchMemo;
    }

    public void setPatchMemo(String patchMemo) {
        this.patchMemo = patchMemo;
    }

    public String getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(String showOrder) {
        this.showOrder = showOrder;
    }

    public String getTemplateUuid() {
        return templateUuid;
    }

    public void setTemplateUuid(String templateUuid) {
        this.templateUuid = templateUuid;
    }

    public String getUiType() {
        return uiType;
    }

    public void setUiType(String uiType) {
        this.uiType = uiType;
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }
}