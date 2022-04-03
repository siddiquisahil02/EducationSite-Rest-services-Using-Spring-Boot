package com.camp.educationalsite.service;

import com.camp.educationalsite.config.CustomUserDetails;
import com.camp.educationalsite.model.Users;
import com.camp.educationalsite.repository.UsersRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private UsersRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.repo.findById(username).orElse(null);

        if(user==null){
            throw new UsernameNotFoundException("No user found.");
        }

        return new CustomUserDetails(user);
    }
    
}
