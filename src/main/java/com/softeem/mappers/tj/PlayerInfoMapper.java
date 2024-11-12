package com.softeem.mappers.tj;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softeem.model.room.Player;
import com.softeem.model.room.PlayerInfo;

public interface PlayerInfoMapper {
	
	List<PlayerInfo> listOnLinePlayerInfo(@Param("uids") List<Long> uids);
	
	List<PlayerInfo> listRegistPlayer();
	

  

}
