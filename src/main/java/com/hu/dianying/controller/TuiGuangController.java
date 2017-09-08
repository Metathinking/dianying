package com.hu.dianying.controller;

import com.hu.dianying.domain.LingQuRecord;
import com.hu.dianying.domain.TuiGuangRecord;
import com.hu.dianying.service.LingQuMaService;
import com.hu.dianying.service.TuiGuangRecordService;
import com.hu.dianying.util.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) TuiGuangController.java 2017/04/19 22:52
 */
@Controller
public class TuiGuangController {

    @Autowired
    private TuiGuangRecordService tuiGuangRecordService;

    @Autowired
    private LingQuMaService lingQuMaService;
    @RequestMapping(value = "tuiguang",method = RequestMethod.GET)
    public String goTuiGuang( HttpServletRequest request){

        return "tuiguang";
    }

    @RequestMapping(value = "tuiguang/{weixinNo}",method = RequestMethod.POST)
    @ResponseBody
    public Tip tuiguang(@PathVariable("weixinNo")String weixinNo,HttpServletRequest request){
        Object obj=request.getSession().getAttribute("code");
        String fatherCode;
        if(obj==null){
            fatherCode="";
        }else{
            fatherCode= (String) obj;
        }
        String code=tuiGuangRecordService.add(weixinNo,fatherCode);
        List<TuiGuangRecord> tuiGuangRecords =tuiGuangRecordService.listByFatherNo(weixinNo);
        int count=tuiGuangRecordService.getCountByFatherNo(weixinNo);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code",code);
        map.put("list",tuiGuangRecords);
        map.put("count",count);
        return new Tip(true,100,"成功",map);
    }

    @RequestMapping(value = "getLingQuMa/{weixinNo}",method = RequestMethod.GET)
    public String getLingQuMa(@PathVariable("weixinNo")String weixinNo, Model model){
        LingQuRecord record=lingQuMaService.verifyWeixinNo(weixinNo);
        if (record!=null){
            model.addAttribute("msg","您的领取码为："+record.getCode());
            return "msg";
        }
        int count = tuiGuangRecordService.getCountByFatherNo(weixinNo);
        if(count<10){
            model.addAttribute("msg","您的推广人数未达10人");
            return "msg";
        }
        String code=lingQuMaService.getNewLingQuMa(weixinNo);
        model.addAttribute("msg","您的领取码为："+code);
        return "msg";
    }
}