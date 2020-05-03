package com.mscNeu.service;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Auther: jkMa
 * @Date: 2020/5/3 20:21
 * @ItemName: smsServer
 */
public interface sendSms {
    boolean sendSms(String phoneNumber, String TemplateCode, Map<String, Object> map);
}
