package com.ubik.formation.library2.service;

import com.ubik.formation.library2.model.Tag;
import com.ubik.formation.library2.repository.TagDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    @Transactional
    public Tag findOrCreateByName(String name) {
        return tagDao.findByName(name)
                .orElseGet(() -> {
                    Tag newTag = new Tag();
                    newTag.setName(name);
                    tagDao.save(newTag);
                    return newTag;
                });
    }


}
