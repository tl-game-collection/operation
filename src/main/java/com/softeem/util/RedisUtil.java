package com.softeem.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 
 * @author jiangkefa
 * 基于spring和redis的redisTemplate工具类
 * 针对所有的hash 都是以h开头的方法
 * 针对所有的Set 都是以s开头的方法                    不含通用方法
 * 针对所有的List 都是以l开头的方法
 */
public class RedisUtil{

	/**
	 * redis操作模板
	 */
	private RedisTemplate<String, Object> redisTemplate;
	
	public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		StringRedisSerializer stringSerializer = new StringRedisSerializer();
		GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
		this.redisTemplate.setKeySerializer(stringSerializer);
		this.redisTemplate.setHashKeySerializer(stringSerializer);
		this.redisTemplate.setValueSerializer(jsonRedisSerializer);
		this.redisTemplate.setHashValueSerializer(jsonRedisSerializer);
	}
	
	//=============================公共api============================
    /**
     * 指定缓存的失效时间
     * @param key redisKey
     * @param timeout 失效时间
     * @return
     */
    public boolean expire(String key,long timeout){ 
    	return timeout > 0 ? redisTemplate.expire(key, timeout, TimeUnit.SECONDS) : false;
    }  

    /**
     * 获得失效时间 
     * @param key redisKey
     * @return
     */
    public long getExpire(String key){  
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);  
    }  

    /**
     * 判断key是否存在
     * @param key redisKey
     * @return
     */
    public boolean hasKey(String key){  
    	return redisTemplate.hasKey(key);  
    }  
    
    /**
     * 删除单个缓存
     * @param key redisKey
     */
    public void del(String key){
    	if(key == null || key.isEmpty()) return;
    	redisTemplate.delete(key);
    }
    
    /**
     * 批量删除redis缓存
     * @param keys redisKey列表
     */
    public void batchDel(List<String> keys){
    	if(keys.size() == 0)return;
    	redisTemplate.delete(keys); 
    }
    
    @SuppressWarnings("unchecked")
	public void batchDel(String[] keys){
    	batchDel(CollectionUtils.arrayToList(keys));
    }
    
    /**
	 * 模糊匹配keys
	 * @param prefix 需要查询的key
	 * @return
	 */
	public Set<String> getAllKeys(String prefix){
		Set<String> ob = redisTemplate.keys(prefix + "*");
		return ob;
	}
    /**
     * 查看redis数据数量
     * @return
     */
	public long dbSize(){
        return (long) redisTemplate.execute(new RedisCallback<Object>(){
            public Long doInRedis(RedisConnection connection) throws DataAccessException{
                return connection.dbSize();
            }
        });
    }

    //============================操作字符串api=============================  
    /**
     * 获取普通数据
     * @param key redisKey
     * @return
     */
    public Object get(String key){
    	return key == null ? null : redisTemplate.opsForValue().get(key);
    }  

    /**
     * 这是普通数据
     * @param key redisKey
     * @param value 值
     * @return
     */
    public void set(String key,Object value) {
    	redisTemplate.opsForValue().set(key, value);  
    }
    
    /**
     * 设置普通数据（设置过期时间） 
     * @param key redisKey
     * @param value 值
     * @param timeout 过期时间
     * @return
     */
    public void set(String key, Object value, long timeout){  
    	//判断是否有过期时间
    	if(timeout > 0){
    		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);  
    	}else{  
    		set(key, value);  
    	}
    }  

    /** 
     * 递增 
     * @param key 键 
     * @param by 要增加几(大于0) 
     * @return 
     */  
    public long incr(String key, long delta){
        if(delta < 0) {  
            throw new RuntimeException("delta参数必须大于0");  
        }
        return redisTemplate.opsForValue().increment(key, delta);  
    }

    /** 
     * 递减 
     * @param key 键 
     * @param by 要减少几(小于0) 
     * @return 
     */  
    public long decr(String key, long delta){    
        if(delta < 0){  
            throw new RuntimeException("delta参数必须大于0");  
        }  
        return redisTemplate.opsForValue().increment(key, -delta);    
    }    

    //================================操作Map api=================================  
    /**
     * 获取hash表的单个值  
     * @param key redisKey
     * @param mapKey map的key
     * @return
     */
    public Object hashGet(String key,String mapKey){  
        return redisTemplate.opsForHash().get(key, mapKey);  
    }  

    /**
     * 获取hash表的所有数据
     * @param key redisKey
     * @return
     */
    public Map<Object,Object> hashGetAll(String key){  
        return redisTemplate.opsForHash().entries(key);  
    }  

    /**
     * 批量添加hash表的值
     * @param key redisKey
     * @param map Map数据
     * @return
     */
    public void hashBatchInsert(String key, Map<String,Object> map){    
    	redisTemplate.opsForHash().putAll(key, map);  
    }  

    /**
     * 批量添加hash表的值(设置过期时间)
     * @param key redisKey
     * @param map Map值
     * @param timeout 过期时间
     * @return
     */
    public void hashBatchInsert(String key, Map<String,Object> map, long timeout){    
    	redisTemplate.opsForHash().putAll(key, map);
    }  

    /**
     * 设置单个hash表的值
     * @param key redisKey
     * @param mapKey Map的key
     * @param value Map的value
     * @return
     */
    public void hashInsert(String key, String mapKey, Object value) {  
    	redisTemplate.opsForHash().put(key, mapKey, value);  
    }  

    /**
     * 设置单个hash表的值(设置过期时间)
     * @param key redisKey
     * @param mapKey Map的key
     * @param value Map的value
     * @param timeout 过期时间
     * @return
     */
    public void hashInsert(String key, String mapKey, Object value, long timeout) {  
    	redisTemplate.opsForHash().put(key, mapKey, value); 
    	//设置过期时间
    	if(timeout > 0)
    		expire(key, timeout);  
    }  

    /** 
     * 删除hash表中的值 
     * @param key redisKey 
     * @param mapKeys map的key，可以使多个，不能为null 
     */  
    public void hashDel(String key, Object... mapKeys){    
        redisTemplate.opsForHash().delete(key,mapKeys);  
    }   

    /** 
     * 判断hash表中是否有该项的值 
     * @param key redisKey 不能为null 
     * @param mapKey 项 不能为null 
     * @return true 存在 false不存在 
     */  
    public boolean hashHasKey(String key, String mapKey){  
        return redisTemplate.opsForHash().hasKey(key, mapKey);  
    }   

    /** 
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回 
     * @param key redisKey
     * @param mapKey map的key 
     * @param by 要增加几(大于0) 
     * @return 
     */  
    public double hashIncr(String key, String mapKey, double by){    
        return redisTemplate.opsForHash().increment(key, mapKey, by);  
    }  

    /** 
     * hash递减 
     * @param key redisKey
     * @param mapKey map的key
     * @param by 要减少记(小于0) 
     * @return 
     */  
    public double hashDecr(String key, String mapKey, double by){    
        return redisTemplate.opsForHash().increment(key, mapKey, -by);    
    }
    
    public void flushDB(){
    	redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return null;
			}
		});
    }

    //============================操作set api=============================  
    /** 
     * 根据key获取Set中的所有值 
     * @param key redisKey 
     * @return 
     */  
    public Set<Object> setGetAll(String key){  
    	return redisTemplate.opsForSet().members(key);  
    }

    /** 
     * 根据value从一个set中查询,是否存在 
     * @param key redisKey
     * @param value 值
     * @return true 存在 false不存在 
     */  
    public boolean setHasKey(String key,Object value){
    	return redisTemplate.opsForSet().isMember(key, value);  
    }  

    /** 
     * 将数据放入set缓存 
     * @param key redisKey
     * @param values set值 可以是多个 
     * @return 成功个数 
     */  
    public long setBatchInsert(String key, Object...values) {
    	return redisTemplate.opsForSet().add(key, values);  
    }  

    /** 
     * 将set数据放入缓存 
     * @param key redisKey
     * @param values set值 可以是多个 
     * @param time 时间(秒) 
     * @return 成功个数 
     */  
    public long setBatchInsert(String key, long timeout, Object...values) {  
    	Long count = redisTemplate.opsForSet().add(key, values);  
    	if(timeout > 0) 
    		expire(key, timeout);
    	return count == null ? 0 : count;
    }  

    /** 
     * 获取set缓存的长度 
     * @param key redisKey 
     * @return 
     */  
    public long setGetSize(String key){  
    	return redisTemplate.opsForSet().size(key);  
    }  

    /** 
     * 批量删除set的值 
     * @param key redisKey
     * @param values 值 可以是多个 
     * @return 移除的个数 
     */  
    public long setBatchRemove(String key, Object ...values) {  
    	Long count = redisTemplate.opsForSet().remove(key, values);  
    	return count == null ? 0 : count;  
    }  
    //===============================操作list api=================================  

    /** 
     * 获取list缓存的指定下标的值（ 0 到 -1代表所有值）
     * @param key redisKey
     * @param startIndex 开始下标 
     * @param endIndex 结束下标 
     * @return 
     */  
    public List<Object> listGet(String key,long startIndex, long endIndex){  
    	return redisTemplate.opsForList().range(key, startIndex, endIndex);  
    }  

    /** 
     * 获取list缓存的长度 
     * @param key redisKey
     * @return 
     */  
    public long listGetSize(String key){  
    	return redisTemplate.opsForList().size(key);  
    }  

    /** 
     * 通过索引 获取list中的值 
     * @param key redisKey
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推 
     * @return 
     */  
    public Object listGetIndex(String key,long index){  
    	return redisTemplate.opsForList().index(key, index);  
    }  

    /** 
     * 将list放入缓存 
     * @param key redisKey
     * @param value 值
     * @return 
     */  
    public long listInsert(String key, Object value) {  
    	return redisTemplate.opsForList().rightPush(key, value);  
    }  

    /** 
     * 将list放入缓存 (设置超时时间)
     * @param key redisKey
     * @param value 值 
     * @param timeout 超时时间(秒) 
     * @return 
     */  
    public long listInsert(String key, Object value, long timeout) {  
    	Long count = redisTemplate.opsForList().rightPush(key, value);  
        if (timeout > 0) 
        	expire(key, timeout);
        return count;
    }  

    /** 
     * 将list放入缓存 
     * @param key 键 
     * @param value 值 
     * @param time 时间(秒) 
     * @return 
     */  
    public long listBatchInsert(String key, List<Object> value) {  
    	return redisTemplate.opsForList().rightPushAll(key, value);  
    }  

    /** 
     * 将list放入缓存 （设置超时时间）
     * @param key redisKey
     * @param value 值 
     * @param timeout 超时时间(秒) 
     * @return 
     */  
    public long listInsert(String key, List<Object> value, long timeout) {  
    	Long count = redisTemplate.opsForList().rightPushAll(key, value);  
    	if (timeout > 0)
    		expire(key, timeout);
    	return count;
    }  

    /** 
     * 根据索引修改list中的某条数据 
     * @param key redisKey
     * @param index 索引 
     * @param value 值 
     * @return 
     */  
    public void listUpdateByIndex(String key, long index, Object value) {  
    	redisTemplate.opsForList().set(key, index, value); 
    }   

    /** 
     * 移除list中N个值为value 
     * @param key 键 
     * @param count 移除数量
     * @param value 值 
     * @return 移除的个数 
     */  
    public long listRemove(String key,long count,Object value) {  
    	return redisTemplate.opsForList().remove(key, count, value);  
    }
    
    //===============================操作sortSet api=================================  
    /**
     * 添加sortedset 元素
     * @param key rediskey
     * @param value redis值
     * @param score 分数
     */
    public void sortSetAdd(String key, Object value, double score){
    	redisTemplate.opsForZSet().add(key, value, score);
    }
    
    /**
     * 获得指定区间有序集合的值
     * @param key redisKey
     * @param start 开始位置
     * @param end 结束位置
     * @return
     */
    public Set<Object> sortSetRange(String key, long start, long end){
    	return redisTemplate.opsForZSet().range(key, start, end);
    }
    
    /**
     * 获得指定区间有序集合
     * @param key redisKey
     * @param start 开始位置
     * @param end 结束位置
     * @return
     */
    public Set<TypedTuple<Object>> sortSetRangeWithScores(String key, long start, long end){
    	return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }
    
    /**
     * 获得指定分数范围内的有序集合的值
     * @param key redisKey
     * @param min 最小分数
     * @param max 最大分数
     * @return
     */
    public Set<Object> sortSetRangeWithScores(String key, double min, double max){
    	return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }
    
    /**
     * 获得指定分数范围内的值数量
     * @param key redisKey
     * @param min 最小分数
     * @param max 最大分数
     * @return
     */
    public long sortSetCount(String key, double min, double max){
    	return redisTemplate.opsForZSet().count(key, min, max);
    }
    
    /**
     * 获得值的分数
     * @param key redisKey
     * @param value 值
     * @return
     */
    public double sortSetScore(String key, Object value){
    	return redisTemplate.opsForZSet().score(key, value);
    }
    
    /**
     * 删除有序集合
     * @param key redisKey
     * @param values 删除的值
     */
    public void sortSetRemove(String key, Object... values){
    	redisTemplate.opsForZSet().remove(key, values);
    }
    
    /**
     * 获得指定值的索引(排名)
     * @param key redisKey
     * @param value 值
     */
    public double sortSetRank(String key, Object value){
    	return redisTemplate.opsForZSet().rank(key, value);
    }
    
    /**
     * 移除指定索引范围内的有序集合
     * @param key redisKey
     * @param start 开始位置
     * @param end 结束位置
     */
    public void sortSetRemoveRange(String key, long start, long end){
    	redisTemplate.opsForZSet().removeRange(key, start, end);
    }
    
    /**
     * 移除指定范围分数内的有序集合
     * @param key redisKey
     * @param min 最小分数
     * @param max 最大分数
     */
    public void sortSetRemoveRangeByScore(String key, double min, double max){
    	redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }
    
    /**
     * 获得指定有序集合的游标迭代器
     * @param key redisKey
     * @return
     */
    public Cursor<TypedTuple<Object>> sortSetScan(String key){
    	return redisTemplate.opsForZSet().scan(key, ScanOptions.NONE);
    }
    
    
	@Component
	private static class RedisUtilInstance{
		/**
    	 * 注入redisTemplate
    	 */
		@Autowired
	    @Resource(name="redisTemplate")
    	private RedisTemplate<String, Object> redisTemplate;
    	
    	/**
    	 * 实例化redisUtil
    	 */
		private static RedisUtil redisUtil;
		
		/**
		 * 初始化注入redisTemplate
		 */
		@PostConstruct
		public void init(){
			redisUtil = new RedisUtil(redisTemplate);
		}
	}
	
	public static RedisUtil getInstance(){
		return RedisUtilInstance.redisUtil;
	}
	
}
