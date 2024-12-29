package com.william.Employee.Expense.and.Reimbursement.employee.controller;


import com.william.Employee.Expense.and.Reimbursement.employee.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file/")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @Value("${project.image}")
    private String path;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileHandler(@RequestPart MultipartFile file) throws IOException {
        String uploadedFileName = fileService.uploadFile(path, file);
        return ResponseEntity.ok("file uploaded successfully" + uploadedFileName);
    }

    @GetMapping("/{fileName}")
    public void getFileUrlHandler(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        // Retrieve the file as an InputStream
        InputStream resourceFile = fileService.getFileUrl(path, fileName);

        // Determine the file path
        Path filePath = Paths.get(path).resolve(fileName);

        // Dynamically determine the MIME type of the file
        String contentType = Files.probeContentType(filePath);
        if (contentType == null) {
            contentType = "application/octet-stream"; // Fallback for unknown types
        }

        // Set the Content-Type header
        response.setContentType(contentType);

        // Copy the content of the file to the response output stream
        StreamUtils.copy(resourceFile, response.getOutputStream());
        response.flushBuffer(); // Ensure all data is written to the output stream
    }

}


