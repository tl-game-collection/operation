package com.softeem.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.softeem.mappers.club.ArenaGoldMapper;
import com.softeem.mappers.club.ClubMapper;
import com.softeem.model.club.ArenaGold;
import com.softeem.model.club.Club;
import com.softeem.services.ArenaGoldService;
import com.softeem.services.ClubService;
@Service
public class ArenaGoldServiceImpl implements ArenaGoldService {
	@Autowired
	private ArenaGoldMapper arenaGoldMapper;

	@Override
	public List<ArenaGold> ListArenaGold(ArenaGold a) {
		
		return arenaGoldMapper.ListArenaGold(a);
	}
	
}
