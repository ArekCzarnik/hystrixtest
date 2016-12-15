import com.netflix.hystrix.*;

public class HystrixUtils {

    public static final int DEFAULT_SEMAPHORE_MAX_COUNT = 100;

    public static HystrixObservableCommand.Setter buildHystrixObservableProperties(final String ressourceName,
                                                                                   final int executionTimeout) {
        return HystrixObservableCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(ressourceName))
                .andCommandKey(HystrixCommandKey.Factory.asKey(ressourceName))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionTimeoutInMilliseconds(executionTimeout)
                                .withFallbackIsolationSemaphoreMaxConcurrentRequests(DEFAULT_SEMAPHORE_MAX_COUNT));
    }

    public static HystrixCommand.Setter buildHystrixProperties(final String ressourceName,
                                                               final int executionTimeout,
                                                               final int poolcoreSize,
                                                               final int maxQueueSize,
                                                               final int rejectSize,
                                                               final boolean forceClosed) {
        return HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(ressourceName))
                .andCommandKey(HystrixCommandKey.Factory.asKey(ressourceName))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withMaxQueueSize(maxQueueSize)
                        .withQueueSizeRejectionThreshold(rejectSize)
                        .withCoreSize(poolcoreSize))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(executionTimeout)
                        .withCircuitBreakerForceClosed(forceClosed)
                );
    }

}
