package com.ascending.project.api;

import com.ascending.project.domain.User;
import com.ascending.project.extend.security.JwtTokenUtil;
import com.ascending.project.repository.UserDao;
import com.ascending.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = {"/api/user","api/users"})
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    @Autowired
//    private AuthenticationManager authenticationManager;

//    @Autowired
//    @Qualifier(BeanIds.AUTHENTICATION_MANAGER)      //TODO
//    private AuthenticationManager authenticationManager;

    //  /api/users GET
    @RequestMapping(method = RequestMethod.GET)
    public List getUserList(){
        logger.debug("list users");
        return userDao.findAll();
    }

    //  /api/users POST
//    @RequestMapping(method = RequestMethod.POST)
//    public User addUser(User u){
//        return userDao.save(u);
//    }

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

    @RequestMapping(value = "", method = RequestMethod.POST)
    public User addUser(@RequestBody User u){
        userService.createUser(u);
        return u;
    }

    //print username and password in tomcat
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user){
        logger.info("username: " + user.getUsername());
        logger.info("username: " + user.getPassword());
        UsernamePasswordAuthenticationToken notfullyAuthentication = new UsernamePasswordAuthenticationToken(
            user.getUsername(),
            user.getPassword()
        );
        String token = null;
//        try{
//            final Authentication authentication = authenticationManager.authenticate(notfullyAuthentication);
//            //TODO generate token from jwttoken service
//            UserDetails ud = userService.findByEmailOrUsername(user.getUsername());
//            token = jwtTokenUtil.generateToken(ud);
//            return token;
//        } catch (AuthenticationException ae){
//            logger.debug("authentication failure");
//        }
        return token;
    }
}
