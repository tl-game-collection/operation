package com.softeem.controller.culb;

import java.io.IOException;
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
import com.softeem.model.club.ArenaGold;
import com.softeem.model.club.ClubModifyMemberGoldReq;
import com.softeem.services.ArenaGoldService;
import com.softeem.util.HttpUtil;
import com.softeem.util.MD5Util;
import com.softeem.util.ResultData;

import io.swagger.annotations.ApiOperation;

@Controller
public class ArenaController {
	
	@Autowired
	private ArenaGoldService arenaGoldService;
	@ApiOperation("修改竞技分")
	@PostMapping("api/updateArenaGold")
	@ResponseBody
	public ResultData updateMemberGold(@RequestBody ClubModifyMemberGoldReq req) {
        	req.gold=req.gold *100;
		    String sign = MD5Util.getMD5(req.clubUid,req.playerUid,req.gold,req.type,AppConfig.APP_KEY);
	        req.sign = sign;
	        String reqString = JSONObject.toJSONString(req);
	        String result;
	        try {
	            result = HttpUtil.sendPost(AppConfig.GAME_API_MEMBERGOLD, reqString);
	            Map<String ,String> info= (Map<String, String>) JSON.parse(result);
	            return  ResultData.success("200",info.get("msg"));
	        } catch (IOException e) {
	            
	        }
		return ResultData.success();
	}
	
	
	
	@ApiOperation("获取竞技分")
	@GetMapping("api/getArenaGold")
	@ResponseBody
	public ResultData getListAccount(ArenaGold a, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ArenaGold> accountrList = arenaGoldService.ListArenaGold(a);
		int count =  arenaGoldService.ListArenaGold(a).size();
		ResultData result = ResultData.result("200", "成功", accountrList, count);
		return result;
	}

	
	
	
	
	

}
