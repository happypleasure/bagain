package com.bagain.mongodb.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bagain.mongodb.vo.VoTest;

public interface CommonInterfaceDao {

    public VoTest findById(String id) ;

    public List<VoTest> findByName(String name) ;

    public List<VoTest> findByNameRegex(String name) ;

    public Page<VoTest> findByNameRegex(String name, int pageIndex, int pageSize) ;
	
}
