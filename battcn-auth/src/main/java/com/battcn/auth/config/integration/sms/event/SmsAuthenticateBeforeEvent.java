package com.battcn.auth.config.integration.sms.event;

import org.springframework.context.ApplicationEvent;

/**
 * 短信认证之前的事件，可以监听事件进行用户手机号自动注册
 *
 * @author Levin
 * @since 2019-04-03
 **/
public class SmsAuthenticateBeforeEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public SmsAuthenticateBeforeEvent(Object source) {
        super(source);
    }

}
