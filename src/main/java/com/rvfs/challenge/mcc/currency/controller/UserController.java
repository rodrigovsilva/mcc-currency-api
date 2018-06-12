package com.rvfs.challenge.mcc.currency.controller;

import com.rvfs.challenge.mcc.currency.dto.ConversionDTO;
import com.rvfs.challenge.mcc.currency.dto.UserDTO;
import com.rvfs.challenge.mcc.currency.service.CurrencyConverterService;
import com.rvfs.challenge.mcc.currency.util.ObjectParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * Logger definition.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity<Object> register(UserDTO userData) {

        ResponseEntity<Object> responseEntity = null;

        try {

            LOGGER.info("register - RequestBody {}", ObjectParserUtil.getInstance().toString(userData));
            responseEntity = new ResponseEntity<>(userData, HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseEntity = new ResponseEntity<>("Error during user registration", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

}
