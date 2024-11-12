package com.softeem.controller.sys;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resources;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.google.common.base.MoreObjects;
import com.softeem.app.a;
import com.softeem.config.AppConfig;
import com.softeem.config.OrcodeNtf;
import com.softeem.model.sys.ORcodeReq;
import com.softeem.model.sys.User;
import com.softeem.util.FileUtils;
import com.softeem.util.GlobalUtil;
import com.softeem.util.QRCodeUtil;
import com.softeem.util.StringUtil;

@Controller

public class FileController {

    

    /**
     * 二维码
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/generateQRCode")
    @ResponseBody
    public OrcodeNtf generateQRCode(@RequestBody ORcodeReq f ,HttpServletResponse response)
            throws Exception {
            String uid=f.getUid();
            String gid=f.getGid();
            String bizChannel=f.getBizChannel();
            String param = "?uid=" + uid + "&" + "gid=" + gid ;
            String fileName =uid+gid+bizChannel+".png";
            String context = AppConfig.PATH + param;//二维码请求地址 下载地址
            String file=GlobalUtil.getValue("orcode.filepath")+fileName;
            QRCodeUtil.encode(context,file);
            OrcodeNtf result=new OrcodeNtf();
            String resutlPath=AppConfig.codePath+"orcode/"+fileName;//二维码访问
            result.data.image=resutlPath;
            return result;
    }
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    
  
    
    
    
  

    
    
    
  


    public static URL getResource(String resourceName) {
        ClassLoader loader = MoreObjects.firstNonNull(
         Thread.currentThread().getContextClassLoader(),
         Resources.class.getClassLoader());
         URL url = loader.getResource(resourceName);
        return url;
       }

       public static void main(String[] args) {
        System.out.println(getResource("static"));
        
       }





   
       

          
      
    

 
	

	
	/**
	 * 处理文件下载
	 * @param request
	 * @param response
	 * @param fileName
	 */
	@RequestMapping(value = "/downLoad")
	@ResponseBody
	public void downLoadList(HttpServletRequest request, HttpServletResponse response,
			String fileName){
	        downloadFile(new File(GlobalUtil.getValue("upload"),fileName), response, false);
	}
	
	/**
	 * 文件下载
	 * @param file
	 * @param response
	 * @param isDelete
	 */
	public void downloadFile(File file,HttpServletResponse response,boolean isDelete) {
        try {
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("UTF-8"),"ISO-8859-1"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            if(isDelete)
            {
                file.delete();        //是否将生成的服务器端文件删除
            }
         } 
         catch (IOException ex) {
            ex.printStackTrace();
        }
    } 
}
