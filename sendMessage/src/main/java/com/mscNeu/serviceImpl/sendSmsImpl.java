package com.mscNeu.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.mscNeu.service.sendSms;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Auther: jkMa
 * @Date: 2020/5/3 20:24
 * @ItemName: smsServer
 */
@Service
public class sendSmsImpl implements sendSms {
    @Override
    public boolean sendSms(String phoneNumber, String TemplateCode, Map<String, Object> map) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "AccessKeyId", "AccessKeySecrt");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");


//        自定义参数
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "板带材实时监测");
        request.putQueryParameter("TemplateCode", TemplateCode);//SMS_189521342

//        构建短信验证码
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("code", 2233);

        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();//判断是否成功
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return false;

    }
}
