package com.example.csvReader.helper;

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
    public CsvHelper csvHelper;

    @Test
    void whenFileHasNotCSVContentType_thenReturnFalse() {

        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.csv", MediaType.TEXT_PLAIN.getType(), "file content".getBytes());
        Assertions.assertFalse(csvHelper.hasCSVFormat(multipartFile));
    }

    @Test
    void whenFileHasCSVContentType_thenReturnTrue() {

        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.csv", "text/csv", "file content".getBytes());
        Assertions.assertTrue(csvHelper.hasCSVFormat(multipartFile));
    }

}