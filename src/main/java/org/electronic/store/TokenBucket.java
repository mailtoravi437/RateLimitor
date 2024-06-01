package org.electronic.store;

import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucket implements RateLimiter{
    private final int bucketCapacity;
    private final int refreshRate;
    private final AtomicInteger currentCapacity;
    private final long lastRefreshTime;
    TokenBucket(int bucketCapacity,int refreshRate){
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        this.currentCapacity = new AtomicInteger(bucketCapacity);
        this.lastRefreshTime = System.currentTimeMillis();
    }

    @Override
    public boolean grantAccess() {
        long currentTime = System.currentTimeMillis();
        int timeElapsed = (int) (currentTime - lastRefreshTime);
        int tokensToAdd = timeElapsed * refreshRate / 1000;
        int newCapacity = Math.min(bucketCapacity, currentCapacity.get() + tokensToAdd);
        if(currentCapacity.compareAndSet(currentCapacity.get(),newCapacity))
            return currentCapacity.decrementAndGet() >= 0;
        return false;
    }
}
