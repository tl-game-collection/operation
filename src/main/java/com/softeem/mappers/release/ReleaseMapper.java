package com.softeem.mappers.release;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softeem.model.release.GameConfigs1;
import com.softeem.model.sys.User;
public interface ReleaseMapper extends BaseMapper<GameConfigs1> {
    GameConfigs1  getGameConfigs1byId(int id);
    
    void  updateGameConfigs1byId(GameConfigs1 id);
    
    
    
   

}
