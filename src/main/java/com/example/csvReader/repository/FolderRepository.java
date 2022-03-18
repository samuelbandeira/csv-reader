package com.example.csvReader.repository;

import com.example.csvReader.model.FolderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<FolderEntity, Long> {
    Page<FolderEntity> findByName(String itemName, Pageable pageable);
}
