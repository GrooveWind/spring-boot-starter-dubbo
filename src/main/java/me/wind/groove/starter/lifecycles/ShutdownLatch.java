package me.wind.groove.starter.lifecycles;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Administrator on 2017/4/25.
 */
public class ShutdownLatch implements ShutdownLatchMBean {

    protected AtomicBoolean isRunning = new AtomicBoolean(false);

    public long checkIntervalInSeconds = 10;

    private String domain = "me.wind.groove.starter.lifecycles";

    public ShutdownLatch() {

    }

    public ShutdownLatch(String domain) {
        this.domain = domain;
    }

    public void await() throws Exception {
        if (isRunning.compareAndSet(false, true)) {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            mBeanServer.registerMBean(this, new ObjectName(domain, "name", "ShutdownLatch"));
            while (isRunning.get()) {
                TimeUnit.SECONDS.sleep(checkIntervalInSeconds);
            }
        }
    }

    @Override
    public String shutdown() {
        if (isRunning.compareAndSet(true, false)) {
            return "shutdown signal sent, shutting down...";
        } else {
            return "shutdown signal had been sent...";
        }
    }

    public static void main(String[] args) throws Exception {
        ShutdownLatch latch = new ShutdownLatch("your_domain_for_mbeans");
        latch.await();
    }

}
