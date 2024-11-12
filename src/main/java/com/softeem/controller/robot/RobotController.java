package com.softeem.controller.robot;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.softeem.config.AppConfig;
import com.softeem.model.room.Player;
import com.softeem.services.PlayerService;
import com.softeem.util.GlobalUtil;
import com.softeem.util.HttpUtil;
import com.softeem.util.MD5Util;
import com.softeem.util.RedisUtil;
import com.softeem.util.ResultData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 机器人
 * 
 * @author lc
 * @date 2020/08/12
 */
@Api("机器人")
@Controller
public class RobotController {
	
	private static Logger LOGGER = Logger.getLogger(RobotController.class);
	
	private Map<String, WebDriver> driverMap = new ConcurrentHashMap<String, WebDriver>();
	
	// D:\lc\下载\\chromedriver_win32\chromedriver.exe
	// /mnt/robot/chromedriver
	private String webDriverAddress = "/mnt/robot/chromedriver";
	//http://localhost:8082
	//http://47.110.12.253:8082
	//http://file.uhqaetu.cn:8082
	private String indexUrl = "http://47.110.12.253:8082";
		
	@Autowired
	private PlayerService playerService;
	
	@ApiOperation("获取机器人名单")
	@GetMapping("api/getRobots")
	@ResponseBody
	public ResultData getRobots(Player p, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Player> playerList = playerService.listRobot(p);
		int count = playerService.listRobot(p).size();
		ResultData result = ResultData.result("200", "成功", playerList, count);
		return result;
	}

	@ApiOperation("更新机器人昵称")
	@PostMapping("api/updRobotName")
	@ResponseBody
	public ResultData updRobotName(@RequestBody Player p) {
		JSONObject json = new JSONObject();
        json.put("playerUid", 0);
        json.put("clubUid", 0);
		json.put("targetPlayerUid", p.getUid().longValue());
		json.put("name", p.getName());
		json.put("icon", "");
	    String sign = MD5Util.getMD5(0, 0, p.getUid().longValue(), p.getName(), "", AppConfig.APP_KEY);
	    json.put("sign", sign);
        String reqString = json.toJSONString();
        String result;
        try {
            result = HttpUtil.sendPost(AppConfig.GAME_API_SET_PLAYER_ICON, reqString);
            Map<String,String> info = (Map<String,String>) JSON.parse(result);
            return  ResultData.success("200",info.get("msg"));
        } catch (IOException e) {
        }
		return ResultData.success();
	}
	
