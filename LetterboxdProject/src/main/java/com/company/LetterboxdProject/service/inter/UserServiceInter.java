package com.company.LetterboxdProject.service.inter;

import com.company.LetterboxdProject.dto.UserRequest;
import com.company.LetterboxdProject.dto.UserResponse;

import java.util.Set;

public interface UserServiceInter {

    UserResponse editUser(final Long userId, final UserRequest userRequest);
    void followUser(final Long fromId, final Long toId);
    Set<UserResponse> getFollowerList(final Long userId);
    Set<UserResponse> getFollowingList(final Long userId);
    void unfollowUser(final Long fromId, final Long toId);
    UserResponse getUser(final Long userId);
    String saveUserAvatar(final Long userId, byte[] image);
    String deleteUserAvatar(final Long userId);
    byte[] getUserAvatar(final Long userId);
}
