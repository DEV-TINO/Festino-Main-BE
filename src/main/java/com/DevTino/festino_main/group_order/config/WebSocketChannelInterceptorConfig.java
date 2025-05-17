package com.DevTino.festino_main.group_order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.security.Principal;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketChannelInterceptorConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

                if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
                    final String sessionId = accessor.getSessionId();


                    // 세션 ID를 기반으로 Principal 생성
                    accessor.setUser(new Principal() {
                        @Override
                        public String getName() {
                            return sessionId;
                        }

                        @Override
                        public String toString() {
                            return "WebSocketUser[" + getName() + "]";
                        }
                    });
                }
                return message;
            }
        });
    }
}