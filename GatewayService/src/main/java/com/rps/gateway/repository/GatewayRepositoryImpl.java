package com.rps.gateway.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rps.gateway.entity.UserInfo;

@Repository
public class GatewayRepositoryImpl implements GatewayRepository {

	private static final Logger logger = LoggerFactory.getLogger(GatewayRepositoryImpl.class);

	private EntityManager entityManager;

	@Autowired
	public GatewayRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public UserInfo getUser(String userName) {
		UserInfo user = new UserInfo();
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<UserInfo> cq = cb.createQuery(UserInfo.class);
			Root<UserInfo> userInfo = cq.from(UserInfo.class);
			Predicate userNamePredicate = cb.equal(userInfo.get("userName"), userName);
			cq.where(userNamePredicate);
			TypedQuery<UserInfo> query = entityManager.createQuery(cq);
			List<UserInfo> users = query.getResultList();
			if (!users.isEmpty()) {
				user = users.get(0);
			}
		} catch (Exception e) {
			logger.error("Exception in getUser {}", e);
		}
		return user;
	}
}
