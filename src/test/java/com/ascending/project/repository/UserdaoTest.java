package com.ascending.project.repository;

import com.ascending.project.config.AppConfig;
import com.ascending.project.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class UserdaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void findByIdTest(){
        User expectedUser = new User();
        expectedUser.setUsername("TestUser");
        expectedUser.setEmail("test.user@project.com");
        expectedUser.setPassword("password");
        userDao.save(expectedUser);
        User actualUser = userDao.findById(expectedUser.getId());
        assertNotNull(actualUser);
//        assertTrue(false);
    }

    @Test
    @Transactional
    public void findByIdEagerTest(){
        User expectedResult = new User();
//        expectedResult.set
    }

}
