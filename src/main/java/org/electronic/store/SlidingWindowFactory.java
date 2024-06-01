package org.electronic.store;

public class SlidingWindowFactory implements RateLimiterFactory{

    private final int timeWindowInSeconds;
    private final int bucketCapacity;

    SlidingWindowFactory(int timeWindowInSeconds, int bucketCapacity){
        this.timeWindowInSeconds = timeWindowInSeconds;
        this.bucketCapacity = bucketCapacity;
    }

    @Override
    public RateLimiter createRateLimiter() {
        return new SlidingWindow(timeWindowInSeconds, bucketCapacity);
    }
}
