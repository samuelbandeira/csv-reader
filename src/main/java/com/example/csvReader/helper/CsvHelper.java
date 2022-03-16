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

    public boolean hasCSVFormat(MultipartFile file) {
        return "text/csv".equals(file.getContentType());
    }

    public List<FolderDTO> csvToFolderDTO(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT)) {
            List<FolderDTO> folders = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                FolderDTO folderDTO = FolderDTO.builder()
                    .id(Long.parseLong(csvRecord.get("id")))
                    .parent(FolderDTO.builder().id(Long.parseLong(csvRecord.get("parent_id"))).build())
                    .name(csvRecord.get("item_name"))
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
}
