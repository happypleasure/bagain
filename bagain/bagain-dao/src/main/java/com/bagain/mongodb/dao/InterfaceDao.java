package com.bagain.mongodb.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bagain.mongodb.vo.VoTest;

public interface InterfaceDao extends MongoRepository<VoTest, String> {

	@Query("{'name':{ '$regex' : '^.*?0.*$'}}")
    List<VoTest> findByNameRegex(String name);

	@Query("{'name':?0}")
    List<VoTest> findByName(String name);

    @Query("{'name':{ '$regex' : '^.*?0.*$'}}")
    Page<VoTest> findByNameRegex(String name, Pageable pageable);
}
