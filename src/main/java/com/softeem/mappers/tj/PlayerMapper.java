package com.softeem.mappers.tj;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softeem.model.room.Player;

public interface PlayerMapper extends BaseMapper<Player> {

    List<Player>  listPlayer(Player player);
    
    List<Player>  listRobotPlayer(Player player);
    
    List<Player>  listRobot(Player player);
    
}
