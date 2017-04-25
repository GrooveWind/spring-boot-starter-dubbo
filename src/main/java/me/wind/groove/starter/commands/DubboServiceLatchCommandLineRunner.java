package me.wind.groove.starter.commands;

import me.wind.groove.starter.lifecycles.ShutdownLatch;
import org.springframework.boot.CommandLineRunner;

/**
 * Created by Administrator on 2017/4/25.
 */
public class DubboServiceLatchCommandLineRunner implements CommandLineRunner {

    private String domain = "me.wind.groove.starter.lifecycles";

    @Override
    public void run(String... args) throws Exception {
        ShutdownLatch latch = new ShutdownLatch(getDomain());
        latch.await();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
