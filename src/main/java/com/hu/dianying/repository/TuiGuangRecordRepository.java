package com.hu.dianying.repository;

import com.hu.dianying.domain.TuiGuangRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) TuiGuangRecordRepository.java 2017/04/19 22:21
 */
@Repository
public interface TuiGuangRecordRepository {

    void create(TuiGuangRecord tuiGuangRecord);

    List<TuiGuangRecord> list(Map<String,Object> parameter);

    List<TuiGuangRecord> listByFatherNo(Map<String,Object> parameter);

    TuiGuangRecord findById(int id);

    int getCount(Map<String,Object> parameter);

    int getMaxId();

    TuiGuangRecord findByCode(String fatherCode);

    int getCountByFatherNo(String weixinNo);

    TuiGuangRecord findByWeixinNo(String weixinNo);
}