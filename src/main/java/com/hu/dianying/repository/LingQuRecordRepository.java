package com.hu.dianying.repository;

import com.hu.dianying.domain.LingQuRecord;
import org.springframework.stereotype.Repository;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) LingQuRecordRepository.java 2017/04/19 23:32
 */
@Repository
public interface LingQuRecordRepository {

    void create(LingQuRecord lingQuRecord);

    LingQuRecord findByWeixinNo(String weixinNo);

    int getMaxId();
}