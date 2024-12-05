package com.ubik.formation.library2.repository;

import com.ubik.formation.library2.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagDao {

    Optional<Tag> findByName(String name);

    void save(Tag tag);
}
