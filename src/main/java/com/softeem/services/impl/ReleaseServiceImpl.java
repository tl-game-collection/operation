package com.softeem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.mappers.release.ReleaseMapper;
import com.softeem.model.release.GameConfigs1;
import com.softeem.model.sys.PLSystemConfigsInfo;
import com.softeem.services.ReleaseService;

import net.bytebuddy.jar.asm.commons.Remapper;
@Service
public class ReleaseServiceImpl extends ServiceImpl<ReleaseMapper,GameConfigs1> implements ReleaseService {
    @Autowired
    private ReleaseMapper releaseMapper;

    @Override
    public PLSystemConfigsInfo getGameInfo(int id) {
        // TODO Auto-generated method stub
         return null;
    }


}
