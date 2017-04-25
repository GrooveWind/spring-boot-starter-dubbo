package me.wind.groove.starter.lifecycles;

import javax.management.MalformedObjectNameException;

/**
 * Created by Administrator on 2017/4/25.
 */
public interface ShutdownLatchMBean {

    String shutdown() throws MalformedObjectNameException;

}
