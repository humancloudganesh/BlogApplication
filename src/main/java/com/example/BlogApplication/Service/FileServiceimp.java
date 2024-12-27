package com.example.BlogApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
@Component
public class FileServiceimp implements FileService{
    @Override
    public String upload(String path, MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();

        String randomID = UUID.randomUUID().toString();
        String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

        String filepath= path + File.separator + fileName1;

        File f=new File(path);
        if(!f.exists())
        {
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filepath));
        return fileName1;

    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullpath=path+File.separator+fileName;
        InputStream is=new FileInputStream(fullpath);
        return is;
    }
}
