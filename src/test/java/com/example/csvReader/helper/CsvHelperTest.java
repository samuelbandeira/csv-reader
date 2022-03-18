package com.example.csvReader.helper;

import java.io.IOException;
import java.util.List;

import com.example.csvReader.dto.FolderDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CsvHelper.class })
class CsvHelperTest {

    @Autowired
    private CsvHelper csvHelper;

    @Test
    void givenFileHasCSVContentType_whenCsvToFolderDTO_thenReturnTrue() throws IOException {

        String csvContent = "id,parent_id,item_name,priority\n" +
            "1,nil,heading 1,3\n" +
            "2,nil,heading 2,1\n" +
            "3,1,folder 1 1,4\n" +
            "4,1,folder 1 2,2\n" +
            "5,2,folder 2 1,2\n" +
            "6,2,folder 2 2,3\n" +
            "7,2,folder 2 3,5";
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes());
        final List<FolderDTO> folderDTOS = csvHelper.csvToFolderDTO(multipartFile.getInputStream());

        Assertions.assertNotNull(folderDTOS);
        Assertions.assertEquals(7, folderDTOS.size());
    }

}