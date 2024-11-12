package com.softeem.mappers.sys;

import java.util.List;

import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softeem.model.sys.Account;
import com.softeem.model.sys.UserRole;

public interface AccountMapper extends BaseMapper<Account> {
	void updateAccountPhone(Account a);
	void updateAccountState(Account a);
	
	List<Account> getAccountList(Account a);
	

}
