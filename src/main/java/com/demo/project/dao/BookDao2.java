package com.demo.project.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.models.Book;
import com.demo.project.models.Company;
import com.demo.project.one.to.many.bi.dir.models.Color;

@Repository
public class BookDao2 {
	EntityManager em;

	List<Book> findAllBooks() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class);

		TypedQuery<Book> query = em.createQuery(cq);
		return query.getResultList();

	}
}
