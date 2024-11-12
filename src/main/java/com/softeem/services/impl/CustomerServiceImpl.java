package com.softeem.services.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.mappers.release.CustomerMapper;
import com.softeem.model.sys.Customer;
import com.softeem.services.CustomerService;
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper,Customer> implements CustomerService {
}
