package me.sdevil507.sms.ali.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里短信通道属性配置
 *
 * @author sdevil507
 */
@ConfigurationProperties("sms.ali")
@Data
public class AliSmsProperties {

    /**
     * 阿里云短信通道accessKeyId
     */
    private String accessKeyId;

    /**
     * 阿里云短信通道accessKeySecret
     */
    private String accessKeySecret;
}
