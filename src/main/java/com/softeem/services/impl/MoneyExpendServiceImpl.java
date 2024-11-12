package com.softeem.services.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softeem.mappers.room.MoneyExpendMapper;
import com.softeem.model.room.MoneyExpend;
import com.softeem.services.MoneyExpendService;

@Service
public class MoneyExpendServiceImpl implements MoneyExpendService  {
	@Autowired
	private MoneyExpendMapper moneyExpendMapper;

	@Override
	public List<MoneyExpend> listMoneyExpend(MoneyExpend m , int pageSize,int pageNum) {
		
		return moneyExpendMapper.listMoneyExpend(m, pageSize, pageNum);
	}

	@Override
	public List<MoneyExpend> listMoneyExpendByPlayerUid(MoneyExpend m) {
	
		return moneyExpendMapper.listMoneyExpendByPlayerUid(m);
	}

	@Override
	public List<MoneyExpend> listMoneyExpendSum() {
	
		return moneyExpendMapper.listMoneyExpendSum();
	}

}
