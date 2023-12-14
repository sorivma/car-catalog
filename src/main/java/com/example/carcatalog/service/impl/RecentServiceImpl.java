package com.example.carcatalog.service.impl;

import com.example.carcatalog.dto.OfferDTO;
import com.example.carcatalog.service.RecentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecentServiceImpl implements RecentService {
    private RedisTemplate<String, OfferDTO> redisTemplate;
    private static final String KEY_PATTERN = "offer:*";

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, OfferDTO> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<OfferDTO> getRecentOffers() {
        Set<String> offerKeys = redisTemplate.keys(KEY_PATTERN);

        if (offerKeys != null) {
            return offerKeys.stream().map(offerKey -> redisTemplate.opsForValue().get(offerKey)).collect(Collectors.toList());

        }
        return Collections.emptyList();
    }
}
