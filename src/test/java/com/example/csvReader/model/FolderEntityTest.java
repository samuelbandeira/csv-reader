package com.example.csvReader.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class FolderEntityTest {

  @Test
  void givenFolderHasNoParent_whenGetPathName_ThenReturnName() {
    final FolderEntity folderRoot = FolderEntity.builder().name("root").build();

    Assertions.assertEquals("root", folderRoot.getPathName());
  }

  @Test
  void givenFolderHasParent_whenGetPathName_ThenReturnName() {
    final FolderEntity folderRoot = FolderEntity.builder().name("root").build();
    final FolderEntity folderLeaf1 = FolderEntity.builder().name("leaf1").parent(folderRoot).build();

    Assertions.assertEquals("root", folderRoot.getPathName());
    Assertions.assertEquals("root/leaf1", folderLeaf1.getPathName());
  }

  @Test
  void givenFolderHas2Parent_whenGetPathName_ThenReturnName() {
    final FolderEntity folderRoot = FolderEntity.builder().name("root").build();
    final FolderEntity folderLeaf1 = FolderEntity.builder().name("leaf1").parent(folderRoot).build();
    final FolderEntity folderLeaf2 = FolderEntity.builder().name("leaf2").parent(folderLeaf1).build();

    Assertions.assertEquals("root", folderRoot.getPathName());
    Assertions.assertEquals("root/leaf1", folderLeaf1.getPathName());
    Assertions.assertEquals("root/leaf1/leaf2", folderLeaf2.getPathName());
  }

}