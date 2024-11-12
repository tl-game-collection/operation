package com.softeem.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.model.room.Player;
@Transactional
public interface PlayerService extends IService<Player> {
    List<Player> listPlayer(Player player);
    
    List<Player> listRobotPlayer(Player player);
    
    List<Player> listRobot(Player player);
}
