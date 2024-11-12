package com.softeem.controller.sys;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.softeem.model.sys.Customer;
import com.softeem.services.CustomerService;
import com.softeem.util.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 客服
 * 
 * @author MyPC
 * @date 2020/06/13
 */
@Api("客服")
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customeService;
    @ApiOperation("获取客服名单")
    @GetMapping("api/getCustomers")
    @ResponseBody
    public ResultData getListCustomer(Customer w) {
        HashMap<String, Object> query=new HashMap<>();
        if(w.getId()!=null) {
            query.put("id", w.getId());
        }
        List<Customer> customerList=(List<Customer>) customeService.listByMap(query);
        ResultData result = ResultData.result("200", "成功",customerList, customerList.size());
        return  result ;
    } 
     
    @ApiOperation("保存客服")
    @PostMapping("api/saveCustomer")
    @ResponseBody
    public ResultData SaveWhite(@RequestBody Customer w) {
        w.setCreateTime(new Date());
        customeService.saveOrUpdate(w);
        return ResultData.success();
    }
    
    @ApiOperation("删除客服")
    @PostMapping("api/deleteCustomer")
    @ResponseBody
    public ResultData deleteCustomer(Customer w) {
         
        customeService.removeById(w.getId());
        return ResultData.success();
    }
}
