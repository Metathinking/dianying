package com.hu.dianying.service;

import com.hu.dianying.domain.TuiGuangRecord;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) TuiGuangRecordService.java 2017/04/19 22:28
 */
public interface TuiGuangRecordService {
    String add(String weixinNo, String father);

    List<TuiGuangRecord> listByFatherNo(String weixinNo);

    int getCountByFatherNo(String weixinNo);

    List<TuiGuangRecord> list(Map<String, Object> parameters);

    int getCount(Map<String, Object> map);
}