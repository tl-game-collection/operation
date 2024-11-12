 package com.softeem.globle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.alibaba.fastjson.JSON;
import com.softeem.util.ResultData;

/*import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;*/
/**
 * 全局异常处理
 * @author ysy
 * @date 2020/06/29
 */
@Controller
    public class AppErrorController implements ErrorController {
        private static final String ERROR_PATH = "/error";

        private ErrorAttributes errorAttributes;

        @Override
        public String getErrorPath() {
            return ERROR_PATH;
        }

        @Autowired
        public AppErrorController(ErrorAttributes errorAttributes) {
            this.errorAttributes = errorAttributes;
        }

        /**
         * Web页面错误处理
         */
        @RequestMapping(value = ERROR_PATH, produces = "text/html")
        public String errorPageHandler(HttpServletRequest request, HttpServletResponse response) {
            int status = response.getStatus();
            switch (status) {
                case 403:
                    return "403.html";
                case 401:
                    return "401.html";
                    
                case 404:
                    return "404.html";
                case 500:
                    return "500.html";
            }

            return "index";
        }

        /**
         * 除Web页面外的错误处理，比如Json/XML等
         */
        @RequestMapping(value = ERROR_PATH)
        @ResponseBody
        @ExceptionHandler(value = {Exception.class})
        public ApiResponse errorApiHandler(HttpServletRequest request,  Exception ex,  WebRequest req,HttpServletResponse rep) {

            RequestAttributes requestAttributes = new ServletRequestAttributes(request);
            Map<String, Object> attr = this.errorAttributes.getErrorAttributes(req, false);
            int status = getStatus(request);
            
            
            switch (status) {
	            case 403:
	                return ApiResponse.ofMessage(status,"您还没有权限访问,请联系管理员");
	                
			case 401:
				
				    String s=JSON.toJSONString(ApiResponse.ofMessage(status,"员fafafafafaf"));
				    System.out.println(s);
	                return  ApiResponse.ofMessage(status,"您还没有权限访问,请联系管理员");
	                
	                
	                
	            case 404:
	                return ApiResponse.ofMessage(status,"您访问的接口不存在");
	            case 500:
	                return ApiResponse.ofMessage(status,"服务器错误");
	        }
            
      
            
            
            return ApiResponse.ofMessage(status, String.valueOf(attr.getOrDefault("message", "error")));

            
            
            
//            if(ex instanceof UnauthenticatedException) {
//            	  return ApiResponse.ofMessage(status, "您还没有登录");
//      	     }
//      	   else if(ex instanceof UnauthorizedException) {
//      		  return ApiResponse.ofMessage(status,"您还没有权限访问,请联系管理员");
//    		}
//      	   else if(ex instanceof Exception){
//      	
//      		  return ApiResponse.ofMessage(status, String.valueOf(attr.getOrDefault("message", "error")));
//      	     }
//            return ApiResponse.ofMessage(status, String.valueOf(attr.getOrDefault("message", "error")));
            
            
  
        }
        
        
        

        private int getStatus(HttpServletRequest request) {
            Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
            if (status != null) {
                return status;
            }

            return 500;
        }
        
        
        
       
        
        
        
    }

  

