package com.william.Employee.Expense.and.Reimbursement.employee.service;



import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    String uploadFile(String path, MultipartFile file) throws IOException;

    InputStream getFileUrl(String path, String fileName) throws FileNotFoundException;
}

