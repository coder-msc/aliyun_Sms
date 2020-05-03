package com.mscNeu.controller;

import com.mscNeu.service.sendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @Auther: jkMa
 * @Date: 2020/5/3 20:33
 * @ItemName: smsServer
 */
@RestController
@CrossOrigin //跨域访问解决办法
public class sendSmsController {


    @Autowired
    private sendSms sendsms;

    @Autowired
    private RedisTemplate<String ,String > redistemplate;


    @GetMapping("/send/{phone}")
    public String sendSms(@PathVariable("phone") String phone){
        String code = redistemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
           return "code:"+code+"验证码已经存在，还未过期"+phone;
        }
        code = UUID.randomUUID().toString().substring(0, 4);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",code);

        boolean isSend = sendsms.sendSms(phone, "SMS_189521342", map);
        if(isSend){
            redistemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return "phone:"+phone+"code:"+code+"发送成功";
        }else {
            return "phone:"+phone+"code:"+code+"发送成功";

        }

    }


    @GetMapping("/getUrl/{phone}")
    public String testUrl(@PathVariable("phone") String phone){

        return "号码时："+phone;
    }
}
