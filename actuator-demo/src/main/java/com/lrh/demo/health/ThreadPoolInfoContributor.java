package com.lrh.demo.health;

import com.lrh.demo.ThreadPoolProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class ThreadPoolInfoContributor implements InfoContributor {
    private static Map threadPoolInfo(ThreadPoolExecutor threadPool) {
        Map<String, Object> info = new HashMap<>();
        info.put("poolSize", threadPool.getPoolSize());//当前池大小
        info.put("corePoolSize", threadPool.getCorePoolSize());//设置的核心池大小
        info.put("largestPoolSize", threadPool.getLargestPoolSize());//最大达到过的池大小
        info.put("maximumPoolSize", threadPool.getMaximumPoolSize());//设置的最大池大小
        info.put("completedTaskCount", threadPool.getCompletedTaskCount());//总完成任务数
        return info;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("demoThreadPool", threadPoolInfo(ThreadPoolProvider.getDemoThreadPool()));
        builder.withDetail("ioThreadPool", threadPoolInfo(ThreadPoolProvider.getIOThreadPool()));
    }
}