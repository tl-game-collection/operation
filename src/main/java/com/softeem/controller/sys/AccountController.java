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
import com.github.pagehelper.PageHelper;
import com.softeem.config.AppConfig;
import com.softeem.model.sys.Account;
import com.softeem.model.sys.BanAccount;
import com.softeem.model.sys.ChangeAccountPhone;
import com.softeem.model.sys.UserRecommend;
import com.softeem.services.AccountService;
import com.softeem.util.HttpUtil;
import com.softeem.util.MD5Util;
import com.softeem.util.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 账号
 * 
 * @author MyPC
 * @date 2020/06/13
 */
@Api("账号")
@Controller
public class AccountController {
	@Autowired
	private AccountService accountService;

	@ApiOperation("获取账号名单")
	@GetMapping("api/getAccounts")
	@ResponseBody
	public ResultData getListAccount(Account w, int pageNum, int pageSize) {
//		HashMap<String, Object> query = new HashMap<>();
//		if (w.getUid() != null) {
//			query.put("uid", w.getUid());
//		}
//		if (w.getState() != null) {
//			query.put("state", w.getState());
//		}
//		
//		List<Account> accountrList = (List<Account>) accountService.listByMap(query);
//		int count = accountService.listByMap(query).size();
//		
		PageHelper.startPage(pageNum, pageSize);
		List<Account> accountrList = accountService.getAccountList(w);
		int count = accountService.getAccountList(w).size();
		
		
		
		ResultData result = ResultData.result("200", "成功", accountrList, count);
		return result;
	}


	@ApiOperation("更新账号")
	@PostMapping("api/updateAccountPhone")
	@ResponseBody
	public ResultData updateAccountPhone(@RequestBody Account a) {
	
		Account  account=accountService.getById(a.getUid());
		if(account.getType()==1) { 
			return ResultData.fail("200","手机号注册的玩家暂时不支持修改手机号");
		}
//数据库操作   accountService.updateAccountPhone(a);
		
		ChangeAccountPhone req=new ChangeAccountPhone(); 
		req.phoneNumber=Long.parseLong(a.getPhone());
		req.playerUid=a.getUid();
		    String sign = MD5Util.getMD5(req.playerUid, req.phoneNumber, AppConfig.APP_KEY);
	        req.sign = sign;
	        String reqString = JSONObject.toJSONString(req);
	        String result;
	        try {
	            result = HttpUtil.sendPost(AppConfig.GAME_API_CHANGE_ACCOUNTPHONE, reqString);
	            Map<String ,String> info= (Map<String, String>) JSON.parse(result);
	            return  ResultData.success("200",info.get("msg"));
	        } catch (IOException e) {
	            
	            
	        }

		return ResultData.success();
	}

	@ApiOperation("更新账号")
	@PostMapping("api/updateAccountState")
	@ResponseBody
	public ResultData updateAccountState(@RequestBody Account a) {
		boolean banFalg=false;
		if(a.getState()==0) {
			banFalg=false;
		}else if(a.getState()==2) {
			banFalg=true;
		}else {
			return ResultData.success("200","您选择有误");
		}
		BanAccount req=new BanAccount();
		req.setPlayerUid(a.getUid());
		req.setBan(banFalg);
		
		 String sign = MD5Util.getMD5(req.getPlayerUid(), req.isBan(), AppConfig.APP_KEY);
	        req.setSign(sign); 
	        String reqString = JSONObject.toJSONString(req);
	        String result;
	        try {
	            result = HttpUtil.sendPost(AppConfig.GAME_API_BANACCOUNT, reqString);
	            Map<String ,String> info= (Map<String, String>) JSON.parse(result);
	            return  ResultData.success("200",info.get("msg"));
	        } catch (IOException e) {
	            
		

		  accountService.updateAccountState(a);
		return ResultData.success();
	 }
	}
	
	
	@ApiOperation("更新用户推荐人")
	@PostMapping("api/updateUserRecommend")
	@ResponseBody
	public ResultData updateUserRecommend(@RequestBody  UserRecommend req) {
		    String sign = MD5Util.getMD5(req.uid, req.targetUid, AppConfig.APP_KEY);
	        req.sign=sign; 
	        String reqString = JSONObject.toJSONString(req);
	        String result;
	        try {
	            result = HttpUtil.sendPost(AppConfig.GAME_API_MODIFY_USERRECOMMEND, reqString);
	            Map<String ,String> info= (Map<String, String>) JSON.parse(result);
	            return  ResultData.success("200",info.get("msg"));
	        } catch (IOException e) {
	            
	
		return ResultData.success();
	 }
	}
	
	

}
