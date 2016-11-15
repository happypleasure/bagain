package com.bagain.mongodb.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.bagain.mongodb.dao.CommonInterfaceDao;
import com.bagain.mongodb.vo.VoTest;

@Repository
public class CommonDaoImpl implements CommonInterfaceDao{

	@Autowired
    private MongoTemplate mongoTemplate;

    public VoTest findById(String id) {
        return mongoTemplate.findById(id, VoTest.class);
    }

    public List<VoTest> findByName(String name) {
        return mongoTemplate.find(new Query(Criteria.where("name").is(name)), VoTest.class);
    }

    public List<VoTest> findByNameRegex(String name) {
        return mongoTemplate.find(new Query(Criteria.where("name").regex(name)), VoTest.class);
    }

    public Page<VoTest> findByNameRegex(String name, int pageIndex, int pageSize) {
        List<VoTest> hospitals = mongoTemplate.find(new Query(Criteria.where("name").regex(name)).skip(pageIndex*pageSize).limit(pageSize), VoTest.class);
        long count = mongoTemplate.count(new Query(Criteria.where("name").regex(name)), VoTest.class);
        PageRequest pageRequest = new PageRequest(pageIndex, pageSize);
        PageImpl<VoTest> page = new PageImpl(hospitals, pageRequest, count);
        return page;
    }
}
