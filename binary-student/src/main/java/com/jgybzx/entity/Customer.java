package com.jgybzx.entity;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * des:
 *
 * @author jgybzx
 * @date 2021/6/25 9:04
 */
public class Customer {
    //    private String number;
//    private String customerNo;
//    private String customerName;
//    private String customerDescription;
//    private String customerPhone;
//    private String consultantName;
//    private String consultantPhone;
//    private String customerLevel;
//    private String agentName;
//    private String noTrace;
//    private String lastTraceDate;
//    private String communicationRecord;
//    private String status;
    @ExcelProperty("序号")
    private String number;
    @ExcelProperty("客户号")
    private String customerNo;
    @ExcelProperty("客户姓名")
    private String customerName;
    @ExcelProperty("客户概述")
    private String customerDescription;
    @ExcelProperty("客户联系电话")
    private String customerPhone;
    @ExcelProperty("咨询人姓名")
    private String consultantName;
    @ExcelProperty("咨询人联系电话")
    private String consultantPhone;
    @ExcelProperty("客户级别")
    private String customerLevel;
    @ExcelProperty("销售人员")
    private String agentName;
    @ExcelProperty("未跟踪天数")
    private String noTrace;
    @ExcelProperty("末次跟踪日期")
    private String lastTraceDate;

    @ExcelProperty("沟通记录")
    private String communicationRecord;
    @ExcelProperty("进度")
    private String status;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerDescription() {
        return customerDescription;
    }

    public void setCustomerDescription(String customerDescription) {
        this.customerDescription = customerDescription;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getConsultantPhone() {
        return consultantPhone;
    }

    public void setConsultantPhone(String consultantPhone) {
        this.consultantPhone = consultantPhone;
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getNoTrace() {
        return noTrace;
    }

    public void setNoTrace(String noTrace) {
        this.noTrace = noTrace;
    }

    public String getLastTraceDate() {
        return lastTraceDate;
    }

    public void setLastTraceDate(String lastTraceDate) {
        this.lastTraceDate = lastTraceDate;
    }

    public String getCommunicationRecord() {
        return communicationRecord;
    }

    public void setCommunicationRecord(String communicationRecord) {
        this.communicationRecord = communicationRecord;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
