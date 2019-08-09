package kz.kasya.bitlab.RXCourse.modules.file.services;

import kz.kasya.bitlab.RXCourse.modules.file.exceptions.MyFileNotFoundException;
import kz.kasya.bitlab.RXCourse.modules.file.models.entities.FileResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {


    public String storeFile(MultipartFile file);

    public Resource loadFileAsResource(String fileName);

    public FileResource save(FileResource fileResource);

    public boolean delete(FileResource fileResource);

    public FileResource getFileByFileName(String fileName) throws MyFileNotFoundException;
}