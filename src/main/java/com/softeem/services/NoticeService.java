package com.softeem.services;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.model.sys.Notice;
@Transactional
public interface NoticeService extends IService<Notice> {
}
