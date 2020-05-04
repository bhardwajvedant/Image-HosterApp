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
        entityManager.persist(imageEntitys3);
        return imageEntitys3;
    }

    public UserAuthTokenEntity getUserAuthToken(final String accesstoken) {
        Query query = entityManager.createQuery("select ut from UserAuthTokenEntity ut where ut.accessToken =:accessToken");
        query.setParameter("accessToken", accesstoken);
        UserAuthTokenEntity userAuthTokenEntitys1 = (UserAuthTokenEntity) query.getSingleResult();
        return userAuthTokenEntitys1;

    }

    public ImageEntity getImage(final String imageUuid) {
        Query query = entityManager.createQuery("from ImageEntity i where i.uuid = :imageUuid");
        query.setParameter("imageUuid", imageUuid);
        ImageEntity imageEntitys4 = (ImageEntity) query.getSingleResult();
        return imageEntitys4;
    }

    public ImageEntity getImageById(final long id) {
        Query query = entityManager.createQuery("from ImageEntity i where i.id = :id");
        query.setParameter("id", id);
        ImageEntity imageEntitys3 = (ImageEntity) query.getSingleResult();
        return imageEntitys3;
    }

    public ImageEntity updateImage(final ImageEntity imageEntity) {
        entityManager.merge(imageEntity);
        return imageEntity;

    }
}
