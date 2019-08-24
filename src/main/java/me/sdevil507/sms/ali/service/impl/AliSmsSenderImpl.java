package me.sdevil507.sms.ali.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.sdevil507.sms.ali.service.AliSmsSender;

/**
 * 阿里云短信通道实现
 *
 * @author sdevil507
 */
@Slf4j
public class AliSmsSenderImpl implements AliSmsSender {

    private IAcsClient client;

    public AliSmsSenderImpl(String accessKeyId, String accessKeySecret) {
        DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, accessKeySecret);
        this.client = new DefaultAcsClient(profile);
    }

    @Override
    public boolean sendSms(String signName, String phoneNumbers, String templateCode, String templateParam) {
        CommonRequest request = new CommonRequest();
        // 预定参数必须设置
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        // 提交参数
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam);
        CommonResponse response;
        try {
            response = client.getCommonResponse(request);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response.getData());
            String codeStr = "Code";
            String msgStr = "Message";
            String code = node.get(codeStr).asText();
            String msg = node.get(msgStr).asText();
            String ok = "OK";
            if (ok.equals(code)) {
                // 短信发送成功
                return true;
            } else {
                // 短信发送失败
                log.error("短信发送失败,手机号:{},错误码:{},错误信息:{}", phoneNumbers, code, msg);
                return false;
            }
        } catch (Exception e) {
            log.error("发送短信失败,阿里云服务器异常:", e);
            return false;
        }
    }
}