	@ApiOperation("更新机器人头像")
	@RequestMapping(value = "api/updRobotIcon")
	@ResponseBody
	public ResultData updateCover(@RequestParam("image")MultipartFile fileUpload, @RequestParam("playerUid")long playerUid){
		//获取文件名
		String fileName = fileUpload.getOriginalFilename();
        //指定本地文件夹存储图片
        try {
            //将图片保存到static文件夹里
            fileUpload.transferTo(new File("/data/server/img/"+fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        json.put("playerUid", 0);
        json.put("clubUid", 0);
		json.put("targetPlayerUid", playerUid);
		json.put("name", "");
		json.put("icon", fileName);
	    String sign = MD5Util.getMD5(0, 0, playerUid, "", fileName, AppConfig.APP_KEY);
	    json.put("sign", sign);
        String reqString = json.toJSONString();
        String result;
        try {
            result = HttpUtil.sendPost(AppConfig.GAME_API_SET_PLAYER_ICON, reqString);
            Map<String,String> info = (Map<String,String>) JSON.parse(result);
            return  ResultData.success("200",info.get("msg"));
        } catch (IOException e) {
        }
		return ResultData.success();
	}
	
	@ApiOperation("获取机器人所有比赛场俱乐部id")
	@RequestMapping(value = "api/getRobotClubs")
	@ResponseBody
	public ResultData getRobotClubs(String playerUid, int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		String result;
        try {
            result = HttpUtil.sendPost(AppConfig.GAME_API_GET_CLUBS, playerUid);
            JSONObject json = JSONObject.parseObject(result);
            JSONArray array = (JSONArray) json.get("clubs");
            int count = array.size();
            return ResultData.result("200", "成功", array, count);
        } catch (IOException e) {
        }
		return ResultData.success();
	}
	
	@ApiOperation("获取机器人所有比赛场俱乐部id")
	@RequestMapping(value = "api/addRobotClubs")
	@ResponseBody
	public ResultData addRobotClubs(int playerUid,int clubId){
		JSONObject json = new JSONObject();
        json.put("playerUid", playerUid);
        json.put("clubUid", clubId);
	    String sign = MD5Util.getMD5(playerUid, clubId, AppConfig.APP_KEY);
	    json.put("sign", sign);
        String reqString = json.toJSONString();
        try {
        	String result = HttpUtil.sendPost(AppConfig.GAME_API_CLUB_ADDCLUBMEMBER, reqString);
        	Map<String,String> info = (Map<String,String>) JSON.parse(result);
            return  ResultData.success("200",info.get("msg"));
        } catch (IOException e) {
        }
		return ResultData.success();
	}
	
	@ApiOperation("根据群id提出该群机器人")
	@RequestMapping(value = "api/delRobotClubs")
	@ResponseBody
	public ResultData delRobotClubs(int playerUid,String clubIds){
		for(int i=0;i<clubIds.split(",").length;i++) {
			if(clubIds.split(",")[i].equals("")) {
				continue;
			}
			int clubId = Integer.parseInt(clubIds.split(",")[i]);
		    JSONObject json = new JSONObject();
	        json.put("playerUid", playerUid);
	        json.put("clubUid", clubId);
		    String sign = MD5Util.getMD5(playerUid, clubId, AppConfig.APP_KEY);
		    json.put("sign", sign);
	        String reqString = json.toJSONString();
	        try {
	        	String result = HttpUtil.sendPost(AppConfig.GAME_API_DEL_CLUB_ROBOT, reqString);
	        	Map<String,String> info = (Map<String,String>) JSON.parse(result);
	            return  ResultData.success("200",info.get("msg"));
	        } catch (IOException e) {
	        }
		}
		return ResultData.success();
	}
	
	@ApiOperation("获取所有配置的机器人")
	@RequestMapping(value = "api/getRobotList")
	@ResponseBody
	public ResultData getRobotList(int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		
		Set<String> robots = RedisUtil.getInstance().getAllKeys("Robots:");
		JSONArray array = new JSONArray();
		for(String key : robots) {
			long playerUid = 0;
			long clubUid = 0;
			int gold = 0;
			int entranceScore = 0;
			int txt = 0;
			String rules = "";
			String button = "";
			JSONObject js = new JSONObject();
			String val = (String) RedisUtil.getInstance().get(key);
//			if(val.split(";")[8].equals("过期")) {
//				continue;
//			}
			for(int i=0;i<val.split(";")  .length;i++) {
				if(i==0) {
					js.put("playerUid", val.split(";")[0]);
					playerUid = Long.parseLong(val.split(";")[0]);
				}else if(i==1) {
					js.put("clubUid", val.split(";")[1]);
					clubUid = Long.parseLong(val.split(";")[1]);
				}else if(i==2) {
					js.put("roomId", val.split(";")[2]);
				}else if(i==3) {
					js.put("start", val.split(";")[3]);
				}else if(i==4) {
					js.put("end", val.split(";")[4]);
				}else if(i==5) {
					js.put("maxScore", val.split(";")[5]);
				}else if(i==6) {
					js.put("minScore", val.split(";")[6]);
				}else if(i==7) {
					entranceScore = Integer.parseInt(val.split(";")[7]);
					js.put("entranceScore", entranceScore);
				}else if(i==8) {
					button = val.split(";")[8];
					js.put("button", button);
				}else if(i==9) {
					rules = val.split(";")[9];
					js.put("rules", rules);
				}else if(i==10) {
					txt = Integer.parseInt(val.split(";")[10]);
					js.put("txt", txt);
				}else if(i==11) {
					js.put("playerName", val.split(";")[11]);
				}else if(i==12) {
					js.put("clubName", val.split(";")[12]);
				}else if(i==13) {
					js.put("playId", val.split(";")[13]);
				}else if(i==14) {
					gold = Integer.parseInt(val.split(";")[14]);
				}else {
					break;
				}
			}
			if(clubUid>0 && playerUid>0 && gold==0 && !button.equals("过期")) {
				//获取当前竞技分 再根据传入进场分 决定增加分
				try {
					JSONObject js1 = new JSONObject();
					js1.put("clubUid", clubUid);
					js1.put("playerUid", playerUid);
					String sign1 = MD5Util.getMD5(clubUid, playerUid, "#u)%P/Wht$~SQlzcq");// 签名
					js1.put("sign", sign1);
					String str1 = js1.toJSONString();
					//获取当前竞技分
					String result1 = HttpUtil.sendPost(AppConfig.GAME_API_GET_CLUB_MEMBER_GOLD, str1);
					JSONObject json1 = JSONObject.parseObject(result1);
					gold = (int) json1.get("gold");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			js.put("gold", entranceScore-gold/100);
			String roomRules = "";
			if(txt==1) {
				roomRules = "1.下注：牛7下("+rules.split(",")[0]+"%)射门，"
							+"牛8("+rules.split(",")[1]+"%)射门，"
							+"牛9("+rules.split(",")[2]+"%)射门，"
							+"牛牛("+rules.split(",")[3]+"%)射门，"
							+"炸弹牛("+rules.split(",")[4]+"%)射门<br>"
							+"2.手动下庄：当前锅底分数高于首次锅底"
							+"("+rules.split(",")[5]+"倍)时"
							+"("+rules.split(",")[6]+"%)概率下庄，"
							+"每高一倍，提高("+rules.split(",")[7]+"%)下庄概率。"
							+"（未开启手动下庄时，设置不生效）<br>"
							+"3.连庄：("+rules.split(",")[8]+"%)概率连庄";
			}else if(txt==2) {
				roomRules = "1.牛1至牛5牌型：（"+rules.split(",")[0]+"%）"
							+"抢庄最大倍，（"+rules.split(",")[1]+"%）随机抢，下最小注<br>"
							+"2.牛6及以上：("+rules.split(",")[2]+")抢庄，"
							+"牛6("+rules.split(",")[3]+"%)下最大注，"
							+"牛7("+rules.split(",")[4]+"%)下最大注，"
							+"牛8("+rules.split(",")[5]+"%)下最大注，"
							+"牛9("+rules.split(",")[6]+"%)下最大注，"
							+"牛牛("+rules.split(",")[7]+"%)下最大注<br>"
							+"3.炸弹牌型："+rules.split(",")[8]+"%抢庄，"
	    					+rules.split(",")[9]+"%下最大注<br>"
	    					+"4.可推注情况下，牛7("+rules.split(",")[10]+"%)，"
	    					+"牛8("+rules.split(",")[11]+"%)，"
	    					+"牛九("+rules.split(",")[12]+"%)，"
	    					+"牛牛("+rules.split(",")[13]+"%)概率推注，"
	    					+"炸弹("+rules.split(",")[14]+"%)推注";
			}else if(txt==3) {
				roomRules = "1.没王("+rules.split(",")[0]+"%)抢庄，"
							+"("+rules.split(",")[1]+"%)下最大注<br>"
							+"2.满牛("+rules.split(",")[2]+")抢庄，"
							+"("+rules.split(",")[3]+"%)下最大注<br>"
							+"3.不是满牛：("+rules.split(",")[4]+"%)抢庄，"
	    					+"("+rules.split(",")[5]+"%)下最大注<br>"
	    					+"4.炸弹牌型："+rules.split(",")[6]+"%抢庄，"
	    					+rules.split(",")[7]+"%下最大注<br>"
	    					+"5.可推注情况下，牛7("+rules.split(",")[8]+"%)，"
	    					+"牛8("+rules.split(",")[9]+"%)，"
	    					+"牛九("+rules.split(",")[10]+"%)概率推注，"
	    					+"炸弹("+rules.split(",")[11]+"%)推注";
			}else if(txt==4) {
				roomRules = "1.没癞子("+rules.split(",")[0]+"%)抢庄，"
							+"("+rules.split(",")[1]+"%)下最大注<br>"
							+"2.多个癞子：("+rules.split(",")[2]+")抢庄，"
							+"("+rules.split(",")[3]+"%)下最大注<br>"
							+"3.满牛+花牌：("+rules.split(",")[4]+"%)抢庄，"
	    					+"("+rules.split(",")[5]+"%)下最大注<br>"
	    					+"4.不是满牛：("+rules.split(",")[6]+"%)抢庄，"
	    					+"("+rules.split(",")[7]+"%)下最大注<br>"
							+"5.炸弹牌型："+rules.split(",")[8]+"%抢庄，"
	    					+rules.split(",")[9]+"%下最大注<br>"
	    					+"6.可推注情况下，多癞子("+rules.split(",")[10]+"%)，"
	    					+"满牛("+rules.split(",")[11]+"%)，"
	    					+"牛九("+rules.split(",")[12]+"%)概率推注，"
	    					+"炸弹("+rules.split(",")[13]+"%)推注";
			}else if(txt==5) {
				roomRules = "("+rules.split(",")[0]+"%)下最大注，"
							+"("+rules.split(",")[1]+"%)下随机注"
							+"(随机注为"+rules.split(",")[2]+"%的倍数)";
		}
			js.put("roomRules", roomRules);
			array.add(js);
		}
		int count = array.size();
		return  ResultData.result("200", "成功", array, count);
	}
	
	@ApiOperation("获取机器人所有比赛场俱乐部id")
	@RequestMapping(value = "api/getClubList")
	@ResponseBody
	public ResultData getClubList(@RequestParam("playerUid") int playerUid){
		try {
			String result = HttpUtil.sendPost(AppConfig.GAME_API_GET_CLUBS, playerUid+"");
			JSONObject json = JSONObject.parseObject(result);
//			Map<String,String> info = (Map<String,String>) JSON.parse(result);
            return  ResultData.success("200",json.toJSONString());
		} catch (IOException e) {

		}
		return ResultData.success();
	}
	
	@ApiOperation("获取所选比赛场所有玩法")
	@RequestMapping(value = "api/getPlays")
	@ResponseBody
	public ResultData getPlays(@RequestParam("clubUid") int clubUid){
		try {
			String result = HttpUtil.sendPost(AppConfig.GAME_API_GET_PLAYS, clubUid+"");
			return  ResultData.success("200",result);
		} catch (IOException e) {

		}
		return ResultData.success();
	}
	
	@ApiOperation("调起机器人")
	@RequestMapping(value = "api/getRobot")
	@ResponseBody
	public ResultData getRobot(@RequestParam("playerUid") long playerUid
			,@RequestParam("clubUid") long clubUid,@RequestParam("roomId") int roomId
			,@RequestParam("start") String start,@RequestParam("end") String end
			,@RequestParam("maxScore") int maxScore,@RequestParam("minScore") int minScore
			,@RequestParam("entranceScore") int entranceScore,@RequestParam("rules") String rules
			,@RequestParam("txt") int txt,@RequestParam("playerName") String playerName
			,@RequestParam("clubName") String clubName,@RequestParam("playId") int playId)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = null;
        Date endDate = null;
        String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		try {
			startDate = sdf.parse(day+" "+start);
			endDate = sdf.parse(day+" "+end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		long startL = startDate.getTime()/60000;
		long endL = endDate.getTime()/60000;
				
		// 设置执行时间
		Calendar calendar1 = Calendar.getInstance();
		int year1 = calendar1.get(Calendar.YEAR);
		int month1 = calendar1.get(Calendar.MONTH);
		int day1 = calendar1.get(Calendar.DAY_OF_MONTH);// 每天
		// 定制每天的执行时间
		Integer hour1 = Integer.parseInt(start.split(":")[0]);
		Integer minute1 = Integer.parseInt(start.split(":")[1]);
		calendar1.set(year1, month1, day1, hour1, minute1, 0);
		Date date1 = calendar1.getTime();
		Timer timer1 = new Timer();
		
		//获取当前竞技分 再根据传入进场分 决定增加分
		try {
			JSONObject js1 = new JSONObject();
			js1.put("clubUid", clubUid);
			js1.put("playerUid", playerUid);
			String sign1 = MD5Util.getMD5(clubUid, playerUid, "#u)%P/Wht$~SQlzcq");// 签名
			js1.put("sign", sign1);
			String str1 = js1.toJSONString();
			//获取当前竞技分
			String result1 = HttpUtil.sendPost(AppConfig.GAME_API_GET_CLUB_MEMBER_GOLD, str1);
			JSONObject json1 = JSONObject.parseObject(result1);
			int gold = (int) json1.get("gold");
			
			int changGold = entranceScore*100 - gold;
			
			if(changGold!=0) {
				JSONObject js2 = new JSONObject();
				js2.put("clubUid", clubUid);
				js2.put("playerUid", playerUid);
				js2.put("gold", changGold);
				js2.put("type", 0);
				// 签名
				String sign2 = MD5Util.getMD5(clubUid, playerUid, changGold, 0, "#u)%P/Wht$~SQlzcq");
				js2.put("sign", sign2);
				String str2 = js2.toJSONString();
				//增加分{"changGold":100,"msg":"成功","ret":0}
				String result2 = HttpUtil.sendPost(AppConfig.GAME_API_MEMBERGOLD, str2);
				JSONObject json2 = JSONObject.parseObject(result2);
				int changGold2 = (int) json2.get("changGold");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String arg1 = webDriverAddress;
		System.setProperty("webdriver.chrome.driver", arg1);
		String key = playerUid+":"+startL+"."+endL;
		String key1 = "WebDrivers:"+key;
		//定时开启
		TimerTask task1 = new TimerTask() {
			public void run() {
				// 1.创建webdriver驱动
		        ChromeOptions options = new ChromeOptions();
		        //参数是不用打开图形界面
		        options.addArguments("--headless");
		        options.addArguments("--disable-gpu");
		        options.addArguments("--disable-dev-shm-usage");
		        //参数是让Chrome在root权限下跑 添加沙盒模式
		        options.addArguments("--no-sandbox");
//		        options.addArguments("-window-size=1920,1080");
//		        options.addArguments("-start-maximized");
		        WebDriver driver = new ChromeDriver(options);
		        // 2.打开百度首页
		        String url = indexUrl+"/index.html?playerUid="+playerUid+"&clubUid="+clubUid+
		        		"&roomId="+roomId+"&maxScore="+maxScore+"&minScore="+minScore+"&rules="+rules+
		        		"&boxUid="+playId;
		        driver.get(url);
//		        // 3.获取输入框，输入selenium
//		        driver.findElement(By.id("kw")).sendKeys("selenium");
//		        // 4.获取“百度一下”按钮，进行搜索
//		        driver.findElement(By.id("su")).click();
		        driverMap.put(key1, driver);
//		        RedisUtil.getInstance().set(key1, driver);
			}
		};
		//执行一次
		timer1.schedule(task1, date1);
		
		// 设置执行时间
		Calendar calendar2 = Calendar.getInstance();
		int year2 = calendar2.get(Calendar.YEAR);
		int month2 = calendar2.get(Calendar.MONTH);
		int day2 = calendar2.get(Calendar.DAY_OF_MONTH);// 每天
		// 定制每天的执行时间
		Integer hour2 = Integer.parseInt(end.split(":")[0]);
		Integer minute2 = Integer.parseInt(end.split(":")[1]);
		calendar2.set(year2, month2, day2, hour2, minute2, 0);
		Date date2 = calendar2.getTime();
		Timer timer2 = new Timer();
			
		//定时开启
		TimerTask task2 = new TimerTask() {
			public void run() {
				WebDriver driver = driverMap.get(key1);
//				WebDriver driver = (WebDriver)RedisUtil.getInstance().get(key1);
				if(driver!=null) {
					// 5.退出浏览器
					driver.quit();
				}
				String key2 = "Robots:"+key;
		        String val = (String) RedisUtil.getInstance().get(key2);
		        String str = "";
				for(int i=0;i<val.split(";")  .length;i++) {
					if(i==0) {
						str = Long.parseLong(val.split(";")[0])+";";
					}else if(i==1) {
						str = str + Long.parseLong(val.split(";")[1])+";";
					}else if(i==2) {
						str = str + val.split(";")[2]+";";
					}else if(i==3) {
						str = str + val.split(";")[3]+";";
					}else if(i==4) {
						str = str + val.split(";")[4]+";";
					}else if(i==5) {
						str = str + val.split(";")[5]+";";
					}else if(i==6) {
						str = str + val.split(";")[6]+";";
					}else if(i==7) {
						str = str + Integer.parseInt(val.split(";")[7])+";";
					}else if(i==8) {
						str = str + "过期;";
					}else if(i==9) {
						str = str + val.split(";")[9]+";";
					}else if(i==10) {
						str = str + val.split(";")[10]+";";
					}else if(i==11) {
						str = str + val.split(";")[11]+";";
					}else if(i==12) {
						str = str + val.split(";")[12]+";";
					}else if(i==13) {
						str = str + val.split(";")[13]+";";
					}else if(i==14) {
						JSONObject js1 = new JSONObject();
						js1.put("clubUid", clubUid);
						js1.put("playerUid", playerUid);
						String sign1 = MD5Util.getMD5(clubUid, playerUid, "#u)%P/Wht$~SQlzcq");// 签名
						js1.put("sign", sign1);
						String str1 = js1.toJSONString();
						try {
							//获取当前竞技分
							String result1 = HttpUtil.sendPost(AppConfig.GAME_API_GET_CLUB_MEMBER_GOLD, str1);
							JSONObject json1 = JSONObject.parseObject(result1);
							int gold = (int) json1.get("gold");
							str = gold+";";
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else {
						break;
					}
				}
				RedisUtil.getInstance().set(key2, str);
			}
		};
		//执行一次
		timer2.schedule(task2, date2);
				
        //86400秒(24h)后存储失效 /60000和8:00差的分钟<8:00为-
        String key2 = "Robots:"+key;
        String val = playerUid+";"+clubUid+";"+roomId+";"+day+" "+start+";"+day+" "+end+
        		";"+maxScore+";"+minScore+";"+entranceScore+";暂停"+";"+rules+";"+txt+";"+playerName+
        		";"+clubName+";"+playId+";"+0;
        RedisUtil.getInstance().set(key2, val);
        
		return ResultData.success();
	}
	
	@ApiOperation("获取机器人基础赢牌概率和黑名单赢牌概率")
	@RequestMapping(value = "api/getProb")
	@ResponseBody
	public ResultData getProb(){
		JSONObject json = new JSONObject();
        json.put("basis", RedisUtil.getInstance().get("Robots:Prob:basis"));
        json.put("blacklist", RedisUtil.getInstance().get("Robots:Prob:blacklist"));
        return  ResultData.success("200",json.toJSONString());
	}
	
	@ApiOperation("设置机器人基础赢牌概率和黑名单赢牌概率")
	@RequestMapping(value = "api/setProb")
	@ResponseBody
	public ResultData setProb(@RequestParam("basis") int basis,@RequestParam("blacklist") int blacklist){
		RedisUtil.getInstance().set("Robots:Prob:basis", basis);
		RedisUtil.getInstance().set("Robots:Prob:blacklist", blacklist);
		return ResultData.success();
	}
	
}
