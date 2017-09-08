package com.hu.dianying.repository;

import com.hu.dianying.domain.LingQuMa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) LingQuMaRepository.java 2017/04/19 22:20
 */
@Repository
public interface LingQuMaRepository {

    void create(LingQuMa lingQuMa);

    void update(LingQuMa lingQuMa);

    List<LingQuMa> list(Map<String,Object> parameter);

    LingQuMa findById(int id);

    int getCount(Map<String,Object> parameter);

    int getMaxId();

    LingQuMa findByCode(String code);

    void createList(List<LingQuMa> list);
}