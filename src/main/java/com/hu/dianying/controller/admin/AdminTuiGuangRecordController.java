package com.hu.dianying.controller.admin;

import com.hu.dianying.domain.TuiGuangRecord;
import com.hu.dianying.query.PageQuery;
import com.hu.dianying.service.TuiGuangRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) AdminTuiGuangRecordController.java 2017/04/20 11:31
 */
@Controller
@RequestMapping("admin/tuiguang")
public class AdminTuiGuangRecordController {

    @Autowired
    private TuiGuangRecordService recordService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "index", required = false) Integer index, Model model) {
        PageQuery query = new PageQuery();
        if (index == null) {
            index = 0;
        }
        query.setIndex(index);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", query.getStart());
        map.put("size", query.getSize());
        List<TuiGuangRecord> list = recordService.list(map);
        int count = recordService.getCount(map);
        query.setCount(count);
        query.setPageInfo();
        model.addAttribute("list", list);
        model.addAttribute("pageQuery", query);
        return "admin/tuiguangrecord-list";
    }
}