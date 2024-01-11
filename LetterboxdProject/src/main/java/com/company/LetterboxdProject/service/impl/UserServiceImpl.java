package com.company.LetterboxdProject.service.impl;

import com.company.LetterboxdProject.dto.UserRequest;
import com.company.LetterboxdProject.dto.UserResponse;
import com.company.LetterboxdProject.entity.*;
import com.company.LetterboxdProject.repository.*;
import com.company.LetterboxdProject.service.inter.StorageServiceInter;
import com.company.LetterboxdProject.service.inter.UserServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.company.LetterboxdProject.util.Mapper.map;
import static com.company.LetterboxdProject.util.Mapper.toResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInter {

    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;
    private final PronounRepository pronounRepository;
    private final ReplyRepository replyRepository;
    private final FilmRateHistoryRepository likedFilmRepository;
    private final FavoriteFilmRepository favoriteFilmRepository;
    private final FilmRepository filmRepository;
    private final UserPhotoRepository userPhotoRepository;
    private final StorageServiceInter storageService;

    @Override
    public UserResponse editUser(Long id, UserRequest userRequest) {
        User user = this.validateIfPresent(id);
        map(userRequest, user);
//-----------------------------------Fav Films--------------------------------------------------------
        FavoriteFilm favoriteFilm = favoriteFilmRepository.findByUser(id);

        Set<Film> notExistFilms = filmRepository.findAllByIds(userRequest.getFavFilms())
                .stream()
                .filter(f -> !favoriteFilm.getFilms().contains(f)).collect(Collectors.toSet());

        Set<Film> existFilms = filmRepository.findAllByIds(userRequest.getFavFilms())
                .stream()
                .filter(f -> favoriteFilm.getFilms().contains(f)).collect(Collectors.toSet());

        favoriteFilm.getFilms().addAll(notExistFilms);
        favoriteFilm.getFilms().removeAll(existFilms);

        favoriteFilmRepository.save(favoriteFilm);
//-----------------------------------Fav Films--------------------------------------------------------
        user.setPronoun(pronounRepository.findById(userRequest.getPronoun()).get());
        user.setReply(replyRepository.findById(userRequest.getReply()).get());
        user.setFavoriteFilm(favoriteFilm);

        UserResponse userResponse = toResponse(user);

        Set<Film> likedFilm = likedFilmRepository.findTop4ByOrderByLikedDateDesc(id);
        Set<Long> likedFilmIds = likedFilm.stream().map(lf -> lf.getId()).collect(Collectors.toSet());
        userResponse.setRecentLikes(likedFilmIds);

        Set<Film> favFilm = favoriteFilmRepository.findByUserId(id);
        Set<Long> favFilmIds = favFilm.stream().map(ff -> ff.getId()).collect(Collectors.toSet());
        userResponse.setFavFilms(favFilmIds);

        return userResponse;
    }
    @Override
    public void followUser(Long fromId, Long toId) {
        final Follower follower = new Follower();
        final User from = this.validateIfPresent(fromId);
        final User to = this.validateIfPresent(toId);

        follower.setFrom(from);
        follower.setTo(to);
        follower.setFollowingDate(LocalDate.now());

        followerRepository.save(follower);
    }
    @Override
    public Set<UserResponse> getFollowerList(Long userId) {
        final User user = this.validateIfPresent(userId);
        Set<User> followers = followerRepository.getFollower(user);

        return followers.stream().filter(f -> f != null).map(f -> {
            UserResponse userResponse = toResponse(f);
            return userResponse;
        }).collect(Collectors.toSet()) ;
    }

    @Override
    public Set<UserResponse> getFollowingList(Long userId) {
        final User user = this.validateIfPresent(userId);
        Set<User> followings = followerRepository.getFollowing(user);

        return followings.stream().filter(f -> f != null).map(f -> {
            UserResponse userResponse = toResponse(f);
            return userResponse;
        }).collect(Collectors.toSet());
    }

    @Override
    public void unfollowUser(Long fromId, Long toId) {
        final User from = validateIfPresent(fromId);
        final User to = validateIfPresent(toId);

        Follower follower = followerRepository.findByFromAndTo(from, to);
        followerRepository.delete(follower);
    }

    @Override
    public UserResponse getUser(Long userId) {
        final User user = this.validateIfPresent(userId);
        UserResponse userResponse = toResponse(user);

        return userResponse;
    }

    @Override
    public String saveUserAvatar(Long userId, byte[] image) {

        if(userPhotoRepository.findByUser(userId) != null) {
            final UserPhoto userPhoto = userPhotoRepository.findByUser(userId);

            storageService.removeImage(userPhoto.getUuid());

            final UUID uuid = UUID.randomUUID();
            final URL url = storageService.uploadImage(uuid, image);

            userPhoto.setUserProfilePhotoUrl("photo" + userPhoto.getUser().getId());
            userPhoto.setUploadDate(LocalDate.now());
            userPhoto.setUuid(uuid);

            userPhotoRepository.save(userPhoto);
        } else {
            final User user = this.validateIfPresent(userId);
            final UserPhoto userPhoto = new UserPhoto();

            final UUID uuid = UUID.randomUUID();
            final URL url = storageService.uploadImage(uuid, image);

            userPhoto.setUser(user);
            userPhoto.setUuid(uuid);
            userPhoto.setUserProfilePhotoUrl("photo" + user.getId());
            userPhoto.setUploadDate(LocalDate.now());

            userPhotoRepository.save(userPhoto);
        }


        return "Photo uploaded successfully";
    }

    @Override
    public String deleteUserAvatar(Long userId) {
        final UserPhoto userPhoto = userPhotoRepository.findByUser(userId);

        storageService.removeImage(userPhoto.getUuid());
        userPhotoRepository.save(userPhoto);

        return "Photo deleted successfully";
    }

    @Override
    public byte[] getUserAvatar(Long userId) {
        try{
            final UserPhoto userPhoto = userPhotoRepository.findByUser(userId);

            return storageService.downloadImage(userPhoto.getUuid());
        } catch(Exception e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    private User validateIfPresent(final Long id) {
        final Optional<User> user = userRepository.findById(id);
        return user.get();
    }
}
