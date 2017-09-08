package com.hu.dianying.service.impl;

import com.hu.dianying.domain.LingQuMa;
import com.hu.dianying.domain.LingQuRecord;
import com.hu.dianying.exception.ServiceException;
import com.hu.dianying.repository.LingQuMaRepository;
import com.hu.dianying.repository.LingQuRecordRepository;
import com.hu.dianying.service.LingQuMaService;
import com.hu.dianying.util.ShortUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) LingQuMaServiceImpl.java 2017/04/19 23:13
 */
@Service
public class LingQuMaServiceImpl implements LingQuMaService {

    @Autowired
    private LingQuMaRepository lingQuMaRepository;

    @Autowired
    private LingQuRecordRepository lingQuRecordRepository;

    public void verifyCode(String code) throws ServiceException {
        LingQuMa db = lingQuMaRepository.findByCode(code);
        if(db==null){
            throw new ServiceException(201,"领取码："+code+"，不存在");
        }
        if (db.isUsed()){
            throw new ServiceException(202,"领取码："+code+",已使用");
        }
    }

    public LingQuRecord verifyWeixinNo(String weixinNo) {
        LingQuRecord lingQuRecord = lingQuRecordRepository.findByWeixinNo(weixinNo);
        return lingQuRecord;
    }

    public String getNewLingQuMa(String weixinNo) {
        LingQuMa lingQuMa = new LingQuMa();
        int maxId = lingQuMaRepository.getMaxId();
        maxId++;
        lingQuMa.setId(maxId);
        lingQuMa.setUsed(false);
        setLingquMaCode(lingQuMa, weixinNo);
        lingQuMaRepository.create(lingQuMa);
        LingQuRecord lingQuRecord = new LingQuRecord();
        int lingQuRecordMaxId = lingQuRecordRepository.getMaxId();
        lingQuRecordMaxId++;
        lingQuRecord.setId(lingQuRecordMaxId);
        lingQuRecord.setWeixinNo(weixinNo);
        lingQuRecord.setCode(lingQuMa.getCode());
        lingQuRecord.setTime(System.currentTimeMillis());
        lingQuRecordRepository.create(lingQuRecord);
        return lingQuMa.getCode();
    }

    public void update(String code) {
        LingQuMa db = lingQuMaRepository.findByCode(code);
        db.setUsed(true);
        db.setTime(System.currentTimeMillis());
        lingQuMaRepository.update(db);
    }

    public List<LingQuMa> create(Integer count) {
        if (count==null){
            count=1000;
        }
        int maxId = lingQuMaRepository.getMaxId();
        List<LingQuMa> list = new ArrayList<LingQuMa>();
        long current = System.currentTimeMillis();
        for (int i=0;i<count;i++){
            maxId++;
            LingQuMa lingQuMa = new LingQuMa();
            lingQuMa.setId(maxId);
            lingQuMa.setUsed(false);
            setLingquMaCode(lingQuMa, i+""+current);
            list.add(lingQuMa);
        }
        lingQuMaRepository.createList(list);
        return list;
    }

    private void setLingquMaCode(LingQuMa lingQuMa, String weixinNo) {
        String[] strings = ShortUrlUtil.shortText(weixinNo, "lingquma");
        for (String str : strings) {
            LingQuMa db = lingQuMaRepository.findByCode(str);
            if (db == null) {
                lingQuMa.setCode(str);
                break;
            }
        }
        if (lingQuMa.getCode() == null) {
            setLingquMaCode(lingQuMa, weixinNo + 1);
        }
    }

    public List<LingQuMa> list(Map<String, Object> map) {
        return lingQuMaRepository.list(map);
    }

    public int getCount(Map<String, Object> map) {
        return lingQuMaRepository.getCount(map);
    }
}