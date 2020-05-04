package com.upgrad.technical.service.dao;

import com.upgrad.technical.service.entity.ImageEntity;
import com.upgrad.technical.service.entity.UserAuthTokenEntity;
import com.upgrad.technical.service.entity.UserEntity;
import com.upgrad.technical.service.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserDao {


    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity createUser(UserEntity userEntity1) {
        entityManager.persist(userEntity1);
        return userEntity1;
    }

    public UserEntity getUserByEmail(final String email) {
        Query query = entityManager.createQuery("from UserEntity u where u.email = :email");
        query.setParameter("email", email);
        UserEntity singleResults = (UserEntity) query.getSingleResult();
        return singleResults;


    }

    public UserAuthTokenEntity createAuthToken(final UserAuthTokenEntity userAuthTokenEntitys1) {
        entityManager.persist(userAuthTokenEntitys1);
        return userAuthTokenEntitys1;
    }

    public void updateUser(final UserEntity updatedUserEntity) {
        entityManager.merge(updatedUserEntity);
    }


}
