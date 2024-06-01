package org.electronic.store;

import java.util.concurrent.atomic.AtomicInteger;

public class LeakyBucket implements RateLimiter{

    private final int bucketCapacity;
    private final int leakRate;
    private final AtomicInteger currentBucketSize;
    private final AtomicInteger lastLeakTime;
    LeakyBucket(int bucketCapacity,int leakRate){
        this.bucketCapacity = bucketCapacity;
        this.leakRate = leakRate;
        this.currentBucketSize = new AtomicInteger(0);
        this.lastLeakTime = new AtomicInteger(0);
    }
    @Override
    public boolean grantAccess() {
        leakToken();
        if(currentBucketSize.get() < bucketCapacity){
            currentBucketSize.incrementAndGet();
            return true;
        }

        return false;
    }

    public void leakToken(){
        int currentTime = (int) System.currentTimeMillis();
        int timeElapsed = currentTime - lastLeakTime.get();
        int tokenToleak = timeElapsed * leakRate / 1000;
        int remainingCapacity = Math.max(0, currentBucketSize.get() - tokenToleak);
        currentBucketSize.set(remainingCapacity);
        lastLeakTime.set(currentTime);
    }
}
