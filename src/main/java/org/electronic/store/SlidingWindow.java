package org.electronic.store;

import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindow implements RateLimiter{
    private final Queue<Long> slidingWindow;
    private final int timeWindowInSeconds;
    private final int bucketCapacity;

    SlidingWindow(int timeWindowInSeconds, int bucketCapacity){
        this.slidingWindow = new LinkedList<>();
        this.timeWindowInSeconds = timeWindowInSeconds;
        this.bucketCapacity = bucketCapacity;
    }

    @Override
    public boolean grantAccess() {
        long currentTime = System.currentTimeMillis();
        synchronized (this){
            while(!slidingWindow.isEmpty() && slidingWindow.peek() < currentTime - timeWindowInSeconds * 1000){
                slidingWindow.poll();
            }
            if(slidingWindow.size() < bucketCapacity){
                slidingWindow.add(currentTime);
                return true;
            }
            return false;
        }
    }
}
