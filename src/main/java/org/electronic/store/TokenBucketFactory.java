package org.electronic.store;

public class TokenBucketFactory implements RateLimiterFactory{

    private int bucketCapacity;
    private int refreshRate;

    TokenBucketFactory(int bucketCapacity, int refreshRate){
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
    }


    @Override
    public RateLimiter createRateLimiter() {
        return new TokenBucket(bucketCapacity, refreshRate);
    }
}
