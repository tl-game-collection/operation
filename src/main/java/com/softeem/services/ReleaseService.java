package com.softeem.services;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.model.release.GameConfigs1;
import com.softeem.model.sys.PLSystemConfigsInfo;


@Transactional
public interface ReleaseService extends IService<GameConfigs1> {
    PLSystemConfigsInfo getGameInfo(int id);
}
