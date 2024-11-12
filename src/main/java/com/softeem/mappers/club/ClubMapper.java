package com.softeem.mappers.club;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softeem.model.club.Club;
import com.softeem.model.sys.Customer;
import com.softeem.model.sys.Permission;
import com.softeem.model.sys.Role;
@Mapper
public interface ClubMapper  {
	
	List<Club> ListClub(Club club);

	
}
