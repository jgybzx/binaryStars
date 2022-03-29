package com.jgybzx.model;

import java.io.Serializable;
import java.util.Date;
public class Student implements Serializable {

    private String id;

    private String name;

    private String sex;

    private String department;

    private String address;

    private String birthday;

    private String datetimeTest;

    private String test;
    private Score score;
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getDatetimeTest() {
        return datetimeTest;
    }

    public void setDatetimeTest(String datetimeTest) {
        this.datetimeTest = datetimeTest;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", sex=").append(sex);
        sb.append(", department=").append(department);
        sb.append(", address=").append(address);
        sb.append(", birthday=").append(birthday);
        sb.append(", datetimeTest=").append(datetimeTest);
        sb.append(", test=").append(test);
        sb.append("]");
        return sb.toString();
    }
}