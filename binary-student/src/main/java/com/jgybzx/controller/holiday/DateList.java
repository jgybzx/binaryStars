/**
 * Copyright 2021 bejson.com
 */
package com.jgybzx.controller.holiday;

import java.util.Date;


/**
 * @author Jgybzx
 */
public class DateList {

    private String uuid;
    private Date date;
    private String status;

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}