package com.softeem.controller.culb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.softeem.config.AppConfig;
import com.softeem.model.club.AddClubGameDesk;
import com.softeem.model.club.GetClubList;
import com.softeem.model.club.GetClubMemberDownLine;
import com.softeem.model.club.GetClubMemberList;
import com.softeem.model.club.GetClubMemberUpLines;
import com.softeem.model.club.UplinePlayer;
import com.softeem.model.room.Player;
import com.softeem.services.PlayerInfoService;
import com.softeem.services.PlayerService;
import com.softeem.util.HttpUtil;
import com.softeem.util.MD5Util;
import com.softeem.util.ResultData;

import io.swagger.annotations.ApiOperation;

@Controller
public class ClubInfoController {
	@Autowired
    private PlayerService playerService;
	@ApiOperation("群信息")
	@GetMapping("api/getListClub")
	@ResponseBody
	public String dismissRoom(GetClubList req) {
		if (req.getClubUid() == null) {
			req.setClubUid(0L);
		}
		req.setPage(req.getPage() - 1);
		String sign = MD5Util.getMD5(req.getPage(), req.getPageSize(), req.getClubUid(), AppConfig.APP_KEY);// 签名
		req.setSign(sign);
		String str1 = JSONObject.toJSONString(req);
		try {
			String result = HttpUtil.sendPost(AppConfig.GAME_API_GET_CLUBLIST, str1);
			return result;
		} catch (IOException e) {

		}
		return null;
	}

	@ApiOperation("当前群成员列表")
	@GetMapping("api/getClubMemberList")
	@ResponseBody
	public String getClubMemberList(GetClubMemberList req) {
		req.setPage(req.getPage() - 1);
		if (req.getSearchUid() == null) {
			req.setSearchUid(0L);
		}
		String sign = MD5Util.getMD5(req.getPlayerUid(), req.getClubUid(), req.getSearchUid(), req.getPage(),
				req.getPageSize(), AppConfig.APP_KEY);// 签名
		req.setSign(sign);
		String str1 = JSONObject.toJSONString(req);
		try {
			String result = HttpUtil.sendPost(AppConfig.GAME_API_GET_CLUBMEMBERLIST, str1);
			return result;
		} catch (IOException e) {

		}
		return null;
	}

	@ApiOperation("当前群的所有群信息")
	@GetMapping("api/getOwnerClubInfoList")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ResultData GetOwnerClubInfoList(GetClubMemberList req) {
		if (req.getSearchUid() == null) {
			req.setSearchUid(0L);
		}
		req.setPage(req.getPage() - 1);
		String sign = MD5Util.getMD5(req.getPlayerUid(), AppConfig.APP_KEY);// 签名
		req.setSign(sign);
		String str1 = JSONObject.toJSONString(req);
		try {
			String result = HttpUtil.sendPost(AppConfig.GAME_API_GET_OWNERCLUBINFO, str1);
			Map<String, Object> map = (Map<String, Object>) JSON.parse(result);
			List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("list");
			List<Map<String, Object>> listserach = new ArrayList<>();
			int pageSerach = req.getPage() * req.getPageSize();
			int pageSerach1 = (req.getPage() + 1) * req.getPageSize();
			for (int i = pageSerach; i < pageSerach1; i++) {
				if (i >= list.size()) {
					break;
				}
				listserach.add(list.get(i));
			}
			return ResultData.result("200", "成功", listserach, list.size());
		} catch (IOException e) {
		}
		return ResultData.success();
	}

	@ApiOperation("俱乐部成员下线")
	@GetMapping("api/getClubMemberDownLine")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ResultData getClubMemberDownLine(GetClubMemberDownLine req) {
		req.setPage(req.getPage() - 1);
		String sign = MD5Util.getMD5(req.getClubUid(), req.getManagerUid(), AppConfig.APP_KEY);// 签名
		req.setSign(sign);
		String str1 = JSONObject.toJSONString(req);
		try {
			String result = HttpUtil.sendPost(AppConfig.GAME_API_GET_CLUBMEMBERDOWNLINE, str1);
			Map<String, Object> map = (Map<String, Object>) JSON.parse(result);
			List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("data");
			List<Map<String, Object>> listserach = new ArrayList<>();
			int pageSerach = req.getPage() * req.getPageSize();
			int pageSerach1 = (req.getPage() + 1) * req.getPageSize();
			for (int i = pageSerach; i < pageSerach1; i++) {
				if (i >= list.size()) {
					break;
				}
				listserach.add(list.get(i));
			}
			return ResultData.result("200", "成功", listserach, list.size());
		} catch (IOException e) {

		}
		return ResultData.success();
	}

	@ApiOperation("俱乐部成员上线")
	@GetMapping("api/getClubMemberUpLines")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ResultData getClubMemberUpLines(GetClubMemberUpLines req) {
		req.setPage(req.getPage() - 1);
		String sign = MD5Util.getMD5(req.getClubUid(), req.getPlayerUid(), AppConfig.APP_KEY);// 签名
		req.setSign(sign);
		String str1 = JSONObject.toJSONString(req);
		try {
			String result = HttpUtil.sendPost(AppConfig.GAME_API_GET_CLUBMEMBERUPLINES, str1);
			Map<String, Object> map = (Map<String, Object>) JSON.parse(result);
			List<Long> list = (List<Long>) map.get("upLines");
			String playerUid= map.get("playerUid").toString();
			String clubUid= map.get("clubUid").toString();	
			List<Object> listserach = new ArrayList<>();
			int pageSerach = req.getPage() * req.getPageSize();
			int pageSerach1 = (req.getPage() + 1) * req.getPageSize();
			  for(int i =pageSerach ; i <pageSerach1; i++) { 
				  if(i>=list.size()) {
					 break; 
				  }
			  listserach.add(list.get(i));
			  }
			  List<Player> player;
			  if(list.isEmpty()) {
				  player=new ArrayList<>();
			  }else {
				   player= (List<Player>) playerService.listByIds(list);
			  }
			  List<UplinePlayer> ups=new ArrayList<>();
			  UplinePlayer up=new UplinePlayer();
			  if(player.size()>0) {
				  for(Player p:player) {
					  up.setClubUid(Long.valueOf(clubUid));
					  up.setPlayerUid(Long.valueOf(playerUid));
					  up.setUpLinePlayerUid(p.getUid().longValue());
					  up.setUpLinePlayerUidName(p.getName());
					  ups.add(up);
				  }
			  }
			return ResultData.result("200", "成功", ups, list.size());
		} catch (IOException e) {

		}
		return ResultData.success();
	}

}
