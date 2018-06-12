package com.rvfs.challenge.mcc.currency.controller;

import com.rvfs.challenge.mcc.currency.dto.UserDTO;
import com.rvfs.challenge.mcc.currency.service.UserService;
import com.rvfs.challenge.mcc.currency.util.ObjectParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/api/user")
public class UserController {

    /**
     * Logger definition.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @Autowired
    UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Object> register(@RequestBody UserDTO userData) {

        ResponseEntity<Object> responseEntity = null;

        try {

            LOGGER.info("register - RequestBody {}", ObjectParserUtil.getInstance().toString(userData));

            userService.save(userData);

            responseEntity = new ResponseEntity<>(userData, HttpStatus.CREATED);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseEntity = new ResponseEntity<>("Error during user registration", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<Object> findByEmail(@RequestParam("email") String email) {

        ResponseEntity<Object> responseEntity = null;

        try {

            LOGGER.info("findByEmail - RequestBody {}", email);
            UserDTO userData = userService.findByEmail(email);
            responseEntity = new ResponseEntity<>(userData, HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseEntity = new ResponseEntity<>("Error during user search", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }
}
