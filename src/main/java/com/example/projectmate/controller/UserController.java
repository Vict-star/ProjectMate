package com.example.projectmate.controller;

import com.example.projectmate.domain.User;
import com.example.projectmate.service.UserService;
import com.example.projectmate.util.AuthUtil;
import com.example.projectmate.util.MySessionContext;
import net.sf.json.JSONObject;
import org.apache.catalina.connector.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import com.wx.auth.util.AuthUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",produces="text/html;charset=UTF-8")
    public @ResponseBody String WechatLogin(@RequestBody String userInfo, HttpServletRequest request, HttpSession session) throws IOException {
        User user=new User();
        JSONObject jsonObject = JSONObject.fromObject(userInfo);
        String wechatcode = jsonObject.getString("wechat_code");//用户登录的code
        String userInfo1 = jsonObject.getString("userInfo");//用户信息json
        JSONObject jsonObject1 = JSONObject.fromObject(userInfo1);//解析用户信息
        String nickName = jsonObject1.getString("nickName");  //微信用户名
        String gender = jsonObject1.getString("gender");  //性别：1：男，0：女
        String country = jsonObject1.getString("country");//国家
        String province = jsonObject1.getString("province");//省
        String city = jsonObject1.getString("city");//城市
        String avatarUrl = jsonObject1.getString("avatarUrl");//头像路径

        String openid ="";
        openid=AuthUtil.GetOPenId(wechatcode);  //得到openid
        user.setWechat_openid(openid);
        if (gender!=null){
            if (gender.equals("1")){
                gender="男";
            }else {
                gender="女";
            }
            user.setGender(gender);
        }
        user.setUsername(nickName);
        user.setAvatarUrl(avatarUrl);
        User user1= userService.findByOpenId(user);//查询用户是否已经存在
        System.out.println("user1= "+user1);
        String sessionId ="";
        if (user1==null){//用户不存在
            userService.addUser(user);//新增用户
            session.setAttribute("Loginuser", user);//将用户放到session
            session.setMaxInactiveInterval(60*60);  //session时间：60分钟
            sessionId = request.getSession().getId();//返回sessionId给前台
        }else {   //用户已存在,将用户详细信息放session
            session.setAttribute("Loginuser", user1);
            session.setMaxInactiveInterval(60*60);  //session时间：60分钟
            sessionId = request.getSession().getId();
        }
        System.out.println("sessionId= "+sessionId);
        return sessionId;
    }
    @RequestMapping(value = "/MyInformation")
    public @ResponseBody User MyInformation(@RequestBody String wechat_code) throws IOException {
        JSONObject jsonObject = JSONObject.fromObject(wechat_code);
        String SessionId = jsonObject.getString("SessionId");//用户登录的sessionId（json格式）
        JSONObject jsonObject1 = JSONObject.fromObject(SessionId);//用户登录的sessionId（json格式）
        String JSESSIONID ="";
        if (jsonObject1.getString("JSESSIONID")!=null){
            JSESSIONID = jsonObject1.getString("JSESSIONID");//提取用户登录的sessionId
            System.out.println("JSESSIONID= "+JSESSIONID);
        }
        HttpSession session1 = MySessionContext.getSession(JSESSIONID);//根据JSESSIONID获得session（自定义MySessionContext监听器）
        User user = new User();
        if (session1!=null){
            user = (User)session1.getAttribute("Loginuser");//根据获得的当前session得到Loginuser的信息，查看是否已登录
        }
        System.out.println("1"+user);
        if(null == user.getUsername()){//未登录，去数据库查找用户
            User user2=new User();
            String wechatcode = jsonObject.getString("wechat_code");//用户登录的code
            String openid ="";
            openid=AuthUtil.GetOPenId(wechatcode);  //得到openid
            user2.setWechat_openid(openid);
            System.out.println("2"+user2);
            User user1= userService.findByOpenId(user2);//查询用户是否已经存在
            if (user1!=null){//用户存在
                user=user1;
            }
        }
        if (user.getIs_identify()=="1"){
            user.setIs_identify("已通过认证");
        }else {
            user.setIs_identify("未认证");
        }
        if (user.getUsername()==null){
            user.setUsername(" ");
        }if (user.getCollege()==null){
            user.setCollege(" ");
        }if (user.getMajor()==null){
            user.setMajor(" ");
        }if (user.getGender()==null){
            user.setGender(" ");
        }if (user.getIntroduction()==null){
            user.setIntroduction(" ");
        }
        System.out.println("3"+user);
        return  user;
    }
    @RequestMapping(value = "/UpdateUser")
    public @ResponseBody String UpdateUser(@RequestBody String UpdateUser) throws IOException {
        JSONObject jsonObject = JSONObject.fromObject(UpdateUser);
        String username = jsonObject.getString("username");
        String student_number = jsonObject.getString("student_number");
        String gender = jsonObject.getString("gender");
        String college = jsonObject.getString("college");
        String major = jsonObject.getString("major");
        String introduction = jsonObject.getString("introduction");
        String SessionId = jsonObject.getString("SessionId");//用户登录的sessionId（json格式）
        JSONObject jsonObject1 = JSONObject.fromObject(SessionId);//用户登录的sessionId（json格式）
        String JSESSIONID ="";
        if (jsonObject1.getString("JSESSIONID")!=null){
            JSESSIONID = jsonObject1.getString("JSESSIONID");//提取用户登录的sessionId
        }
        HttpSession session1 = MySessionContext.getSession(JSESSIONID);//根据JSESSIONID获得session（自定义MySessionContext监听器）
        User user = new User();
        if (session1!=null){
            user = (User)session1.getAttribute("Loginuser");//根据获得的当前session得到Loginuser的信息，查看是否已登录
        }if (user.getWechat_openid()==null||user.getWechat_openid().equals("null")||user.getWechat_openid().equals("")){//未登录
            String wechatcode = jsonObject.getString("wechat_code");//用户登录的code
            String openid ="";
            openid=AuthUtil.GetOPenId(wechatcode);  //得到openid
            user.setWechat_openid(openid);
        }
        user.setUsername(username);
        user.setStudent_number(student_number);
        user.setGender(gender);
        user.setCollege(college);
        user.setMajor(major);
        user.setIntroduction(introduction);
        userService.updateUser(user);
        return  "已成功更新";
    }
}
