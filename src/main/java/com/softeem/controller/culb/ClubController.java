package com.softeem.controller.culb;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
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
import com.github.pagehelper.PageHelper;
import com.softeem.config.AppConfig;
import com.softeem.model.club.AddClubGameDesk;
import com.softeem.model.club.Club;
import com.softeem.model.club.ClubRobotNum;
import com.softeem.model.club.GetClubRobotNumResp;
import com.softeem.model.room.ClubDissolveRoom;
import com.softeem.model.room.Player;
import com.softeem.model.sys.Customer;
import com.softeem.services.ClubService;
import com.softeem.services.PlayerService;
import com.softeem.util.HttpUtil;
import com.softeem.util.MD5Util;
import com.softeem.util.ResultData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("俱乐部")
@Controller
public class ClubController {
	private static final Long  ROBOTUID=30000L;//机器人Uid
	@Autowired
	private ClubService clubService;
	@Autowired
	private PlayerService playerService;
	 @ApiOperation("添加玩法桌")
     @PostMapping("api/addClubGameDesk")
     @ResponseBody
     public String dismissRoom( @RequestBody AddClubGameDesk req) {
          String sign = MD5Util.getMD5(req.getPlayerUid(),req.getClubUid(),req.getFloorUid(),req.getType(),req.getRobotDeskMin(),req.getRobotDeskMax(),req.getRandomTime(),  AppConfig.APP_KEY);//签名
          req.sign=sign;
          String str1=JSONObject.toJSONString(req);
          try {
              String result = HttpUtil.sendPost(AppConfig.GAME_API_CLUB_AddGameDesk, str1);
              return result;
          } catch (IOException e) {
              
          }
         return null;
     }
	 
	 
	 @ApiOperation("获取俱乐部")
	 @GetMapping("api/getClubs")
     @ResponseBody
     public ResultData listClub( Club req ,int pageNum,int pageSize) {
		    PageHelper.startPage(pageNum, pageSize);
		    List<Club>  club=clubService.ListClub(req);
		    List<Club>  clubRobet=new ArrayList<>();
		    if(!club.isEmpty()) {
		    	 for(Club b:club) {
				    	b.setCurRobotDesk2((int)getClubRobotNum(b).get("curRobotDesk2"));       ;
				        b.setCurRobotDesk3((int)getClubRobotNum(b).get("curRobotDesk3"));
			            b.setCurRobotDesk4((int)getClubRobotNum(b).get("curRobotDesk4"));
				    	getClubRobotNum(b);
				        clubRobet.add(b);
				    }
		    }else {
		    	clubRobet=club;
		    }
	        ResultData result = ResultData.result("200", "成功",clubRobet, clubRobet.size());
	        return  result ;
     }
	 
	 
	 
	 /**
	  * 获取机器人数量
	  * @param req
	  * @return
	  */
     public  Map<String ,Object> getClubRobotNum( Club req) {
          String sign = MD5Util.getMD5(req.getClubUid(),req.getFloorUid(), AppConfig.APP_KEY);//签名
          req.sign=sign;
          String str1=JSONObject.toJSONString(req);
          try {
              String result = HttpUtil.sendPost(AppConfig.GAME_API_CLUB_ROBOT_NUM,str1);
              Map<String ,Object> resp= (Map<String ,Object>) JSON.parse(result);
              return resp;
          } catch (IOException e) {
          }
         return null;
     }
	 
	 
	 @ApiOperation("添加俱乐部成员")
     @PostMapping("api/addClubMember")
     @ResponseBody
     public String addClubMember( @RequestBody AddClubGameDesk req) {
          String sign = MD5Util.getMD5(req.getPlayerUid(),req.getClubUid(), AppConfig.APP_KEY);//签名
          req.sign=sign;
          String str1=JSONObject.toJSONString(req);
          try {
              String result = HttpUtil.sendPost(AppConfig.GAME_API_CLUB_ADDCLUBMEMBER,str1);
              return result;
          } catch (IOException e) {
              
          }
         return null;
     }
	 
	 @ApiOperation("获取机器人")
	 @GetMapping("api/getRobets")
     @ResponseBody
     public ResultData listClub(Player player,int pageNum,int pageSize) {
		    PageHelper.startPage(pageNum, pageSize);
		    player.setUid(BigInteger.valueOf(ROBOTUID));
	        ResultData result = ResultData.result("200", "成功",playerService.listRobotPlayer(player), playerService.listRobotPlayer(player).size());
	        return  result ;
     }
}
