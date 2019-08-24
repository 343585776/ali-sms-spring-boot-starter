package me.sdevil507.sms.ali.service;

/**
 * 阿里云短信发送接口
 *
 * @author sdevil507
 */
public interface AliSmsSender {

    /**
     * 发送短信
     *
     * @param signName      短信签名
     * @param phoneNumbers  手机号列表(多个号码间使用","分隔)
     * @param templateCode  模板号
     * @param templateParam 模板中占位符与对应值的map类型json字符串,例如:{"code":"123456"}
     * @return 执行反馈
     */
    boolean sendSms(String signName, String phoneNumbers, String templateCode, String templateParam);
}
