package com.jgybzx.controller;

/**
 * @author jgybzx
 * @date 2020/9/10 17:44
 * @description
 */
public class studentD {
    public studentD() {
    }

    public studentD(String userName, String age) {
        this.userName = userName;
        this.age = age;
    }

    private String userName;
    private String age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "studentD{" +
                "userName='" + userName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
