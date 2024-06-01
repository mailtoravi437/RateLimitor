package org.electronic.store;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RateLimiterFactory leakyBucketFactory = new LeakyBucketFactory(10, 1);
        UserBucketCreator userBucketCreator = new UserBucketCreator(leakyBucketFactory );

        ExecutorService executorService  = Executors.newFixedThreadPool(12);


        for(int i=0;i<12;i++){
            executorService.submit(()->{
                userBucketCreator.accessApplication();

            });
        }

    }
}
