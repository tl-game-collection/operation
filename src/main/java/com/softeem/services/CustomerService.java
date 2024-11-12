package com.softeem.services;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.model.sys.Customer;
@Transactional
public interface CustomerService extends IService<Customer> {
}
