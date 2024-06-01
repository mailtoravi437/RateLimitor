package org.electronic.store;

public class LeakyBucketFactory implements RateLimiterFactory{
    private final int bucketCapacity;
    private final int leakRate;
    LeakyBucketFactory(int bucketCapacity,int leakRate){
        this.bucketCapacity = bucketCapacity;
        this.leakRate = leakRate;
    }
    @Override
    public RateLimiter createRateLimiter() {
         return new LeakyBucket(this.bucketCapacity,this.leakRate);
    }
}
