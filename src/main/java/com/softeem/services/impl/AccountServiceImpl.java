package com.softeem.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.mappers.sys.AccountMapper;
import com.softeem.mappers.sys.UserMapper;
import com.softeem.mappers.sys.UserRoleMapper;
import com.softeem.model.sys.Account;
import com.softeem.model.sys.UserRole;
import com.softeem.services.AccountService;
import com.softeem.services.UserRoleService;
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>  implements AccountService{
	
	@Autowired
	private AccountMapper accountMapper; 

	@Override
	public void updateAccountPhone(Account a) {
		accountMapper.updateAccountPhone(a);
		
	}

	@Override
	public void updateAccountState(Account a) {
		accountMapper.updateAccountState(a);
	}

	@Override
	public List<Account> getAccountList(Account a) {
	
		return accountMapper.getAccountList(a);
	}

}
