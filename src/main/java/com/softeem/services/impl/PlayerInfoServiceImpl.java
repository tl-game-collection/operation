package com.softeem.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.mappers.tj.PlayerInfoMapper;
import com.softeem.mappers.tj.PlayerMapper;
import com.softeem.model.room.Player;
import com.softeem.model.room.PlayerInfo;
import com.softeem.services.PlayerInfoService;
import com.softeem.services.PlayerService;
@Service
public class PlayerInfoServiceImpl  implements PlayerInfoService {
	
	@Autowired
	private PlayerInfoMapper playerInfoMapper;

	@Override
	public List<PlayerInfo> listOnLinePlayerInfo(List<Long> uids) {
		
		return playerInfoMapper.listOnLinePlayerInfo(uids);
	}

	@Override
	public List<PlayerInfo> listRegistPlayer() {
		
		return playerInfoMapper.listRegistPlayer();
	}
}
