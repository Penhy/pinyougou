package cn.nightwee.core.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录管理
 */
@RestController
@RequestMapping("/login")
public class LoginController {


    /**
     * 获取当前登录人
     */
    @RequestMapping("/showName")
    public Map<String,Object> showName() {
        //使用安全框架获取当前登录人
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username",name);
        map.put("curTime",new Date());

        return map;
    }
}
