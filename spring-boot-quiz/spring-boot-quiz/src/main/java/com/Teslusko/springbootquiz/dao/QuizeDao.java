package com.Teslusko.springbootquiz.dao;

import com.Teslusko.springbootquiz.model.Quize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizeDao extends JpaRepository<Quize,Integer> {
}
