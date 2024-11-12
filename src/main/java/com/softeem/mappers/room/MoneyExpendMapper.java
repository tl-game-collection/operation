package com.softeem.mappers.room;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.softeem.model.room.MoneyExpend;

public interface MoneyExpendMapper {
	List<MoneyExpend> listMoneyExpend(MoneyExpend m ,@Param("pageSize") int pageSize, @Param("pageNum") int pageNum);
	List<MoneyExpend> listMoneyExpendByPlayerUid(MoneyExpend m);
	List<MoneyExpend> listMoneyExpendSum();
	
	List<MoneyExpend> listMoneyAdd(MoneyExpend m);
	
}
