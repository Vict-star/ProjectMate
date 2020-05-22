package com.example.projectmate.util;

import java.io.IOException;


import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import net.sf.json.JSONObject;

public class AuthUtil {

    public static final String APPID="wxb82e5dfe5ae0613c";
    public static final String APPSECR="3fef280959899ed39e999d73563b2e6b";
    public static String GetOPenId(String wechatcode) throws IOException{
        String url= "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" +APPID+ "&secret=" +APPSECR+
                "&js_code=" + wechatcode + "&grant_type=authorization_code";
        HttpGet httpGet=new HttpGet(url);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(httpGet);
        HttpEntity msg=response.getEntity();
        String openid ="";
        if(msg!=null){
            String result= EntityUtils.toString(msg, "UTF-8");
            JSONObject jsonObject=JSONObject.fromObject(result);
            //获取openid
            openid=  jsonObject.getString("openid");
        }
        return  openid;
    }
}
