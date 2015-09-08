package com.hz.wechatproject.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hz.wechatproject.db.service.UserService;
import com.hz.wechatproject.pojo.User;


@Service
public class CustomUserAccessServiceImpl implements UserDetailsService {  
	
	
	@Autowired
	UserService userService;
	
    protected static Logger logger = Logger.getLogger(CustomUserAccessServiceImpl.class);  
  
    public UserDetails loadUserByUsername(String username)  
            throws UsernameNotFoundException, DataAccessException {  
  
        UserDetails user = null;  
  
        try {  
  
            // 搜索数据库以匹配用户登录名.  
            // 我们可以通过dao使用JDBC来访问数据库  
        	List<User> userList = userService.getAllUser();  
  
            // Populate the Spring User object with details from the dbUser  
            // Here we just pass the username, password, and access level  
            // getAuthorities() will translate the access level to the correct  
            // role type  
  
            user = new org.springframework.security.core.userdetails.User(userList.get(0).getUserName(), userList.get(0).getUserPasswd(), true, true, true, true, getAuthorities(userList.get(0).getUserAccess()));  
  
        } catch (Exception e) {  
            logger.error("Error in retrieving user");  
            throw new UsernameNotFoundException("Error in retrieving user");  
        }  
  
        return user;  
    }  
  
    /** 
     * 获得访问角色权限 
     *  
     * @param access 
     * @return 
     */  
    public Collection<GrantedAuthority> getAuthorities(Integer access) {
  
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);  
  
        // 所有的用户默认拥有ROLE_USER权限  
        logger.debug("Grant ROLE_USER to this user");  
        authList.add(new GrantedAuthorityImpl("ROLE_USER"));  
  
        // 如果参数access为1.则拥有ROLE_ADMIN权限  
        if (access.compareTo(999) == 0) {
            logger.debug("Grant ROLE_ADMIN to this user");  
            authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));  
        }  
  
        return authList;  
    }  
    
}