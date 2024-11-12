package com.softeem.controller.culb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.softeem.model.room.MoneyExpend;
import com.softeem.model.room.ServiceFee;
import com.softeem.services.ServiceFeeService;
import com.softeem.util.ResultData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 服务费统计
 * 
 * @author MyPC
 * @date 2020/06/13
 */
@Api("服务费列表")
@Controller
public class ServiceFeeController {
    @Autowired
    private ServiceFeeService serviceFeeService;
    @ApiOperation("获取服务费列表")
    @GetMapping("api/getServiceFees")
    @ResponseBody
    public ResultData ServiceFee(ServiceFee fee, int pageNum,int pageSize) {
    	int count= serviceFeeService.ListServiceFee(fee).size();
        PageHelper.startPage(pageNum, pageSize);
        List<ServiceFee> MoneyExpendList1=serviceFeeService.ListServiceFee(fee);
        ResultData result = ResultData.result("200", "成功",MoneyExpendList1,count);
        return  result ;
    } 
    
    
    @ApiOperation("获取单个服务费详情")
    @GetMapping("api/getServiceFeeDetil")
    @ResponseBody
    public ResultData ServiceFeeDetil(ServiceFee fee, int pageNum,int pageSize) {
    	int count= serviceFeeService.ListServiceFeeDetil(fee).size();
        PageHelper.startPage(pageNum, pageSize);
        List<ServiceFee> MoneyExpendList1=serviceFeeService.ListServiceFeeDetil(fee);
        ResultData result = ResultData.result("200", "成功",MoneyExpendList1,count);
        return  result ;
    } 
    
    
    
    
  
     
    
}
