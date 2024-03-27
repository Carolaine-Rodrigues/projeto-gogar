package br.com.gogar.sistema.api.domain.controller;

import br.com.gogar.sistema.api.domain.dto.FileDTO;
import br.com.gogar.sistema.api.domain.entity.ProposalFile;
import br.com.gogar.sistema.api.domain.service.ProposalFileService;
import org.hibernate.boot.archive.scan.spi.ScanOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class ProposalFileController {

    @Autowired
    ProposalFileService proposalFileService;
    @GetMapping(value = "/excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generateExcelFile() throws Exception {
        byte[] fileData = new byte[0];
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            proposalFileService.generateExcelFile();
            fileData = outputStream.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "filename.xlsx"); // Define o nome do arquivo e sua extensão

            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        }catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/post")
    public ResponseEntity postFileExcel(@RequestBody FileDTO data){

        var file = proposalFileService.saveFile(data);
        return ResponseEntity.ok(file);
    }


}
