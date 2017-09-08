package com.hu.dianying.controller.admin;

import com.hu.dianying.domain.Admin;
import com.hu.dianying.domain.LingQuMa;
import com.hu.dianying.service.LingQuMaService;
import com.hu.dianying.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) AdminController.java 2017/04/19 22:30
 */
@Controller
public class AdminController {

    @Autowired
    private LingQuMaService lingQuMaService;

    private final String name="huqingchen";
    private final String password="renshuang";

    @RequestMapping(value = "adminLogin",method = RequestMethod.GET)
    public String gotoLogin(){
        return "admin/login";
    }

    @RequestMapping(value = "adminLogin",method = RequestMethod.POST)
    public String login(Admin admin, Model model, HttpSession session){
        if(name.equals(admin.getName())&&password.equals(admin.getPassword())){
            session.setAttribute("admin",admin);
            return "redirect:/admin/getLingQuMa";
        }else{
            model.addAttribute("msg","用户名或密码错误");
        }
        return "admin/login";
    }

    @RequestMapping(value = "admin/getLingQuMa",method = RequestMethod.GET)
    public String goGetLingQuMa(){
        return "admin/getlingquma";
    }

    @RequestMapping(value = "admin/getLingQuMa",method = RequestMethod.POST)
    @ResponseBody
    public Tip getLingQuMa(@RequestParam("count")Integer count){
        List<LingQuMa> list = lingQuMaService.create(count);
        return new Tip(true,100,"成功",list);
    }
}