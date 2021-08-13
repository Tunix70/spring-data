package com.springCrudV2.demo.controller;

import com.springCrudV2.demo.dto.LanguageDto;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;


@RestController
@RequestMapping("/languages")
public class LanguageController {
    private LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<LanguageDto>> getAll() {
        Set<LanguageDto> languageList = languageService.getAll();
        return languageList != null
                ? new ResponseEntity<>(languageList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LanguageDto> getById(@PathVariable("id") @Min(1) @NotNull Long id) {
        LanguageDto language = languageService.getLanguageById(id);
        return new ResponseEntity<>(language, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LanguageDto> save(@RequestBody LanguageDto dto) {
        return new ResponseEntity<>(languageService.save(dto), HttpStatus.OK);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LanguageDto> update(@RequestBody LanguageDto dto) {
        languageService.isValidId(dto.getId());
        return new ResponseEntity<>(languageService.save(dto), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Language> deleteById(@PathVariable("id") @Min(1) @NotNull Long id) {
        languageService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
