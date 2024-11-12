package com.softeem.controller.room;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.softeem.model.room.MoneyExpend;
import com.softeem.services.MoneyExpendService;
import com.softeem.util.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 房卡消耗
 * 
 * @author MyPC
 * @date 2020/06/13
 */
@Api("房卡消耗")
@Controller
public class MoneyAddController {
    @Autowired
    private MoneyExpendService customeService;
    @ApiOperation("获取房卡消耗名单")
    @GetMapping("api/getMoneyAdds")
    @ResponseBody
    public ResultData getListMoneyExpend(MoneyExpend m, int pageNum,int pageSize) {
    	int count= customeService.listMoneyExpend(m,pageSize,pageNum).size();
        PageHelper.startPage(pageNum, pageSize);
        List<MoneyExpend> MoneyExpendList1= customeService.listMoneyExpend(m,pageSize,pageNum);
        ResultData result = ResultData.result("200", "成功",MoneyExpendList1,count);
        return  result ;
    } 
    
    
  
     
    
}
