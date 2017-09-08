package com.hu.dianying.service;

import com.hu.dianying.domain.LingQuMa;
import com.hu.dianying.domain.LingQuRecord;
import com.hu.dianying.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) LingQuMaService.java 2017/04/19 22:27
 */
public interface LingQuMaService {

    void verifyCode(String code) throws ServiceException;

    LingQuRecord verifyWeixinNo(String weixinNo);

    String getNewLingQuMa(String weixinNo);

    void update(String code);

    List<LingQuMa> create(Integer count);

    List<LingQuMa> list(Map<String, Object> map);

    int getCount(Map<String, Object> map);
}