package com.softeem.controller.sys;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.softeem.config.AppConfig;
import com.softeem.model.game.Announcement;
import com.softeem.model.sys.Notice;
import com.softeem.services.NoticeService;
import com.softeem.util.HttpUtil;
import com.softeem.util.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 公告
 * 
 * @author MyPC
 * @date 2020/06/13
 */
@Api("公告")
@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @ApiOperation("获取公告名单")
    @GetMapping("api/getNotices")
    @ResponseBody
    public ResultData getListNotice(Notice w) {
        HashMap<String, Object> query=new HashMap<>();
        if(w.getId()!=null) {
            query.put("type", w.getType());
        }
        List<Notice> noticerList=(List<Notice>) noticeService.listByMap(query);
        ResultData result = ResultData.result("200", "成功",noticerList, noticerList.size());
        return  result ;
    } 
     
    @ApiOperation("保存公告")
    @PostMapping("api/saveNotice")
    @ResponseBody
    public ResultData SaveWhite(@RequestBody Notice w) {
        noticeService.saveOrUpdate(w);
        return ResultData.success();
    }
    
    @ApiOperation("删除公告")
    @PostMapping("api/deleteNotice")
    @ResponseBody
    public ResultData deleteNotice(Notice w) {
         
        noticeService.removeById(w.getId());
        return ResultData.success();
    }
    

    
    @ApiOperation("发布游戏公告跑马灯")
    @PostMapping("api/postAnnouncement")
    @ResponseBody
    public ResultData postAnnouncement(@RequestBody Announcement w) {
    	
    	String reqString = JSONObject.toJSONString(w);
    	String result;
		try {
			result = HttpUtil.sendPost(AppConfig.GAME_API_POSTANNOUNCEMENT, reqString);
			 Map<String ,String> info= (Map<String, String>) JSON.parse(result);
	         return  ResultData.success("200",info.get("msg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResultData.fail("200","发布公告失败");
        
   
    }
    
    
    @ApiOperation("获取游戏公告跑马灯")
    @PostMapping("api/getAnnouncements")
    @ResponseBody
    public ResultData getAnnouncements() {
    	try {
			 String result = HttpUtil.sendPost(AppConfig.GAME_API_GETANNOUNCEMENTS,"");
			 Map<String ,String> info= (Map<String, String>) JSON.parse(result);
	         return  ResultData.success("200",info.get("msg"),info.get("entities"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResultData.fail("200","获取公告失败");
        
   
    }
    
    
    @ApiOperation("撤销游戏公告跑马灯")
    @PostMapping("api/clearAnnouncements")
    @ResponseBody
    public ResultData clearAnnouncements() {
		try {
			 String result = HttpUtil.sendPost(AppConfig.GAME_API_CLEARANNOUNCEMENTS,"");
			 Map<String ,String> info= (Map<String, String>) JSON.parse(result);
	         return  ResultData.success("200",info.get("msg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResultData.fail("200","撤销公告失败");
      
      
    }
    
    
    
    
}
