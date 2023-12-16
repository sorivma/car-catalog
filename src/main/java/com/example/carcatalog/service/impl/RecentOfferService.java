package com.example.carcatalog.service.impl;

import com.example.carcatalog.dto.OfferDTO;
import com.example.carcatalog.service.RecentService;
import com.example.carcatalog.utils.ApplicationConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecentOfferService implements RecentService<OfferDTO> {
    private final RedisTemplate<String, OfferDTO> redisTemplate;

    public RecentOfferService(RedisTemplate<String, OfferDTO> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<OfferDTO> getRecent(String username) {
        String key = ApplicationConstants.CacheConstants.recentOfferPrefix + username;
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    @Override
    public void addRecent(OfferDTO recent, String username) {
        String key = ApplicationConstants.CacheConstants.recentOfferPrefix + username;

        if (!isOfferAlreadyPresent(key, recent)) {
            redisTemplate.opsForList().leftPush(key, recent);
        }
    }

    private boolean isOfferAlreadyPresent(String key, OfferDTO offer) {
        List<OfferDTO> recentlyWatched = redisTemplate.opsForList().range(key, 0, -1);

        if (recentlyWatched == null) {
            return false;
        }

        for (OfferDTO watchedOffer : recentlyWatched) {
            if (watchedOffer.getId().equals(offer.getId())) {
                return true; // Duplicate found
            }
        }

        return false;
    }
}
