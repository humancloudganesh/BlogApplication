package com.example.BlogApplication.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
@Service
public interface FileService {

    String upload(String path, MultipartFile file)throws IOException;

    InputStream getResource(String path,String fileName)throws FileNotFoundException;
}
