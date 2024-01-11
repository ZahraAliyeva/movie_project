package com.company.LetterboxdProject.service.impl;

import com.company.LetterboxdProject.dto.FilmListRequest;
import com.company.LetterboxdProject.dto.FilmListResponse;
import com.company.LetterboxdProject.entity.*;
import com.company.LetterboxdProject.repository.*;
import com.company.LetterboxdProject.service.inter.FilmListService;
import com.company.LetterboxdProject.service.inter.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.company.LetterboxdProject.util.Mapper.*;

@Service
@Transactional
@RequiredArgsConstructor
public class FilmListServiceImpl implements FilmListService {

    private final FilmListRepository filmListRepository;
    private final UserRepository userRepository;
    private final FilmListVisibleToRepository filmListVTRepository;
    private final FilmRepository filmRepository;
    private final TagService tagService;
    private final FilmNameRepository filmNameRepository;

    @Override
    public FilmListResponse createFilmList(Long userId, FilmListRequest filmListRequest) {
        final FilmList filmList = toModel(filmListRequest);
        final User user = userRepository.findById(userId).get();
        final FilmListVisibleTo filmListVisibleTo = filmListVTRepository.findById(filmListRequest.getFilmListVisibleTo()).get();

        final Set<Film> films = filmRepository.findAllByIds(filmListRequest.getFilms());
        final Set<Tags> tags = tagService.saveTags(filmListRequest.getTags());

        filmList.setCreatedDate(LocalDate.now());
        filmList.setUser(user);
        filmList.setFilmListVisibleTo(filmListVisibleTo);
        filmList.setFilms(films);
        filmList.setTags(tags);

        FilmListResponse response = toResponse(filmListRepository.save(filmList));
        response.setUsername(user.getUserName());

        return response;
    }

    @Override
    public FilmListResponse editFilmList(Long userId, Long listId, FilmListRequest filmListRequest) {
        final FilmList filmList = this.validateIfPresent(listId);

        map(filmListRequest, filmList);

        final FilmListVisibleTo filmListVisibleTo = filmListVTRepository.findById(filmListRequest.getFilmListVisibleTo()).get();

        final Set<Film> films = filmRepository.findAllByIds(filmListRequest.getFilms());
        final Set<Tags> tags = tagService.saveTags(filmListRequest.getTags());

        filmList.setUpdatedDate(LocalDate.now());
        filmList.setFilmListVisibleTo(filmListVisibleTo);
        filmList.setFilms(films);
        filmList.setTags(tags);

        FilmListResponse response = toResponse(filmListRepository.save(filmList));
        response.setUsername(userRepository.findById(listId).get().getUserName());

        return response;
    }

    @Override
    public String addFilm(Long userId, Long filmId, Long listId) {
        try {
            final FilmList filmList = this.validateIfPresent(listId);
            final Film film = filmRepository.findById(filmId).get();

            final Set<Film> films = filmRepository.findFilmByList(listId);
            films.add(film);

            filmList.setFilms(films);
            filmList.setUpdatedDate(LocalDate.now());
            filmListRepository.save(filmList);

            return filmNameRepository
                    .findFilmNameByLanguageIsAndFilmAndId("English", filmId) +
                    " was added to your list(" + filmList.getListName() +")";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String deleteFilm(Long userId, Long filmId, Long listId) {
        try {
            final FilmList filmList = this.validateIfPresent(listId);
            final Film film = filmRepository.findById(filmId).get();

            final Set<Film> films = filmRepository.findFilmByList(listId);

            films.remove(film);

            filmList.setFilms(films);
            filmList.setUpdatedDate(LocalDate.now());
            filmListRepository.save(filmList);

            return filmNameRepository
                    .findFilmNameByLanguageIsAndFilmAndId("English", filmId) +
                    " was deleted from your list(" + filmList.getListName() +")";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteFilmList(Long userId, Long listId) {
        final FilmList filmList = this.validateIfPresent(listId);
        filmListRepository.delete(filmList);
    }

    @Override
    public List<FilmListResponse> getFilmListByUser(Long userId) {
        try{
            Set<FilmList> filmLists = filmListRepository.findByUser(userId);

            return filmLists.stream().filter(fl -> fl !=null).map(fl -> {
                FilmListResponse response = toResponse(fl);
                response.setUsername(userRepository.findById(userId).get().getUserName());
                return response;
            }).toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FilmListResponse getFilmList(Long userId, Long listId) {
        try {
            final FilmList filmList = this.validateIfPresent(listId);
            FilmListResponse response = toResponse(filmList);
            response.setUsername(userRepository.findById(userId).get().getUserName());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private FilmList validateIfPresent(final Long id) {
        final Optional<FilmList> filmList = filmListRepository.findById(id);
        return filmList.get();
    }
}
