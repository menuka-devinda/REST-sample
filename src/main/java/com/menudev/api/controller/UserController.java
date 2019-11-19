package com.menudev.api.controller;

import java.rmi.UnexpectedException;
import java.util.List;

import com.menudev.api.MicrmeterRestHandler;
import io.micrometer.core.instrument.MeterRegistry;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.menudev.api.domain.User;
import com.menudev.api.service.UserService;
import com.menudev.api.util.ResponseDetail;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    MicrmeterRestHandler micrmeterRestHandler;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<User>> getUsers() {
        LOG.info("get all users");
        micrmeterRestHandler.handleMessage();
//		MeterRegistryCustomizer meterRegistryCustomizer = registry -> {
//			meterRegistry.counter("testCounter","tag1", "tagVal").increment();
//
//		};
//	//	meterRegistry.
//		//meterRegistryCustomizer.
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") final Integer id) throws UnexpectedException {
        LOG.info("get user by user id: " + id);
        if (null == id) {
            throw new IllegalArgumentException("Id is invalid ");
        }

        User user = userService.getUserById(id).orElseThrow();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDetail> createUser(@RequestBody final User user) throws UnexpectedException {
        LOG.info("create new user ");
        User createdUser = userService.createUser(user);
        if (null != createdUser) {
            LOG.info("new user with id " + createdUser.getId() + " is created successfully");

            return new ResponseEntity<>(new ResponseDetail(204, "User created succefully"), HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDetail> update(@PathVariable("id") final Integer id, @RequestBody final User user) {
        LOG.info("update user with id: " + id);
        userService.updateUser(id, user);
        LOG.info("user with id: " + id + " is updated successfully");
        return new ResponseEntity<>(new ResponseDetail(204, "User modified succefully"), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDetail> delete(@PathVariable("id") final Integer id) {
        LOG.info("delete user with id: " + id);
        userService.deleteUser(id);
        LOG.info("user with id: " + id + " is deleted successfully");
        return new ResponseEntity<>(new ResponseDetail(204, "User deleted succefully"), HttpStatus.NO_CONTENT);
    }

}
