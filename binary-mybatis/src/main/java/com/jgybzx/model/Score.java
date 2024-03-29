package com.jgybzx.model;

import java.io.Serializable;

public class Score implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column score.score_id
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    private String scoreId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column score.student_id
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    private String studentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column score.math
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    private Integer math;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column score.chinese
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    private Integer chinese;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column score.english
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    private Integer english;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column score.total_score
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    private Integer totalScore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table score
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column score.score_id
     *
     * @return the value of score.score_id
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    public String getScoreId() {
        return scoreId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column score.score_id
     *
     * @param scoreId the value for score.score_id
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    public void setScoreId(String scoreId) {
        this.scoreId = scoreId == null ? null : scoreId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column score.student_id
     *
     * @return the value of score.student_id
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column score.student_id
     *
     * @param studentId the value for score.student_id
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column score.math
     *
     * @return the value of score.math
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    public Integer getMath() {
        return math;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column score.math
     *
     * @param math the value for score.math
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    public void setMath(Integer math) {
        this.math = math;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column score.chinese
     *
     * @return the value of score.chinese
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    public Integer getChinese() {
        return chinese;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column score.chinese
     *
     * @param chinese the value for score.chinese
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    public void setChinese(Integer chinese) {
        this.chinese = chinese;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column score.english
     *
     * @return the value of score.english
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    public Integer getEnglish() {
        return english;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column score.english
     *
     * @param english the value for score.english
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    public void setEnglish(Integer english) {
        this.english = english;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column score.total_score
     *
     * @return the value of score.total_score
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    public Integer getTotalScore() {
        return totalScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column score.total_score
     *
     * @param totalScore the value for score.total_score
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score
     *
     * @mbg.generated Wed Jul 28 15:40:33 CST 2021
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", scoreId=").append(scoreId);
        sb.append(", studentId=").append(studentId);
        sb.append(", math=").append(math);
        sb.append(", chinese=").append(chinese);
        sb.append(", english=").append(english);
        sb.append(", totalScore=").append(totalScore);
        sb.append("]");
        return sb.toString();
    }
}