package com.example.csvReader.facade;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import com.example.csvReader.mapper.FolderMapper;
import com.example.csvReader.mapper.FolderMapperImpl;
import com.example.csvReader.model.FolderEntity;
import com.example.csvReader.repository.FolderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {FolderFacade.class, FolderMapperImpl.class, FolderMapper.class})
class FolderFacadeTest {

  @Mock(answer = Answers.CALLS_REAL_METHODS)
  private FolderMapper folderMapper;
  @Mock
  private FolderRepository folderRepository;
  @Captor
  private ArgumentCaptor<PageRequest> pageCaptor;

  @Autowired
  @InjectMocks
  private FolderFacade folderFacade;

  @Test
  void givenItemNameIsNull_thenCallFindAll() {

    Mockito.when(folderRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(new PageImpl<>(new CopyOnWriteArrayList<>()));
    folderFacade.findByName(null, null, null);

    Mockito.verify(folderRepository).findAll(Mockito.any(PageRequest.class));
  }

  @Test
  void givenItemNameIsNotNull_thenCallFindAll() {

    Mockito.when(folderRepository.findByName(Mockito.anyString(), Mockito.any(PageRequest.class))).thenReturn(new PageImpl<>(new CopyOnWriteArrayList<>()));
    folderFacade.findByName("name", null, null);

    Mockito.verify(folderRepository, Mockito.never()).findAll(Mockito.any(PageRequest.class));
    Mockito.verify(folderRepository, Mockito.times(1)).findByName(Mockito.anyString(), Mockito.any(PageRequest.class));
  }

  @Test
  void givenPageableIsNull_thenCallFindAllWithDefaultPageRequest() {

    Mockito.when(folderRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(new PageImpl<>(new CopyOnWriteArrayList<>()));
    folderFacade.findByName(null, null, null);

    Mockito.verify(folderRepository).findAll(pageCaptor.capture());

    Assertions.assertEquals(100, pageCaptor.getValue().getPageSize());
    Assertions.assertEquals(0, pageCaptor.getValue().getPageNumber());
  }

  @Test
  void givenPageableIsNotNull_thenCallFindAllWithDefaultPageRequest() {

    Mockito.when(folderRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(new PageImpl<>(new CopyOnWriteArrayList<>()));
    folderFacade.findByName(null, null, PageRequest.of(2, 50));

    Mockito.verify(folderRepository).findAll(pageCaptor.capture());

    Assertions.assertEquals(50, pageCaptor.getValue().getPageSize());
    Assertions.assertEquals(2, pageCaptor.getValue().getPageNumber());
  }

  @Test
  void givenSortAttributeIsNull_thenCallFindAllWithoutSort() {

    Mockito.when(folderRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(new PageImpl<>(new CopyOnWriteArrayList<>()));
    folderFacade.findByName(null, null, null);

    Mockito.verify(folderRepository).findAll(pageCaptor.capture());

    Assertions.assertTrue(pageCaptor.getValue().getSort().isEmpty());
  }

  @Test
  void givenSortAttributeIsPriority_thenCallFindAllSortByPriorityASC() {

    Mockito.when(folderRepository.findAll(Mockito.any(PageRequest.class))).thenAnswer(invocationOnMock -> {
      return new PageImpl<FolderEntity>(new CopyOnWriteArrayList<>(), invocationOnMock.getArgument(0), 0);
    });
    folderFacade.findByName(null, "priority", null);

    Mockito.verify(folderRepository).findAll(pageCaptor.capture());

    Assertions.assertFalse(pageCaptor.getValue().getSort().isEmpty());

    final List<Sort.Order> priority = List.of(new Sort.Order(Sort.Direction.ASC, "priority"));
    final List<Sort.Order> collect = pageCaptor.getValue().getSort().get().collect(Collectors.toList());
    Assertions.assertArrayEquals(priority.toArray(), collect.toArray());
  }

  @Test
  void givenSortAttributeIsName_thenCallFindAllSortByPriorityASC() {

    Mockito.when(folderRepository.findAll(Mockito.any(PageRequest.class))).thenAnswer(invocationOnMock -> {
      return new PageImpl<FolderEntity>(new CopyOnWriteArrayList<>(), invocationOnMock.getArgument(0), 0);
    });
    folderFacade.findByName(null, "name", null);

    Mockito.verify(folderRepository).findAll(pageCaptor.capture());

    Assertions.assertTrue(pageCaptor.getValue().getSort().isEmpty());
  }
}