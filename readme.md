## 阿里云短信通道场景启动器

### 发送短信方法

```java
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
```

### 使用示例

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private AliSmsSender aliSmsSender;

    @Test
    public void contextLoads() {
        String signName = "叠石桥家纺";
        String phoneNumber = "18662823907";
        String templateCode = "SMS_96970009";
        // 构建templateParam
        HashMap<String, String> map = new HashMap<>(1);
        map.put("code", "888888");
        String templateParam = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            templateParam = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        boolean b = aliSmsSender.sendSms(signName, phoneNumber, templateCode, templateParam);
        System.out.println(b);
    }
}
```