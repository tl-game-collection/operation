 package com.softeem.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.softeem.model.release.GameConfigs1;
import com.softeem.model.release.White;
import com.softeem.services.ReleaseService;
import com.softeem.services.WhiteService;

@Component("cacheManager")
 public class CacheManager {
    @Autowired
    private ReleaseService releaseService;
    
    @Autowired
    private WhiteService whiteService;
    
    private GameConfigs1 cache1;
    private GameConfigs1 cache2;
    private GameConfigs1 cache3;
    private GameConfigs1 cache4;
    
    private ReentrantLock lock1 =new ReentrantLock();
    private ReentrantLock lock2 =new ReentrantLock();
    private ReentrantLock lock3 =new ReentrantLock();
    private ReentrantLock lock4 =new ReentrantLock();
    
    private long lastRefreshTime1 = 0;
    private long lastRefreshTime2 = 0;
    private long lastRefreshTime3 = 0;
    private long lastRefreshTime4 = 0;
    
    
    public boolean inWhite(String uuid) {
        
        QueryWrapper<White> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid",uuid);
                    
        
        int count=whiteService.count(queryWrapper);
        return  count>0;
   
    }
    public GameConfigs1 getById(int id,String uuid) {
        if(id == 1) {
            try {
                if (null == cache1) {
                    lock1.lock();
                    
                    if(inWhite(uuid)) {
                        cache1=releaseService.getById(1);
                        cache1.setInWhiteList(true);
                    }else {
                        cache1=releaseService.getById(1);
                    }
                        
                   
                   
                    lastRefreshTime1 = System.currentTimeMillis()+5*60*1000;
                } else {
                    if(lock1.tryLock(5, TimeUnit.MILLISECONDS)) {
                        if( System.currentTimeMillis()> lastRefreshTime1) {
                            lastRefreshTime1 = System.currentTimeMillis()+5*60*1000;
                            
                            if(inWhite(uuid)) {
                                cache1=releaseService.getById(1);
                                cache1.setInWhiteList(true);
                            }else {
                                cache1=releaseService.getById(1);
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
           } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
            }
            return cache1;
        } else if(id == 2) {
            try {
                if (null == cache2) {
                    lock2.lock();
                    if(inWhite(uuid)) {
                        cache2=releaseService.getById(2);
                        cache2.setInWhiteList(true);
                    }else {
                        cache2=releaseService.getById(2);
                    }
            
                } else {
                    if(lock2.tryLock(5, TimeUnit.MILLISECONDS)) {
                       if( System.currentTimeMillis()> lastRefreshTime2) {
                            lastRefreshTime2 = System.currentTimeMillis()+5*60*1000;
                            if(inWhite(uuid)) {
                                cache2=releaseService.getById(2);
                                cache2.setInWhiteList(true);
                            }else {
                                cache2=releaseService.getById(2);
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
           } finally {
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
            }
            return cache2;
        }else if(id==3) {
            try {
                if (null == cache3) {
                    lock3.lock();
                  
                    
                    if(inWhite(uuid)) {
                        cache3=releaseService.getById(3);
                    }
                    lastRefreshTime3 = System.currentTimeMillis()+5*60*3000;
                } else {
                    if(lock3.tryLock(5, TimeUnit.MILLISECONDS)) {
                        if( System.currentTimeMillis()> lastRefreshTime3) {
                            lastRefreshTime3 = System.currentTimeMillis()+5*60*3000;
                            cache3=releaseService.getById(3);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
           } finally {
                if (lock3.isHeldByCurrentThread()) {
                    lock3.unlock();
                }
            }
            return cache3;
            
            
            
        }else if(id==4) {
            try {
                if (null == cache4) {
                    lock4.lock();
                    
                    if(inWhite(uuid)) {
                        cache3=releaseService.getById(4);
                    }
                  
                    lastRefreshTime4 = System.currentTimeMillis()+5*60*4000;
                } else {
                    if(lock4.tryLock(5, TimeUnit.MILLISECONDS)) {
                        if( System.currentTimeMillis()> lastRefreshTime4) {
                            lastRefreshTime4 = System.currentTimeMillis()+5*60*4000;
                            cache4=releaseService.getById(4);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
           } finally {
                if (lock4.isHeldByCurrentThread()) {
                    lock4.unlock();
                }
            }
            return cache4;
            
            
        }
        return null;
    }
     
    public void cleanRefush() {
        cache1=null;
        cache2=null;
    }
    
    public void cleanRefushPre() {
        cache3=null;
        cache4=null;
    }
     
}
