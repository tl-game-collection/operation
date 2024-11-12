package com.softeem.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.model.room.Player;
import com.softeem.model.room.PlayerInfo;
@Transactional
public interface PlayerInfoService  {

	List<PlayerInfo> listOnLinePlayerInfo(List<Long> uids);
	
	List<PlayerInfo> listRegistPlayer();

      
}
