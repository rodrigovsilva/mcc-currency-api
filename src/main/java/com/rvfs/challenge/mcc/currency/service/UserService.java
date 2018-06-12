package com.rvfs.challenge.mcc.currency.service;

import com.rvfs.challenge.mcc.currency.dto.UserDTO;

public interface UserService {

    public UserDTO save(UserDTO userData);

    public UserDTO findByEmail(String email);


}
