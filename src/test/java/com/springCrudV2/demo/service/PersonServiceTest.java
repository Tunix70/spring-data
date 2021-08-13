package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.PersonRepository;
import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.dto.PersonDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.entity.Person;
import com.springCrudV2.demo.exception.DepartmentNotFoundException;
import com.springCrudV2.demo.exception.DocumentNotFoundException;
import com.springCrudV2.demo.exception.LanguageNotFoundException;
import com.springCrudV2.demo.exception.PersonNotFoundException;
import com.springCrudV2.demo.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    private final Long ID = 1L;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonMapper personMapper;
    @Mock
    private DocumentService documentService;
    @Mock
    private DepartmentService departmentService;
    @Mock
    private LanguageService languageService;

    @InjectMocks
    private PersonService personService;

    @Test
    void shouldGetAllListPerson() {
        //given
        List<Person> personList = new ArrayList<>();
        Person person = getPersonFirst();
        Person person1 = getPersonSecond();

        personList.add(person);
        personList.add(person1);

        List<PersonDto> expectList = new ArrayList<>();
        PersonDto personDto = getPersonDtoFirst();
        PersonDto personDto1 = getPersonDtoSecond();

        expectList.add(personDto);
        expectList.add(personDto1);

        //when
        when(personRepository.findAll()).thenReturn(personList);
        when(personMapper.mapToPersonDto(person)).thenReturn(personDto);
        when(personMapper.mapToPersonDto(person1)).thenReturn(personDto1);
        List<PersonDto> resultList = personService.getAll();

        //than
        assertThat(resultList).isEqualTo(expectList);
        verify(personMapper, times(0)).mapToPersonEntity(any());
        verify(personMapper, times(2)).mapToPersonDto(any());
        verify(personRepository, times(1)).findAll();
    }

    @Test
    void shouldGetPeronByIdIfIdValid() {
        //given
        Person person = getPersonFirst();
        PersonDto expected = getPersonDtoFirst();

        //when
        when(personRepository.findById(ID)).thenReturn(java.util.Optional.of(person));
        when(personMapper.mapToPersonDto(person)).thenReturn(expected);
        PersonDto result = personService.getPersonById(ID);

        //than
        assertThat(result).isEqualTo(expected);
        verify(personRepository, times(1)).findById(ID);
        verify(personMapper, times(1)).mapToPersonDto(person);
        verify(personMapper, times(0)).mapToPersonEntity(any());
    }

    @Test
    void shouldThrowWhenGetPersonByIdIfIdInvalid() {
        //when
        when(personRepository.findById(ID)).thenThrow(new PersonNotFoundException(ID));

        //than
        assertThatThrownBy(() -> personService.getPersonById(ID))
                .isInstanceOf(PersonNotFoundException.class);
        verify(personMapper, times(0)).mapToPersonDto(any());
        verify(personMapper, times(0)).mapToPersonEntity(any());
    }

    @Test
    void shouldSavePersonAndReturnSavePerson() {
        //given
        Person person = getPersonFirst();
        PersonDto expected = getPersonDtoFirst();

        //when
        when(personMapper.mapToPersonEntity(expected)).thenReturn(person);
        when(personRepository.save(person)).thenReturn(person);
        when(documentService.isExistById(any())).thenReturn(true);
        when(languageService.isExistById(any())).thenReturn(true);
        when(departmentService.isExistById(any())).thenReturn(true);
        PersonDto result = personService.save(expected);

        //than
        assertThat(result).isEqualTo(expected);
        verify(personRepository, times(1)).save(person);
        verify(personMapper, times(1)).mapToPersonEntity(expected);
        verify(personMapper, times(0)).mapToPersonDto(any());
        verify(documentService, times(1)).isExistById(any());
        verify(languageService, times(2)).isExistById(any());
        verify(departmentService, times(1)).isExistById(any());
    }

    @Test
    void shouldRemovePersonByIdIfItExistInDataBase() {
        //when
        personService.deleteById(ID);

        //than
        verify(personRepository, times(1)).deleteById(ID);
    }

    @Test
    void shouldFillAllFieldsToPersonFromDtoAndRetutnPerson() {
        //given
        Department department = getDepartment();
        DepartmentDto departmentDto = getDepartmentDto();

        Document document = getDocument();
        DocumentDto documentDto = getDocumentDto();

        PersonDto personDto = getPersonDtoFirst();
        Person person = getPersonFirst();

        //when
        when(personMapper.mapToPersonEntity(personDto)).thenReturn(person);
        when(documentService.getDocumentById(personDto.getDocument())).thenReturn(documentDto);
        when(documentService.getEntity(documentDto)).thenReturn(document);
        when(departmentService.getDepartmentById(personDto.getDepartment())).thenReturn(departmentDto);
        when(departmentService.getEntity(departmentDto)).thenReturn(department);

        Person result = personService.fillFieldsPerson(personDto);

        //than
        assertThat(result).isEqualTo(person);
        assertThat(person.getId()).isEqualTo(personDto.getId());
        assertThat(person.getFirst_name()).isEqualTo(personDto.getFirst_name());
        assertThat(person.getSecond_name()).isEqualTo(personDto.getSecond_name());
        assertThat(person.getBirthday()).isEqualTo(personDto.getBirthday());
        assertThat(person.getDocument()).isEqualTo(documentService.getEntity(documentDto));
        assertThat(person.getDepartment()).isEqualTo(departmentService.getEntity(departmentDto));

        verify(personMapper,times(0)).mapToPersonDto(person);
        verify(personMapper,times(1)).mapToPersonEntity(personDto);
        verify(documentService, times(1)).getDocumentById(any());
        verify(documentService, times(2)).getEntity(documentDto);
        verify(departmentService, times(1)).getDepartmentById(any());
        verify(departmentService, times(2)).getEntity(departmentDto);
        verify(languageService,times(2)).getLanguageById(any());
        verify(languageService,times(2)).getEntity(any());
    }

    @Test
    void shouldTrueIfAllDtoInPersonIsValid() {
        //given
        PersonDto personDto = getPersonDtoFirst();

        //when
        when(documentService.isExistById(any())).thenReturn(true);
        when(languageService.isExistById(any())).thenReturn(true);
        when(departmentService.isExistById(any())).thenReturn(true);

        boolean result = personService.isValidDto(personDto);

        //than
        assertThat(result).isEqualTo(true);
        verify(documentService, times(1)).isExistById(any());
        verify(languageService, times(2)).isExistById(any());
        verify(departmentService, times(1)).isExistById(any());
    }

    @Test
    void shouldThrowExceptionIfDocumentInPersonIsInvalid() {
        //given
        PersonDto personDto = getPersonDtoFirst();

        //when
        when(documentService.isExistById(any())).thenReturn(false);

        //than
        assertThatThrownBy(() -> personService.isValidDto(personDto))
                .isInstanceOf(DocumentNotFoundException.class);
        verify(documentService, times(1)).isExistById(any());
        verify(languageService, times(0)).isExistById(any());
        verify(departmentService, times(0)).isExistById(any());
    }

    @Test
    void shouldThrowExceptionIfDepartmentInPersonIsInvalid() {
        //given
        PersonDto personDto = getPersonDtoFirst();

        //when
        when(documentService.isExistById(any())).thenReturn(true);
        when(departmentService.isExistById(any())).thenReturn(false);

        //than
        assertThatThrownBy(() -> personService.isValidDto(personDto))
                .isInstanceOf(DepartmentNotFoundException.class);
        verify(documentService, times(1)).isExistById(any());
        verify(languageService, times(0)).isExistById(any());
        verify(departmentService, times(1)).isExistById(any());
    }

    @Test
    void shouldThrowExceptionIfLanguageInPersonIsInvalid() {
        //given
        PersonDto personDto = getPersonDtoFirst();

        //when
        when(documentService.isExistById(any())).thenReturn(true);
        when(departmentService.isExistById(any())).thenReturn(true);
        when(languageService.isExistById(any())).thenReturn(false);

        //than
        assertThatThrownBy(() -> personService.isValidDto(personDto))
                .isInstanceOf(LanguageNotFoundException.class);
        verify(documentService, times(1)).isExistById(any());
        verify(languageService, times(1)).isExistById(any());
        verify(departmentService, times(1)).isExistById(any());
    }

    private Person getPersonFirst() {
        Department department = new Department(1L, "Department");
        Document document = new Document("wer-123-fsd", new Date(12335L));

        Language language = new Language(1L, "RU");
        Language language1 = new Language(2L,"EU");

        Set<Language> languageSet = new HashSet<>();
        languageSet.add(language);
        languageSet.add(language1);
        return new Person(1L, "Pavel", "Morozov", new Date(1233L), department, languageSet, document);
    }

    private Person getPersonSecond() {
        Department department = new Department(1L, "Main Department");
        Document document = new Document("sdf-234-hhh", new Date(12335L));

        Language language = new Language(1L, "IT");
        Language language1 = new Language(2L,"GR");

        Set<Language> languageSet = new HashSet<>();
        languageSet.add(language);
        languageSet.add(language1);
        return new Person(2L, "Oleg", "Ivanov", new Date(2234L), department, languageSet, document);
    }

    private PersonDto getPersonDtoFirst() {
        Department department = new Department(1L, "Department");
        Document document = new Document("wer-123-fsd", new Date(12335L));

        Set<Long> languageSet = new HashSet<>();
        languageSet.add(1L);
        languageSet.add(2L);
        return new PersonDto(1L, "Pavel", "Morozov", new Date(1233L), department.getId(), languageSet, document.getId());
    }

    private PersonDto getPersonDtoSecond() {
        Department department = new Department(1L, "Main Department");
        Document document = new Document("sdf-234-hhh", new Date(12335L));

        Set<Long> languageSet = new HashSet<>();
        languageSet.add(1L);
        languageSet.add(2L);
        return new PersonDto(2L, "Oleg", "Ivanov", new Date(2234L), department.getId(), languageSet, document.getId());
    }

    private Department getDepartment() {
        return new Department(1L, "Department");
    }

    private Document getDocument() {
        return new Document("wer-123-fsd", new Date(12335L));
    }

    private Set<Language> getSetLanguages() {
        Set<Language> languageSet = new HashSet<>();
        languageSet.add(new Language(1L, "RU"));
        languageSet.add(new Language(2L,"EU"));

        return languageSet;
    }

    private DepartmentDto getDepartmentDto() {
        return new DepartmentDto(1L, "Department");
    }

    private DocumentDto getDocumentDto() {
        return new DocumentDto("wer-123-fsd", new Date(12335L));
    }

    private Set<Long> getSetLanguagesDto() {
        Set<Long> languageSet = new HashSet<>();
        languageSet.add(1L);
        languageSet.add(2L);

        return languageSet;
    }
}