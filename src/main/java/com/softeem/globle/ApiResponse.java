 package com.softeem.globle;

 public class ApiResponse {
     private int code;
     private String msg;
     private Object data;
     private boolean more;

     public ApiResponse(int code, String msg, Object data) {
         this.code = code;
         this.msg = msg;
         this.data = data;
     }

     public ApiResponse() {
         this.code = Status.SUCCESS.getCode();
         this.msg = Status.SUCCESS.getStandardMessage();
     }

     public int getCode() {
         return code;
     }

     public void setCode(int code) {
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

     public boolean isMore() {
         return more;
     }

     public void setMore(boolean more) {
         this.more = more;
     }

     public static ApiResponse ofMessage(int code, String msg) {
         return new ApiResponse(code, msg, null);
     }

     public static ApiResponse ofSuccess(Object data) {
         return new ApiResponse(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(), data);
     }

     public static ApiResponse ofStatus(Status status) {
         return new ApiResponse(status.getCode(), status.getStandardMessage(), null);
     }

     public enum Status {
         SUCCESS(200, "OK"),
         BAD_REQUEST(400, "Bad Request"),
         NOT_FOUND(404, "Not Found"),
         INTERNAL_SERVER_ERROR(500, "Unknown Internal Error"),
         NOT_VALID_PARAM(40005, "Not valid Params"),
         NOT_SUPPORTED_OPERATION(40006, "Operation not supported"),
         NOT_LOGIN(50000, "Not Login");

         private int code;
         private String standardMessage;

         Status(int code, String standardMessage) {
             this.code = code;
             this.standardMessage = standardMessage;
         }

         public int getCode() {
             return code;
         }

         public void setCode(int code) {
             this.code = code;
         }

         public String getStandardMessage() {
             return standardMessage;
         }

         public void setStandardMessage(String standardMessage) {
             this.standardMessage = standardMessage;
         }
     }
 }
