package com.demo.project.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.project.models.one.to.one.uni.dir.User;

public class CustomerUserDaoImpl implements CustomerUserDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<User> FindUserByName(String name) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root<User> usr = cq.from(User.class);
		Predicate namePredicate = cb.equal(usr.get("name"), name);
		cq.where(namePredicate);
		TypedQuery<User> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

}
