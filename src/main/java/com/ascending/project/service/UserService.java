package com.ascending.project.service;

import com.ascending.project.domain.Authority;
import com.ascending.project.domain.User;
import com.ascending.project.enumdef.AuthorityRole;
import com.ascending.project.repository.AuthorityDao;
import com.ascending.project.repository.UserDao;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorityDao authorityDao;

//    @Transactional(readOnly = true)
//    public User findByEmailOrUsername(String keyword) throws NotFoundException, NullPointerException{
//        if (keyword == null || "".equals(keyword.trim())){     //if keyword is null or empty
//            throw new NullPointerException("search keyword is null");      //throw new NullPointerException instance called null keyword error
//        }
//        User user = userDao.findByEmailIgnoreCase(keyword);     //declare a user instance, find user by email:
//        if (user == null){                                      //    if user is null - find user by username
//            user = userDao.findByUsernameIgnoreCase(keyword);
//        }
//        if (user == null){                                      //     if user is null - throw new NotFoundException instance
//            throw new NotFoundException("user is not found");
//        }
//        return user;
//    }

//    @Transactional
//    public User save(User user){
//
//    }

    @Transactional
    public User createUser(User newUser){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();        // encoder
        String originalPassword = newUser.getPassword();                    // get newUser's password
        String encodedPassword = encoder.encode(originalPassword);          // encode original password
        newUser.setPassword(encodedPassword);                               // set encoded password to newUser
        addAuthority(newUser, AuthorityRole.ROLE_REGISTERED_USER);
        userDao.save(newUser);
        return newUser;
    }

    @Transactional
    public Authority addAuthority(User user, String authorityString){
        Authority userAuthority = new Authority(user, authorityString);
        return authorityDao.save(userAuthority);
    }
}
