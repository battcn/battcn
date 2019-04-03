package com.battcn.auth.config.integration.sms.event;

import org.springframework.context.ApplicationEvent;

/**
 * 短信认证成功事件
 *
 * @author Levin
 * @since 2019-04-03
 **/
public class SmsAuthenticateSuccessEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public SmsAuthenticateSuccessEvent(Object source) {
        super(source);
    }

}
