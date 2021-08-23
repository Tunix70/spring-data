package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.DocumentRepository;
import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.exception.DocumentNotFoundException;
import com.springCrudV2.demo.mapper.DocumentMapper;
import com.springCrudV2.demo.validator.DocumentDtoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {
    private static final String ID1 = "wer-2124-gg";
    private static final String ID2 = "sdf-0022-as";
    private static final Date DATE1 = new Date(12335L);
    private static final Date DATE2 = new Date(54635L);
    private static final Document document = new Document(ID1, DATE1);;
    private static final Document document1 = new Document(ID2, DATE2);;
    private static final DocumentDto dto = new DocumentDto(ID1, DATE1);;
    private static final DocumentDto dto1 = new DocumentDto(ID2, DATE2);;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private DocumentMapper documentMapper;

    @InjectMocks
    private DocumentService documentService;

    @Test
    void shouldGetAllListDocument() {
        //given
        List<Document> documentList = new ArrayList<>();
        documentList.add(document);
        documentList.add(document1);

        List<DocumentDto> extectedList = new ArrayList<>();
        extectedList.add(dto);
        extectedList.add(dto1);

        //when
        when(documentRepository.findAll()).thenReturn(documentList);
        when(documentMapper.mapToDocumentDto(document)).thenReturn(dto);
        when(documentMapper.mapToDocumentDto(document1)).thenReturn(dto1);
        List<DocumentDto> resultList = documentService.getAll();

        //then
        assertThat(extectedList).isEqualTo(resultList);
        verify(documentMapper, times(1)).mapToDocumentDto(document);
        verify(documentMapper, times(1)).mapToDocumentDto(document1);
        verify(documentRepository, times(1)).findAll();
    }

    @Test
    void shouldGetDocumentByIdIfIdValid() {
        //when
        when(documentRepository.findById(ID1)).thenReturn(java.util.Optional.of(document));
        when(documentMapper.mapToDocumentDto(document)).thenReturn(dto);
        DocumentDto result = documentService.getDocumentById(ID1);

        //than
        assertThat(result).isEqualTo(dto);
        verify(documentRepository, times(1)).findById(ID1);
        verify(documentMapper, times(1)).mapToDocumentDto(document);
    }

    @Test
    void shouldThrowWhenGetDocumentByIdIfIdInvalid() {
        //when
        when(documentRepository.findById(ID1)).thenReturn(java.util.Optional.empty());

        //than
        assertThatThrownBy(() -> documentService.getDocumentById(ID1))
                .isInstanceOf(DocumentNotFoundException.class);
        verify(documentMapper, times(0)).mapToDocumentEntity(any());
        verify(documentMapper, times(0)).mapToDocumentDto(any());
    }

    @Test
    void shouldSaveDocumentAndReturnSaveDocument() {
        //when
        when(documentMapper.mapToDocumentEntity(dto)).thenReturn(document);
        when(documentRepository.save(document)).thenReturn(document);
        DocumentDto result = documentService.save(dto);

        //than
        assertThat(result).isEqualTo(dto);
        verify(documentRepository, times(1)).save(document);
        verify(documentMapper, times(1)).mapToDocumentEntity(dto);
        verify(documentMapper, times(0)).mapToDocumentDto(any());
    }

    @Test
    void shouldRemoveDocumentByIdIfItExistInDataBase() {
        //when
        when(documentRepository.existsById(ID1)).thenReturn(true);
        documentService.deleteByNumber(ID1);

        //than
        verify(documentRepository, times(1)).existsById(any());
        verify(documentRepository, times(1)).deleteById(ID1);
    }

    @Test
    void shouldFailWhenRemoveDepartmentByIdIfItNotExistInDataBase() {
        //when
        when(documentRepository.existsById(ID1)).thenReturn(false);

        //than
        assertThatThrownBy(() -> documentService.deleteByNumber(ID1))
                .isInstanceOf(DocumentNotFoundException.class);
    }

    @Test
    void shouldReturnTrueIfDocumentIsExistById() {
        //when
        when(documentRepository.existsById(any())).thenReturn(true);
        documentService.isExistById(ID1);

        //than
        verify(documentRepository, times(1)).existsById(ID1);
    }
}