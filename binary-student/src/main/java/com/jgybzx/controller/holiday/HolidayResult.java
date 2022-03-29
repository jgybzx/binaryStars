/**
  * Copyright 2021 bejson.com 
  */
package com.jgybzx.controller.holiday;


/**
 * @author Jgybzx
 */
public class HolidayResult {

    private int error_code;
    private String reason;
    private Result result;
    public void setError_code(int error_code) {
         this.error_code = error_code;
     }
     public int getError_code() {
         return error_code;
     }

    public void setReason(String reason) {
         this.reason = reason;
     }
     public String getReason() {
         return reason;
     }

    public void setResult(Result result) {
         this.result = result;
     }
     public Result getResult() {
         return result;
     }

}