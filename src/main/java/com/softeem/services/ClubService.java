package com.softeem.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.model.club.Club;
import com.softeem.model.sys.Customer;
@Transactional
public interface ClubService  {
	List<Club> ListClub(Club club);
}
