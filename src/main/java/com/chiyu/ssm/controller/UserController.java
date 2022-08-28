package com.chiyu.ssm.controller;

import com.chiyu.ssm.entity.User;
import com.chiyu.ssm.service.UserService;
import com.chiyu.ssm.vo.LoginVo;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.setProperty("nashorn.args", "--no-deprecation-warning");

        ArithmeticCaptcha captcha = new ArithmeticCaptcha(160, 32);
        captcha.setLen(2);
        CaptchaUtil.out(captcha, request, response);
    }


    @PostMapping("/login")
    public String login(LoginVo loginvo, HttpServletRequest request,
                        HttpServletResponse response,
                        HttpSession session) throws IOException {

        System.out.println("loginvo = " + loginvo);

        // 校验验证码
        if (!CaptchaUtil.ver(loginvo.getCheckCode(), request)) {
            // 清除session中的验证码
            CaptchaUtil.clear(request);
            request.setAttribute("errinfo", "验证码错误");
            return "login";
        }
        CaptchaUtil.clear(request);

        // 查询数据库，校验帐号密码
        User user = userService.selectUserByName(loginvo.getAccount());
        if (user == null) {
            request.setAttribute("errinfo", "帐号错误");
            return "login";
        }
        if (!user.getPassword().equals(loginvo.getPassword())) {
            request.setAttribute("errinfo", "密码错误");
            return "login";
        }
        // 到这里说明登陆成功, 将数据保存到session域中
        request.getSession().setAttribute("user", user);
        // 实现记住我功能
        // 先根据当前的sessionID创建一个新的cookie
        Cookie cookie = new Cookie("JSESSIONID", session.getId());

        if (loginvo.isSaveMe()) {
            cookie.setMaxAge(7 * 24 * 60 * 60);
        } else {
            // 恢复默认的会话结束删除
            cookie.setMaxAge(-1);
        }
        response.addCookie(cookie);
        // 使用重定向的方式转发视图
        // 使用index.html不是直接使用静态方法, 是找控制器中设置的index.html
        return "redirect:/index.html";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        // 让当前的会话对象直接失效
        session.invalidate();

        // 重定向到登陆页
        return "redirect:/login.html";
    }
}
