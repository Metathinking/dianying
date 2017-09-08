package com.hu.dianying.controller;

import com.hu.dianying.exception.ServiceException;
import com.hu.dianying.service.LingQuMaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) HomeController.java 2017/04/19 22:12
 */
@Controller
public class HomeController {

    @Autowired
    private LingQuMaService lingQuMaService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(@RequestParam(value = "code", required = false) String code, HttpServletRequest request) {
        if (!StringUtils.isEmpty(code)) {
            request.getSession().setAttribute("code", code);
        }
        return "home";
    }

    @RequestMapping(value = "goLingQuMa", method = RequestMethod.GET)
    public String goLingQuMa() {
        return "lingquma";
    }

    @RequestMapping(value = "goDownload", method = RequestMethod.GET)
    public String goDownload(@RequestParam(value = "code", required = false) String code, Model model,
                             HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(code)) {
            model.addAttribute("error", "请填写领取码");
            return "home";
        }
        try {
            lingQuMaService.verifyCode(code);
        } catch (ServiceException e) {
            model.addAttribute("error", e.getMessage());
            return "home";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            e.printStackTrace();
            return "home";
        }
        try {
            download(request, response);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "home";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "home";
        }
        lingQuMaService.update(code);
        return "home";
    }

    private void download(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, UnsupportedEncodingException {
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        // 下载本地文件
        String fileName = "人民的名义55集全.torrent".toString(); // 文件的默认保存名
        // 读到流中
        String filePath = "resources\\" + fileName;
        InputStream inStream = new FileInputStream(rootDirectory + filePath);// 文件的存放路径
        // 设置输出的格式

        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            while ((len = inStream.read(b)) > 0) {
                outputStream.write(b, 0, len);
            }
            inStream.close();
            outputStream.close();
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}