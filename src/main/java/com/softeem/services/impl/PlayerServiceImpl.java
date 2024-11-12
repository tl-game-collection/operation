package com.softeem.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.mappers.tj.PlayerMapper;
import com.softeem.model.room.Player;
import com.softeem.services.PlayerService;
@Service
public class PlayerServiceImpl extends ServiceImpl<PlayerMapper,Player> implements PlayerService {
@Autowired
private PlayerMapper playerMapper;
    @Override
    public List<Player> listPlayer(Player player) {
         return playerMapper.listPlayer(player);
    }
	@Override
	public List<Player> listRobotPlayer(Player player) {
		
		return playerMapper.listRobotPlayer(player);
	}
	@Override
	public List<Player> listRobot(Player player) {
		
		return playerMapper.listRobot(player);
	}
}
