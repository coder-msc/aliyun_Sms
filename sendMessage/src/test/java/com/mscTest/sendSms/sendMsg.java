package com.mscTest.sendSms;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @Auther: jkMa
 * @Date: 2020/5/2 11:21
 * @ItemName: smsServer
 */
@SpringBootTest
public class sendMsg {

    @Test
    public void TestSms() {
        //连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GCiw91nT3HCaTFBBJJK", "RcWHdSgSd3XBcf8EWCvSNgGv2wVB4X");
        IAcsClient client = new DefaultAcsClient(profile);

        //构建请求
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

//        自定义参数
        request.putQueryParameter("PhoneNumbers", "17336334731");
        request.putQueryParameter("SignName", "板带材实时监测");
        request.putQueryParameter("TemplateCode", "SMS_189521342");

//        构建短信验证码
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 2233);

        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));


        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println("发送成功" + response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }
}
