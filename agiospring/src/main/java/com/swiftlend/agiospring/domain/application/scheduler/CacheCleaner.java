package com.swiftlend.agiospring.domain.application.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CacheCleaner {

    private final CacheManager cacheManager;

    @Scheduled(cron = "0 0 0 * * ?")
    public void clearInstallmentCache() {
        Objects.requireNonNull(cacheManager.getCache("installments")).clear();
        System.out.println("Installment cache cleared at midnight");
    }
}

