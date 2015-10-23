package com.hz.wechatproject.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hz.wechatproject.db.pojo.User;
import com.hz.wechatproject.db.service.UserService;
import com.hz.wechatproject.utils.CommonUtil;


@Service("customUserAccessService")
public class CustomUserAccessServiceImpl implements UserDetailsService {  
	
	
	@Resource(name="userService")
	UserService userService;
	
    private static Logger logger = Logger.getLogger(CustomUserAccessServiceImpl.class);  
  
    public UserDetails loadUserByUsername(String username)  
            throws UsernameNotFoundException, DataAccessException {  
  
        UserDetails user = null;  
  
        try {  
  
            // 搜索数据库以匹配用户登录名.  
            // 我们可以通过dao使用JDBC来访问数据库  
        	User foundedUser = userService.getUserByName(username);
        	
        	if(foundedUser==null){
        		return user;
        	}
            // Populate the Spring User object with details from the dbUser  
            // Here we just pass the username, password, and access level  
            // getAuthorities() will translate the access level to the correct  
            // role type  
            user = new org.springframework.security.core.userdetails.User(foundedUser.getUserName(), foundedUser.getUserPasswd(), true, true, true, true, getAuthorities(foundedUser.getUserAccess()));  
  
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
    public Collection<GrantedAuthority> getAuthorities(String access) {
  
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
        
        // 所有的用户默认拥有ROLE_USER权限  
        logger.debug("Grant ROLE_USER to this user");  
        authList.add(new SimpleGrantedAuthority(CommonUtil.SecurityUtil.ACCESS_ROLE_ROLE_USER));  

        if(CommonUtil.isEmptyString(access)){
        	return authList;
        }
        // 如果参数access为1.则拥有ROLE_ADMIN权限
        List<String> rights=CommonUtil.splitStringAsList(access,CommonUtil.SecurityUtil.ACCESS_STRING_SEPERATOR);
        if(rights!=null&&!rights.isEmpty()){
        	 for(String right:rights){
             	switch(right){
             		case CommonUtil.SecurityUtil.ACCESS_NUM_ROLE_ADMIN:
             			logger.debug("Grant "+CommonUtil.SecurityUtil.ACCESS_ROLE_ROLE_ADMIN+" to this user");  
                         authList.add(new SimpleGrantedAuthority(CommonUtil.SecurityUtil.ACCESS_ROLE_ROLE_ADMIN));  
             			break;
             	}
             }
        }
  
        return authList;  
    }  
    
}