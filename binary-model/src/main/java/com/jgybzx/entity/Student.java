package com.jgybzx.entity;

import java.io.Serializable;

/**
 * @author jgybzx
 * @date 2020/08/18 11:12
 * @description
 */
public class Student implements Serializable {
    private String id;
    private String name;
    private String sex;
    private String department;
    private String address;
    private String birthday;

    public Student() {
    }

    public Student(String id, String name, String sex, String department, String address, String birthday) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.department = department;
        this.address = address;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", department='" + department + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
