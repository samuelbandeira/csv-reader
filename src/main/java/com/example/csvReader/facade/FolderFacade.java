package com.example.csvReader.facade;

import com.example.csvReader.dto.FolderDTO;
import com.example.csvReader.exception.FolderException;
import com.example.csvReader.helper.CsvHelper;
import com.example.csvReader.mapper.FolderMapper;
import com.example.csvReader.model.FolderEntity;
import com.example.csvReader.repository.FolderRepository;
import com.example.csvReader.service.FolderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class FolderFacade {

    @Autowired
    public CsvHelper csvHelper;
    @Autowired
    public FolderMapper folderMapper;
    @Autowired
    public FolderService folderService;

    @Transactional
    public List<FolderDTO> save(MultipartFile file) {
        if (!csvHelper.hasCSVFormat(file)) {
            log.error("invalid content type");
            throw new FolderException("invalid content type");
        }

        List<FolderDTO> returnList = new CopyOnWriteArrayList<>();
        try {
            final List<FolderDTO> folderDTOS = csvHelper.csvToFolderDTO(file.getInputStream());
            for (FolderDTO folderDTO : folderDTOS) {
                final FolderEntity folderEntity = folderMapper.toEntity(folderDTO);
                final FolderEntity saved = folderService.save(folderEntity);
                returnList.add(folderMapper.toDTO(saved));
            }

        } catch (IOException e) {
            log.error("error reading csv file");
            throw new FolderException("error reading csv file");
        }

        return returnList;
    }
}
