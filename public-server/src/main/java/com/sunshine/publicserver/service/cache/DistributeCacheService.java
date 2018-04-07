package com.sunshine.publicserver.service.cache;

import com.sunshine.publicserver.utils.SerializeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DistributeCacheService {

    private static Logger LOGGER = LoggerFactory.getLogger(DistributeCacheService.class);

    @Resource
    private StringRedisTemplate redisTemplate;

    public <T> T getCache(final String key, Class<T> clz) {
        if (StringUtils.isBlank(key)) {
            return null;
        }

        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(key.getBytes());
            }
        });

        if (result == null) {
            return null;
        }

        T finalResult = SerializeUtil.deserialize(result, clz);
        LOGGER.debug("fetch cache info: {} -- {}", key, finalResult);
        return finalResult;
    }

    /**
     * compare and set
     * @Param cacheTime unit seconds
     */
    public <T> void putCacheIfAbsent(String key, T obj, int cacheTime) {
        if (StringUtils.isBlank(key)) {
            return;
        }

        final byte[] bkey = key.getBytes();
        final byte[] bvalue = SerializeUtil.serialize(obj);
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        if (connection.setNX(bkey, bvalue)) {
//            connection.setEx(bkey, cacheTime, bvalue);
//            LOGGER.debug("cache info: key {} cacheTime {} -- {}", key, cacheTime, obj);
//        }

        connection.set(bkey, bvalue, Expiration.seconds(cacheTime), RedisStringCommands.SetOption.ifAbsent());
        LOGGER.debug("cache info: key {} cacheTime {} -- {}", key, cacheTime, obj);

    }

    public long add(final String key) {
        if (StringUtils.isBlank(key)) {
            return -1;
        }

        final byte[] bkey = key.getBytes();
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        long count = connection.incr(bkey);
        LOGGER.info("add cache info: key {} count {}", key, count);
        return count;
    }

    /**
     * add cache
     */
    public <T> void putCache(final String key, final T obj, final int expireTime) {
        if (StringUtils.isBlank(key)) {
            return;
        }

        final byte[] bkey = key.getBytes();
        final byte[] bvalue = SerializeUtil.serialize(obj);
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setEx(bkey, expireTime, bvalue);
                LOGGER.debug("cache info: key {} cacheTime {} -- {}", key, expireTime, obj);
                return true;
            }
        });
    }

    public boolean refresh(final String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }

        try {
            final byte[] bkey = key.getBytes();
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                    if (connection.exists(bkey)) {
                        connection.del(bkey);
                        LOGGER.debug("del cache key {}", key);
                        return true;
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            LOGGER.error("refresh cache fare error: key {}", key, e);
            return false;
        }
    }

}
