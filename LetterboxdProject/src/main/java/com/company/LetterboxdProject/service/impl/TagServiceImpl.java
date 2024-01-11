package com.company.LetterboxdProject.service.impl;

import com.company.LetterboxdProject.entity.Tags;
import com.company.LetterboxdProject.repository.TagsRepository;
import com.company.LetterboxdProject.service.inter.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagsRepository tagsRepository;

    @Override
    public Set<Tags> saveTags(Set<String> tags) {
        Set<Tags> savedTags = tags.stream().filter(t -> !tagsRepository.existsTagsByTags(t)).map(t-> {
            Tags tag = new Tags();
            tag.setTags(t);

            return tagsRepository.save(tag);
        }).collect(Collectors.toSet());

        Set<Tags> allTags = tagsRepository.findByTags(tags);

        return allTags;
    }
}
