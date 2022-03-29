package com.jgybzx.entity;

/**
 * @author jgybzx
 * @date 2020/08/18 11:59
 * @description
 */
public class Score {
    private String id;
    private String stuId;
    private String cName;
    private String grade;

    public Score() {
    }

    public Score(String id, String stuId, String cName, String grade) {
        this.id = id;
        this.stuId = stuId;
        this.cName = cName;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id='" + id + '\'' +
                ", stuId='" + stuId + '\'' +
                ", cName='" + cName + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
