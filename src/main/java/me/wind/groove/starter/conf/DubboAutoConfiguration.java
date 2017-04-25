package me.wind.groove.starter.conf;

import me.wind.groove.starter.commands.DubboServiceLatchCommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Created by Administrator on 2017/4/25.
 */
@Configuration
@Order
public class DubboAutoConfiguration {

    protected Logger logger = LoggerFactory.getLogger(DubboAutoConfiguration.class);

    @Value("${shutdown.latch.domain.name:me.wind.groove.starter.lifecycles}")
    private String shutdownLatchDomainName;

    @Bean
    @ConditionalOnClass(name = "com.alibaba.dubbo.rpc.Exporter")
    public DubboServiceLatchCommandLineRunner configureDubboServiceLatchCommandLineRunner() {
        logger.debug("DubboAutoConfiguration enabled by adding DubboServiceLatchCommandLineRunner.");
        DubboServiceLatchCommandLineRunner runner = new DubboServiceLatchCommandLineRunner();
        runner.setDomain(shutdownLatchDomainName);
        return runner;
    }

}
