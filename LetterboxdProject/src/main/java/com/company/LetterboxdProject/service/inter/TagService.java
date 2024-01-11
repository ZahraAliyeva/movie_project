package com.company.LetterboxdProject.service.inter;

import com.company.LetterboxdProject.entity.Tags;

import java.util.Set;

public interface TagService {

    Set<Tags> saveTags(final Set<String> tags);
}
