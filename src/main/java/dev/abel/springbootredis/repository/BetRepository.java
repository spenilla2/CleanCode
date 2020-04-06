package dev.abel.springbootredis.repository;

import dev.abel.springbootredis.domain.Bet;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class BetRepository implements RedisRepository {
    private static final String KEY="Bet";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    public BetRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Map<String, Object> findAll(String KEY) {
        return hashOperations.entries(KEY);
    }
    public List<Bet> getBetsRoulette(String idRoulette) {
        Map<String, Object> mapBets = hashOperations.entries("Bet");
        List<Bet> betList= new ArrayList<>();
        for (Object valueBet : mapBets.values()) {
            Bet bet = (Bet) valueBet;
            if(bet.getIdRoulette().equals(idRoulette)){
                betList.add(bet);
            }
        }
        return betList;
    }
    @Override
    public Object findById(String id, String KEY) {
        return (Object) hashOperations.get(KEY, id);
    }

    @Override
    public void save(Object objectRepo, String KEY, String UUID) {
        hashOperations.put(KEY,UUID , objectRepo);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(KEY, id);
    }

}
