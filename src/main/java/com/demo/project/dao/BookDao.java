package com.demo.project.dao;

import java.util.List;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.models.Book;
import com.demo.project.models.Company;
import com.demo.project.one.to.many.bi.dir.models.Color;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {

	List<Book> findAllById(int i);

}
