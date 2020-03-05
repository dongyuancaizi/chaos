package com.cui.tech.chaos.lite.app.config;

import com.cui.tech.chaos.constant.ThreadPoolTypeEnum;
import com.cui.tech.chaos.util.ThreadPoolFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class AsyncConfig {

    @Bean(name = "taskExecutorWbswryxx")
    public Executor taskExecutorWbswryxx() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(200);

        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("taskExecutorWbswryxx-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }



    /**
     * io密集型的线程池
     *
     * @return
     */
    @ConditionalOnMissingBean
    @Bean(name = "ioIntensiveThreadPool")
    public Executor ioIntensiveThreadPool() {
        return ThreadPoolFactory.instance(ThreadPoolTypeEnum.IO_INTENSIVE);
    }

    /**
     * cpu密集型的线程池
     *
     * @return
     */
    @ConditionalOnMissingBean
    @Bean(name = "cpuIntensiveThreadPool")
    public Executor cpuIntensiveThreadPool() {
        return ThreadPoolFactory.instance(ThreadPoolTypeEnum.CPU_INTENSIVE);
    }

}

