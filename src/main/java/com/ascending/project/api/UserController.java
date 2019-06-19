package com.ascending.project.api;

import com.ascending.project.domain.User;
import com.ascending.project.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = {"/api/user","api/users"})
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;

    //  /api/users GET
    @RequestMapping(method = RequestMethod.GET)
    public List getUserList(){
        logger.debug("list users");
        return userDao.findAll();
    }

    //  /api/users POST
    @RequestMapping(method = RequestMethod.POST)
    public User addUser(User u){
        return userDao.save(u);
    }

    //  /api/users/5 GET
    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("user_id") Long Id){
        logger.debug("find user id: " + Id);
        return userDao.findById(Id);
    }

    //  /api/user?username=xxxx GET
//    @RequestMapping(method = RequestMethod.GET, params = "username")
//    public User getUserByUsername(@RequestParam("username") String username){
//        logger.debug("find users by username: "+username);
//        return userDao.findByUsernameIgnoreCase(username);
//    }
}
