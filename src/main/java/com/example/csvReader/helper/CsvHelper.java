package com.example.csvReader.helper;

import com.example.csvReader.dto.FolderDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CsvHelper {
    public List<FolderDTO> csvToFolderDTO(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = CSVFormat.EXCEL.withHeader().parse(fileReader)) {
            List<FolderDTO> folders = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                FolderDTO folderDTO = FolderDTO.builder()
                    .id(Long.parseLong(csvRecord.get("id")))
                    .parent_id(getParentFolder(csvRecord))
                    .item_name(csvRecord.get("item_name"))
                    .priority(Long.parseLong(csvRecord.get("priority")))
                    .build();
                folders.add(folderDTO);
            }
            return folders;
        } catch (IOException e) {
            log.error("fail to parse CSV file: " + e.getMessage());
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    private Long getParentFolder(CSVRecord csvRecord) {
        String parentId = csvRecord.get("parent_id");
        return "nil".equals(parentId) ? null : Long.parseLong(parentId);
    }
}
