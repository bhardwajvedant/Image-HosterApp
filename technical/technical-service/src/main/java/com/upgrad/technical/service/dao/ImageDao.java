package com.upgrad.technical.service.dao;

import com.upgrad.technical.service.entity.ImageEntity;
import com.upgrad.technical.service.entity.UserAuthTokenEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class ImageDao {

    @PersistenceContext
    private EntityManager entityManager;

    public ImageEntity createImage(ImageEntity imageEntity) {
        entityManager.persist(imageEntity);
        return imageEntity;
    }

    public UserAuthTokenEntity getUserAuthToken(final String accesstoken) {
        Query query = entityManager.createQuery("select ut from UserAuthTokenEntity ut where ut.accessToken =:accessToken");
        query.setParameter("accessToken", accesstoken);
        UserAuthTokenEntity userAuthTokenEntity = (UserAuthTokenEntity) query.getSingleResult();
        return userAuthTokenEntity;

    }

    public ImageEntity getImage(final String imageUuid) {
        Query query = entityManager.createQuery("from ImageEntity i where i.uuid = :imageUuid");
        query.setParameter("imageUuid", imageUuid);
        ImageEntity imageEntity = (ImageEntity) query.getSingleResult();
        return imageEntity;
    }

    public ImageEntity getImageById(final long id) {
        Query query = entityManager.createQuery("from ImageEntity i where i.id = :id");
        query.setParameter("id", id);
        ImageEntity imageEntity = (ImageEntity) query.getSingleResult();
        return imageEntity;
    }

    public ImageEntity updateImage(final ImageEntity imageEntity) {
        entityManager.merge(imageEntity);
        return imageEntity;

    }
}
