package com.acc.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acc.dao.LoginDao;
import com.acc.dto.LoginUser;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private LoginDao loginDao;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		LoginUser userDetails=loginDao.getUser(username);
			if(userDetails!= null&&userDetails.getEnterpriseId()!=null)
			{
				Set<GrantedAuthority> authorities=new HashSet<GrantedAuthority>();
				for(String role:userDetails.getRoles()){
				GrantedAuthority authority=new SimpleGrantedAuthority (role);
				authorities.add(authority);
				}
				User user= new User(String.valueOf(userDetails.getEnterpriseId()),userDetails.getPassword(), authorities);
				return user;
			}

			else{
				throw new UsernameNotFoundException("Invalid Username or Password");
			}

	}

}
