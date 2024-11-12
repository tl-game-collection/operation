 package com.softeem.util;

import com.softeem.baseEnum.ResponseCode;
/**
 * 统一返会api
 * @author MyPC
 * @date 2020/06/14
 */
public class ResultData {

     private String code;
     private String msg;
     private int count;
     private Object data;
     

     public static ResultData success(Object data) {
        return resultData(ResponseCode.SUCCESS.val(), ResponseCode.SUCCESS.msg(), data);
     }
     
     public static ResultData success() {
         return resultData(ResponseCode.SUCCESS.val(),ResponseCode.SUCCESS.msg());
      }
     
     public static ResultData success(String code, String msg) {
         return resultData(code, msg, null);
     }
     
     public static ResultData success(String code, String msg,Object data) {
         return resultData(code, msg, data);
     }

     public static ResultData success(Object data, String msg) {
         return resultData(ResponseCode.SUCCESS.val(), msg, data);
     }
     
     public static ResultData fail(String code, String msg) {
         return resultData(code, msg, null);
     }
     
     public static ResultData result(String code, String msg, Object data,int count) {
         return resultData( code,msg,data,count );
     }
     
     
     
     
     
     
     
     

     public static ResultData fail(String code, String msg, Object data) {
         return resultData(code, msg, data);
     }

     
     private static ResultData resultData(String code, String msg) {
         ResultData resultData = new ResultData();
         resultData.setCode(code);
         resultData.setMsg(msg);
  
         return resultData;
     }
     private static ResultData resultData(String code, String msg, Object data) {
         ResultData resultData = new ResultData();
         resultData.setCode(code);
         resultData.setMsg(msg);
         resultData.setData(data);
         return resultData;
     }
     

     
     
     
     private static ResultData resultData(String code, String msg, Object data,int count ) {
         ResultData resultData = new ResultData();
         resultData.setCode(code);
         resultData.setMsg(msg);
         resultData.setData(data);
         resultData.setCount(count);
         return resultData;
     }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    
     
     
 }