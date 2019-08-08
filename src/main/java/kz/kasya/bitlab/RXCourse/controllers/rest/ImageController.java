package kz.kasya.bitlab.RXCourse.controllers.rest;

import kz.kasya.bitlab.RXCourse.models.entities.Image;
import kz.kasya.bitlab.RXCourse.services.ImageService;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

//@RestController
//@RequestMapping("/api/image")
public class ImageController {

    @Value("${image.not.found.src}")
    String NO_IMAGE;


    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity fileUpload(@RequestParam("file") MultipartFile file, String imagePath)
            throws IOException {

        Image image = imageService.getByImagePath(imagePath);
        if (image == null) {
            image = new Image();
            String folders = "var/tmp/";
            String path = folders + new Date().getTime() + file.getOriginalFilename();
            File convertFile = new File(path);
            new File(folders).mkdirs();
            convertFile.createNewFile();
            FileUtils.writeByteArrayToFile(convertFile, file.getBytes());
            image.setPath(path);
            imageService.add(image);
        } else {
            String folders = "var/tmp/";
            String path = folders + new Date().getTime() + file.getOriginalFilename();
            File convertFile = new File(path);
            new File(folders).mkdirs();
            if (convertFile.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File not created");
            }
            FileUtils.writeByteArrayToFile(convertFile, file.getBytes());
            String oldImage = new String(image.getPath());
            image.setPath(path);
            imageService.update(image, oldImage);
        }

        return ResponseEntity.status(HttpStatus.OK).body(image);
    }

    @GetMapping(value = "{id}")
    public @ResponseBody
    ResponseEntity<byte[]> getFile(@PathVariable Long id) throws IOException {
        Image image = imageService.getById(id);
        File file = null;
        if (image != null) {
            String filename = image.getPath();
            file = new File(filename);
        } else {
            file = new ClassPathResource(NO_IMAGE).getFile();
        }

        Tika tika = new Tika();
        String mimeType = tika.detect(file);
        MediaType mediaType = null;
        if (mimeType.trim().equalsIgnoreCase(MediaType.IMAGE_JPEG_VALUE)) {
            mediaType = MediaType.IMAGE_JPEG;
        } else if (mimeType.trim().equalsIgnoreCase(MediaType.IMAGE_PNG_VALUE)) {
            mediaType = MediaType.IMAGE_PNG;
        }
        return ResponseEntity.ok().contentType(mediaType).body(Files.readAllBytes(file.toPath()));
    }
}