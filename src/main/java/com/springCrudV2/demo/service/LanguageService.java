package com.springCrudV2.demo.service;


import com.springCrudV2.demo.dao.LanguageRepository;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.entity.Language;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    private LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    public Language getLanguageById(Long id) {
        return languageRepository.findById(id).orElse(null);

    }

    public Language save(Language language) {
        return languageRepository.save(language);
    }

    public void deleteById(Long id) {
        languageRepository.deleteById(id);
    }
}
