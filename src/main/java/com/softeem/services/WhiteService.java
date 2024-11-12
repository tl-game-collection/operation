package com.softeem.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.model.release.White;
import com.softeem.model.room.Player;
@Transactional
public interface WhiteService extends IService<White> {
}
