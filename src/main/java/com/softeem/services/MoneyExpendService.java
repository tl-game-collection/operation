package com.softeem.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.softeem.model.room.MoneyExpend;

public interface MoneyExpendService {
	List<MoneyExpend> listMoneyExpend(MoneyExpend m, int pageSize, int pageNum);
	List<MoneyExpend> listMoneyExpendByPlayerUid(MoneyExpend m);
	List<MoneyExpend> listMoneyExpendSum();

}
