package com.softeem.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.model.sys.Account;
@Transactional
public interface AccountService extends IService<Account> {
	void updateAccountPhone(Account a);
	void updateAccountState(Account a);
	List<Account> getAccountList(Account a);
	

}
