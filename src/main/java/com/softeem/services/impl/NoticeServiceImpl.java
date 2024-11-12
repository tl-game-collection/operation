package com.softeem.services.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.mappers.release.NoticeMapper;
import com.softeem.model.sys.Notice;
import com.softeem.services.NoticeService;
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper,Notice> implements NoticeService {
}
