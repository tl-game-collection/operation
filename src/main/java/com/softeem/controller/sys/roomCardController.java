package com.softeem.controller.sys;

import java.io.IOException;
import java.math.BigInteger;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.softeem.baseEnum.EMoneyExpendType;
import com.softeem.config.AppConfig;
import com.softeem.config.GameConfig;
import com.softeem.model.room.ClubDissolveRoom;
import com.softeem.model.room.Player;
import com.softeem.model.room.RoomCardReq;
import com.softeem.model.sys.ModifyUserPrivilege;
import com.softeem.services.PlayerService;
import com.softeem.util.HttpUtil;
import com.softeem.util.MD5Util;
import com.softeem.util.ResultData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 房卡
 * 
 * @author MyPC
 * @date 2020/06/13
 */
@Api("房卡")
@Controller
public class roomCardController {
    @Autowired
    private PlayerService playerService;

    @ApiOperation("获取玩家信息")
    @GetMapping("api/getPlayer")
    @ResponseBody
    public ResultData getAppConfig( Player player,  BigInteger uid,  int pageSize,  int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
         ResultData result = ResultData.result("200", "成功", playerService.listPlayer(player),
                playerService.listPlayer(player).size());
        return result;

    }

    @ApiOperation("设置房卡")
    @PostMapping("api/updatePlayer")
    @ResponseBody
    public String doSaveSystemConfig( RoomCardReq req) {
         int money=req.getAmount();
          int falg=0;
         if(money>0) {
             falg=EMoneyExpendType.RUN_DEDUCT.getValue();
         }else {
             falg=EMoneyExpendType.RUN_DONATE.getValue();
             //money=(-money);
         }
         String sign = MD5Util.getMD5( req.getUid(), money, falg, -1, -1, AppConfig.APP_KEY);//签名
         req.setAmount(money);
         req.setType(falg);
         req.setFromUid(-1);
         req.setOperatorUid(-1);
         req.sign=sign;
         Player player=playerService.getById(req.getUid());
         String opmoney=getoPMoney("DIAMOND",req.getAmount());
         String save= getMoney("DIAMOND",player.getMoney(),opmoney);
         player.setMoney(save);
         String str1=JSONObject.toJSONString(req);
         try {
             String result = HttpUtil.sendPost(AppConfig.GAME_API_ROOM, str1);
             playerService.updateById(player);
             return result;
         } catch (IOException e) {
             
         }
        return null;
    }

    @ApiOperation("设置权限")
    @PostMapping("api/updateUserPrivilege")
    @ResponseBody
    public String modifyPrivilege( ModifyUserPrivilege req) {
        String sign = MD5Util.getMD5(req.getUid(), req.getLevel(), AppConfig.APP_KEY);
        req.sign = sign;
        String reqString = JSONObject.toJSONString(req);
        String result;
        try {
            result = HttpUtil.sendPost(AppConfig.GAME_API, reqString);
            return result;
            
            
        } catch (IOException e) {
            
            
        }
        return null;
      
     }
     
     public String getMoney( String args) {
          String result=args.substring(args.lastIndexOf(":")+1,args.lastIndexOf("}"));
         return result;
     }
     
     public  String getMoney( String key, String oldMoney, String opMoney) {
      
          Double onum=Double.parseDouble(getMoney(oldMoney));
          Double opnum=Double.parseDouble(getMoney(opMoney));
         if(opnum<0) {
             if((onum+opnum)<0) {
                 return "error";
             }
         }
        
          Double nomnum=onum+opnum;
          String result="{\""+key+"\":"+nomnum+"}";
         return result;
     }

     public String getoPMoney(String key,int money){

        return "{\""+key+"\":"+money+"}";

     }
     
     
     @ApiOperation("解散房间")
     @PostMapping("api/clubDissolveRoom")
     @ResponseBody
     public String dismissRoom( @RequestBody ClubDissolveRoom req) {
          String sign = MD5Util.getMD5(req.getRoomUid(),  AppConfig.APP_KEY);//签名
          req.sign=sign;
          String str1=JSONObject.toJSONString(req);
          try {
              String result = HttpUtil.sendPost(AppConfig.GAME_API_ROOM_DISSOLVEROOM, str1);
              return result;
          } catch (IOException e) {
              
          }
         return null;
     }

     
     


}
