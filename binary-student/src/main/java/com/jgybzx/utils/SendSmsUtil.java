package com.jgybzx.utils;


import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;


import java.util.HashMap;
import java.util.Map;

/**
 * @author jgybzx
 * @date 2021/3/8 10:00
 * @description 阿里云 短信服务，短信发送工具类
 */
public class SendSmsUtil {

    public static void sendSms(String code, String phone) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G53BsscYPym7Yu9pBep", "4KXovzsIfhaldGbCZxoynpSiuGYDXV");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        // <version>4.5.3</version> 该版本下 SysVersion = 2017-05-25
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "将光阴比作夏");
        request.putQueryParameter("TemplateCode", "SMS_180063671");
        Map<String, String> paramMap = new HashMap<>(16);
        paramMap.put("code", code);
        paramMap.put("phone", phone);
        request.putQueryParameter("TemplateParam", JSON.toJSONString(paramMap));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int random = (int) ((Math.random() * 9 + 1) * 100000);
        //sendSms("", "13716636647");

        sendSms("", "17501696526");
    }
}
