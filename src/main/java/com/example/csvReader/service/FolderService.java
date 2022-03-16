package com.example.csvReader.service;

import com.example.csvReader.exception.FolderException;
import com.example.csvReader.model.FolderEntity;
import com.example.csvReader.repository.FolderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FolderService {

    @Autowired
    private FolderRepository folderRepository;

    public FolderEntity save(FolderEntity folderEntity) {

        if (folderRepository.existsById(folderEntity.getId())) {
            log.error("[idempotency] folder already exists for id {}", folderEntity.getId());
            throw new FolderException("folder already exists");
        }


        if (!folderRepository.existsById(folderEntity.getParent().getId())) {
            log.error("could not find parent {} for folder id {}", folderEntity.getParent(), folderEntity.getId());
            throw new FolderException("could not find parent");
        }

        return folderRepository.save(folderEntity);
    }
}
