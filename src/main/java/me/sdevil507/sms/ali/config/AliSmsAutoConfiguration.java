package me.sdevil507.sms.ali.config;

import me.sdevil507.sms.ali.properties.AliSmsProperties;
import me.sdevil507.sms.ali.service.AliSmsSender;
import me.sdevil507.sms.ali.service.impl.AliSmsSenderImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云短信通道自动暴露配置
 *
 * @author sdevil507
 */
@Configuration
@EnableConfigurationProperties(AliSmsProperties.class)
public class AliSmsAutoConfiguration {

    /**
     * 获取阿里云短信发送者
     *
     * @param aliSmsProperties 阿里云短信配置
     * @return 阿里云短信发送者
     */
    @Bean
    @ConditionalOnProperty(prefix = "sms.ali", name = {"access-key-id", "access-key-secret"})
    public AliSmsSender aliSmsSender(AliSmsProperties aliSmsProperties) {
        return new AliSmsSenderImpl(aliSmsProperties.getAccessKeyId(), aliSmsProperties.getAccessKeySecret());
    }
}
