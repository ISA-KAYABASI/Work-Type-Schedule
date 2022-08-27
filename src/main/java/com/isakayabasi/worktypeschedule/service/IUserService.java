package com.isakayabasi.worktypeschedule.service;


import com.isakayabasi.worktypeschedule.dto.UserRegistrationDto;
import com.isakayabasi.worktypeschedule.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IUserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);

    List<User> getAllUsers();

//    User addDayPlan(User user);

    User getUserById(Long id);
    User getUserByEmail(String Email);

    User updateUser(User user);



}
