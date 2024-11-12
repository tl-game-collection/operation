package com.softeem.controller.tj;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.softeem.config.AppConfig;
import com.softeem.model.room.Player;
import com.softeem.model.room.PlayerInfo;
import com.softeem.model.room.ServiceFee;
import com.softeem.services.PlayerInfoService;
import com.softeem.services.PlayerService;
import com.softeem.util.HttpUtil;
import com.softeem.util.ResultData;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HonePage {
	@Autowired
	private PlayerInfoService playerService;
	    @ApiOperation("首页")
	    @GetMapping("/api/getHomePage")
	    @ResponseBody
	    public Map<String,Object> getPage() {
	    	int onlineNum=4567;
	    	int registNum=7890;
	    	Map<String,Object> result=new HashedMap();
	    	result.put("onlineNum",onlineNum);
	    	result.put("registNum",registNum);
	        return result;
	    }
	    
	    @ApiOperation("获取在线玩家")
	    @GetMapping("/api/getPlayerNum")
	    @ResponseBody
	    public Map<String,Object> getOnLinePlayer() {
	    	int onlineNum=getOnlineNum();
	    	int registNum=playerService.listRegistPlayer().size();
	    	Map<String,Object> result=new HashedMap();
	    	result.put("onlineNum",onlineNum);
	    	result.put("registNum",registNum);
	        return result;
	    }

	    @ApiOperation("分页查询")
	    @GetMapping("/api/getRegistPlayer")
	    @ResponseBody
	    public ResultData getPage( int pageNum, int pageSize) {
		    int count=  playerService.listRegistPlayer().size();
	        PageHelper.startPage(pageNum,pageSize);
	        List<PlayerInfo> MoneyExpendList1=playerService.listRegistPlayer();
	        ResultData result = ResultData.result("200", "成功",MoneyExpendList1,count);
	        return result;
	    }
	    
	    @ApiOperation("分页查询")
	    @GetMapping("/api/getOnLinePlayer")
	    @ResponseBody
	    public ResultData getOnLinePlayer(int pageNum, int pageSize) {
	    	List<Long> uids=getOnlineUid();
	    	if(uids.size()==0) {
	    		return ResultData.result("200", "成功",null,0);
	    	}
		    int count=  playerService.listOnLinePlayerInfo(uids).size();
	        PageHelper.startPage(pageNum,pageSize);
	        List<PlayerInfo> MoneyExpendList1=playerService.listOnLinePlayerInfo( uids);
	        ResultData result = ResultData.result("200", "成功",MoneyExpendList1,count);
	        return result;
	    }
	    
	    //在线玩家uid
	    public List<Long> getOnlineUid(){
	        try {
	            String result = HttpUtil.sendPost(AppConfig.GAME_API_GET_ONLINEPLAYERUIDS,"");
	            List<Long> re=(List<Long>) JSON.parse(result);
	            return re;
	        } catch (IOException e) {
	            
	        }
	        return null;
	    }
	    
        //在线玩家数量
	    public int  getOnlineNum(){
	        try {
	            String result = HttpUtil.sendPost(AppConfig.GAME_API_GET_ONLINECOUNT,"");
	            Map<String,Object> re=(Map<String,Object>) JSON.parse(result);
	            Map<String,Object> data= (Map<String, Object>) re.get("data");
	            int res=(int) data.get("onlineNum");  
	            return res;
	        } catch (IOException e) {
	            
	        }
	        return 0;
	    }
	    
	    
	    
	  
	    
}
