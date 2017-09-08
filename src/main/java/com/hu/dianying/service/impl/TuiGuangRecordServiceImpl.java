package com.hu.dianying.service.impl;

import com.hu.dianying.domain.TuiGuangRecord;
import com.hu.dianying.repository.TuiGuangRecordRepository;
import com.hu.dianying.service.TuiGuangRecordService;
import com.hu.dianying.util.ShortUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) TuiGuangRecordServiceImpl.java 2017/04/19 23:14
 */
@Service
public class TuiGuangRecordServiceImpl implements TuiGuangRecordService {

    @Autowired
    private TuiGuangRecordRepository tuiGuangRecordRepository;

    public String add(String weixinNo, String fatherCode) {
        TuiGuangRecord db=tuiGuangRecordRepository.findByWeixinNo(weixinNo);
        if(db!=null){
            return db.getCode();
        }
        int maxId = tuiGuangRecordRepository.getMaxId();
        maxId++;
        TuiGuangRecord tuiGuangRecord = new TuiGuangRecord();
        tuiGuangRecord.setId(maxId);
        tuiGuangRecord.setWeixinNo(weixinNo);
        TuiGuangRecord father= tuiGuangRecordRepository.findByCode(fatherCode);
        if(father!=null){
            tuiGuangRecord.setFatherNo(father.getWeixinNo());
        }else{
            tuiGuangRecord.setFatherNo("");
        }
        setRecordCode(tuiGuangRecord,weixinNo);
        tuiGuangRecord.setTime(System.currentTimeMillis());
        tuiGuangRecordRepository.create(tuiGuangRecord);
        return tuiGuangRecord.getCode();
    }

    private void setRecordCode(TuiGuangRecord record, String weixinNo) {
        String[] strings = ShortUrlUtil.shortText(weixinNo, "lingquma");
        for (String str : strings) {
            TuiGuangRecord db = tuiGuangRecordRepository.findByCode(str);
            if (db == null) {
                record.setCode(str);
                break;
            }
        }
        if (record.getCode() == null) {
            setRecordCode(record, weixinNo + 1);
        }
    }

    public List<TuiGuangRecord> listByFatherNo(String weixinNo) {
        Map<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("fatherNo",weixinNo);
        parameters.put("start",0);
        parameters.put("size",10);
        List<TuiGuangRecord> list = tuiGuangRecordRepository.listByFatherNo(parameters);
        return list;
    }

    public int getCountByFatherNo(String weixinNo) {
        return tuiGuangRecordRepository.getCountByFatherNo(weixinNo);
    }

    public List<TuiGuangRecord> list(Map<String, Object> parameters) {
        return tuiGuangRecordRepository.list(parameters);
    }

    public int getCount(Map<String, Object> map) {
        return tuiGuangRecordRepository.getCount(map);
    }
}