package org.electronic.store;

import java.util.HashMap;
import java.util.Map;

public class UserBucketCreator {
    private final RateLimiterFactory rateLimiterFactory;
    UserBucketCreator(RateLimiterFactory rateLimiterFactory){
        this.rateLimiterFactory = rateLimiterFactory;
    }

    public void accessApplication(){
        RateLimiter rateLimiter = rateLimiterFactory.createRateLimiter();
        if(rateLimiter.grantAccess()){
            System.out.println("Access granted");
        }else{
            System.out.println("Access denied");
        }
    }
}
