package com.isakayabasi.worktypeschedule.service.impl;


import com.isakayabasi.worktypeschedule.dto.UserRegistrationDto;
import com.isakayabasi.worktypeschedule.model.Role;
import com.isakayabasi.worktypeschedule.model.User;
import com.isakayabasi.worktypeschedule.repository.UserRepository;
import com.isakayabasi.worktypeschedule.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto){

        User user = new User(
                registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()),
                registrationDto.isActive(),
                Arrays.asList(new Role("ROLE_USER")));

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id).get();
    }
    @Override
    public User getUserByEmail(String Email) {

        return userRepository.findByEmail(Email);
    }


    @Override
    public User updateUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if( user == null ){
            throw new UsernameNotFoundException("Invalid username or password.");
        }else{

            if(user.isActive()){
                // Email = username
                return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
                // FirstName == username
//                return new org.springframework.security.core.userdetails.User(user.getFirstName() ,user.getPassword(),mapRolesToAuthorities(user.getRoles()));
            }else{

                throw new UsernameNotFoundException("User Blocked by Admin");

            }
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles ){

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }





}