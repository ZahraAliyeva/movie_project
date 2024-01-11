package com.company.LetterboxdProject.controller;

import com.company.LetterboxdProject.dto.UserRequest;
import com.company.LetterboxdProject.dto.UserResponse;
import com.company.LetterboxdProject.service.inter.UserServiceInter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceInter userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long userId) {
        UserResponse response = userService.getUser(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/avatar/{id}")
    public ResponseEntity<?> getUserAvatar(@PathVariable("id") Long userId) {
        byte[] response = userService.getUserAvatar(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<?> getFollowers(@PathVariable("userId") Long user) {
        Set<UserResponse> userResponse = userService.getFollowerList(user);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/followings/{userId}")
    public ResponseEntity<?> getFollowings(@PathVariable("userId") Long user) {
        Set<UserResponse> userResponse = userService.getFollowingList(user);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping("/from/{from}/to/{to}")
    public ResponseEntity<?> followUser(@PathVariable("from") Long fromId,
                                        @PathVariable("to") Long toId) {
        userService.followUser(fromId, toId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/avatar/{userId}")
    public ResponseEntity<?> saveUserAvatar(@PathVariable("userId") Long userId,
                                            @RequestPart MultipartFile file) {
        try{
            String response = userService.saveUserAvatar(userId, file.getBytes());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            log.error("Exception while converting image", e);
            return null;
        }
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<?> editUser(@PathVariable("userId") Long userId,
                                      @RequestBody UserRequest userRequest) {
        UserResponse response = userService.editUser(userId, userRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/unfollow/from/{fromId}/to/{toId}")
    public ResponseEntity<?> unfollowing(@PathVariable("fromId") Long fromId,
                                         @PathVariable("toId") Long toId) {
        userService.unfollowUser(fromId, toId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/avatar/{userId}")
    public ResponseEntity<?> deleteUserAvatar(@PathVariable("userId") Long userId) {
        String response = userService.deleteUserAvatar(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
