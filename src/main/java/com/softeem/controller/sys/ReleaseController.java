package com.softeem.controller.sys;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.softeem.manager.CacheManager;
import com.softeem.model.release.GameConfigReq;
import com.softeem.model.release.GameConfigs1;
import com.softeem.model.release.White;
import com.softeem.model.sys.PLSystemConfigsInfo;
import com.softeem.services.ReleaseService;
import com.softeem.services.WhiteService;
import com.softeem.util.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 发布
 * @author MyPC
 * @date 2020/06/13
 */
@Api("发布")
@Controller
 public class ReleaseController {
   @Autowired
   private ReleaseService releaseService;
   
   @Autowired
   private WhiteService whiteService;
   @Resource(name="cacheManager")
   private CacheManager cacheManager;
     @ApiOperation("获取系统配置信息")
     @PostMapping("api/getSystemConfigs")
     @ResponseBody
     public PLSystemConfigsInfo  getAppConfig(@RequestBody GameConfigReq req) {
         PLSystemConfigsInfo result=new PLSystemConfigsInfo();
         GameConfigs1 game=new GameConfigs1();
         //game = cacheManager.getById(req.getPhone(),req.getUuid());
        
         if(cacheManager.inWhite(req.getUuid())){
             if(req.getPhone()==1) {
                 game=releaseService.getById(3);//预发布 ios
                
             }else if(req.getPhone()==2) {
                 game=releaseService.getById(4);//预发布安卓
             }
             result.setInWhiteList(true);
    
         }else {
             game=releaseService.getById(req.getPhone());
             result.setInWhiteList(false);
         }
      
         result.gameConfigs.GameDownload=game.getGameDownload();
         result.gameConfigs.LoadImage=game.getLoadImage();
         result.gameConfigs.FilePath=game.getFilePath();
         result.gameConfigs.GameApi=game.getGameApi();
         result.gameConfigs.Statistics=game.getStatistics();
         result.gameConfigs.AgencyApiUrl=game.getAgencyApiUrl();
         result.gameConfigs.AgencyUrl=game.getAgencyUrl();
         result.gameConfigs.VerDataUrl=game.getVerDataUrl();
         result.zipInfo.zipVersion=game.getZipVersion();
         result.zipInfo.zipMd5=game.getZipMd5();
         result.zipInfo.zipSize=game.getZipSize();
         result.zipInfo.resVersion=game.getResVersion();
         result.version=game.getVersion();
         result.plistUrl=game.getPlistUrl();
         result.resourceUrl=game.getResourceUrl();
         result.isInWhiteList=result.getIsInWhiteList();
     return result;
    }
     
     @ApiOperation("获取系统配置信息运营后台使用")
     @PostMapping("api/getSysAppConfigs")
     @ResponseBody
     public ResultData  getAppConfigPage( GameConfigReq req ,int pageSize,  int pageNum) {
    	 List<GameConfigs1> game=releaseService.list();
    	 PageHelper.startPage(pageNum, pageSize);
         ResultData result = ResultData.result("200", "成功", game,
        		 game.size());
     return result ;
    }
 
     
     
     
     @ApiOperation("保存版本信息")
     @PostMapping("api/saveSystemConfigs")
     @ResponseBody
     public ResultData doSaveSystemConfig(@RequestBody GameConfigs1 req) {
         if(req.isInWhiteList()) {
             if(req.getPhone()==1) {
                 req.setId(3);
             }else if(req.getPhone()==2) {
                 req.setId(4);
             }
         }else {
             req.setId(req.getPhone());  
         }
                 releaseService.saveOrUpdate(req);
                 cacheManager.cleanRefush();  
         return ResultData.success();
     }
       
     @ApiOperation("保存白名单")
     @PostMapping("api/saveWhite")
     @ResponseBody
     public ResultData SaveWhite(@RequestBody White w) {
                 whiteService.saveOrUpdate(w);
                 cacheManager.cleanRefush();  
         return ResultData.success();
     }
     
     
   
  
     @ApiOperation("获取名单")
     @GetMapping("api/getWhites")
     @ResponseBody
     public ResultData getListWhite(White w,int pageSize,  int pageNum) {
         HashMap<String, Object> query=new HashMap<>();
            if(StringUtils.isNotBlank(w.getUuid())) {
                query.put("uuid",w.getUuid());
            }    
        Set<White>  white=new HashSet<>();
        List<White> whiteList=(List<White>)whiteService.listByMap(query);
        if(whiteList.size()>0) {
            for(White s:whiteList) {
                white.add(s);       
            } 
        }
        PageHelper.startPage(pageNum, pageSize);
        ResultData result = ResultData.result("200", "成功",white,
            white.size());
         return  result ;
     } 
     
     @ApiOperation("删除白名单")
     @PostMapping("api/deleteWhite")
     @ResponseBody
     public ResultData deleteWhite(White w) {
                 Map<String, Object> re= new HashMap<>();
                 re.put("uuid",w.getUuid());
                 whiteService.removeByMap(re);
                 cacheManager.cleanRefush();  
         return ResultData.success();
     }
     
     
     
     
     @ApiOperation("获取版本信息")
     @GetMapping("api/ListEdition")
     @ResponseBody
     public ResultData getListEdition(White w,int pageSize,  int pageNum) {
        
    	List<GameConfigs1>  GameConfigs1=releaseService.list();
        PageHelper.startPage(pageNum, pageSize);
        ResultData result = ResultData.result("200", "成功",GameConfigs1,
          GameConfigs1.size());
         return  result ;
     } 
     
     
     
     
     
     
     
     
     
     
}
