package com.rvfs.challenge.mcc.currency.service;

import com.rvfs.challenge.mcc.currency.dto.PostalAddressDTO;
import com.rvfs.challenge.mcc.currency.dto.UserDTO;
import com.rvfs.challenge.mcc.currency.model.PostalAddress;
import com.rvfs.challenge.mcc.currency.model.User;
import com.rvfs.challenge.mcc.currency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO save(UserDTO userData) {

        try {

            PostalAddress address = new PostalAddress(userData.getAddress().getStreet(),userData.getAddress().getZipCode(), userData.getAddress().getCity(), userData.getAddress().getCountry());
            User user = new User(userData.getId(), userData.getEmail(), userData.getBirth(), address);
            userRepository.save(user);

        }catch (Exception e){
            throw e;
        }

        return userData;
    }

    @Override
    public UserDTO findByEmail(String email) {

        UserDTO userData = null;
        try{

            User foundUser = userRepository.findByEmail(email);

            PostalAddressDTO postalAddress = new PostalAddressDTO(foundUser.getAddress().getStreet(),foundUser.getAddress().getZipCode(), foundUser.getAddress().getCity(), foundUser.getAddress().getCountry());
            userData = new UserDTO(foundUser.getId(), foundUser.getEmail(), foundUser.getBirth(), postalAddress);

        }catch (Exception e){
            throw e;
        }
        return userData;
    }
}
