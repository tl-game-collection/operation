package com.softeem.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softeem.mappers.club.ClubMapper;
import com.softeem.model.club.Club;
import com.softeem.services.ClubService;
@Service
public class ClubServiceImpl implements ClubService {
	@Autowired
	private ClubMapper clubMapper;

	@Override
	public List<Club> ListClub(Club club) {
		return  clubMapper.ListClub(club);
	}
}
