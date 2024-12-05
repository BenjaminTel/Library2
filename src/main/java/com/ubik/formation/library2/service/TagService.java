package com.ubik.formation.library2.service;

import com.ubik.formation.library2.model.Tag;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

public interface TagService {

    Tag findOrCreateByName(String tagName);
}
