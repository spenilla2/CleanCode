package dev.abel.springbootredis.repository;

import java.util.Map;

public interface RedisRepository {
    Map<String, Object> findAll(String KEY);
    Object findById(String id, String KEY);
    void save(Object object, String KEY, String UUID);
    void delete(String id);
}
