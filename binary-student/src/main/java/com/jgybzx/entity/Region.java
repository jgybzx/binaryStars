package com.jgybzx.entity;

import java.io.Serializable;

public class Region implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region.code
     *
     * @mbggenerated Thu Jun 10 17:53:54 CST 2021
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region.p_code
     *
     * @mbggenerated Thu Jun 10 17:53:54 CST 2021
     */
    private String pCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region.name
     *
     * @mbggenerated Thu Jun 10 17:53:54 CST 2021
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table region
     *
     * @mbggenerated Thu Jun 10 17:53:54 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column region.code
     *
     * @return the value of region.code
     *
     * @mbggenerated Thu Jun 10 17:53:54 CST 2021
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column region.code
     *
     * @param code the value for region.code
     *
     * @mbggenerated Thu Jun 10 17:53:54 CST 2021
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column region.p_code
     *
     * @return the value of region.p_code
     *
     * @mbggenerated Thu Jun 10 17:53:54 CST 2021
     */
    public String getpCode() {
        return pCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column region.p_code
     *
     * @param pCode the value for region.p_code
     *
     * @mbggenerated Thu Jun 10 17:53:54 CST 2021
     */
    public void setpCode(String pCode) {
        this.pCode = pCode == null ? null : pCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column region.name
     *
     * @return the value of region.name
     *
     * @mbggenerated Thu Jun 10 17:53:54 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column region.name
     *
     * @param name the value for region.name
     *
     * @mbggenerated Thu Jun 10 17:53:54 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region
     *
     * @mbggenerated Thu Jun 10 17:53:54 CST 2021
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", pCode=").append(pCode);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}