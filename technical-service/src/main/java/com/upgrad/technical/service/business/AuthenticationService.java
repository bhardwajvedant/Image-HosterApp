package com.upgrad.technical.service.business;


import com.upgrad.technical.service.dao.UserDao;
import com.upgrad.technical.service.entity.UserAuthTokenEntity;
import com.upgrad.technical.service.entity.UserEntity;
import com.upgrad.technical.service.exception.AuthenticationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class AuthenticationService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider CryptographyProvider;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserAuthTokenEntity authenticate(final String username, final String password) throws AuthenticationFailedException {
        UserEntity userEntity = userDao.getUserByEmail(username);

        if(userEntity == null){
            throw new AuthenticationFailedException("ATH-001","User with email not found");
        }
        final String encryptedPassword = CryptographyProvider.encrypt(password, userEntity.getSalt());
        if(encryptedPassword.equals(userEntity.getPassword())){
            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(encryptedPassword);
            UserAuthTokenEntity userAuthProvider = new UserAuthTokenEntity();
            userAuthProvider.setUser(userEntity);
            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime expiresAt = now.plusHours(8);
            userAuthProvider.setAccessToken(jwtTokenProvider.generateToken(userEntity.getUuid(),now,expiresAt));
            userAuthProvider.setLoginAt(now);
            userAuthProvider.setExpiresAt(expiresAt);
           // userAuthProvider.setId(50003L);
          //  userAuthProvider.setCreatedBy("api-backend");
           // userAuthProvider.setCreatedAt(now);
            userDao.createAuthToken(userAuthProvider);
            userDao.createUser(userEntity);
            
            return userAuthProvider;
        }else{
            throw new AuthenticationFailedException("ATH-002","Password Failed");
        }
//        dialect: org.hibernate.dialect.MySQL5Dialect
        
//        jpa:
//          properties:
//            hibernate:
//            dialect: org.hibernate.dialect.MySQL5Dialect
    }
}