package com.example.demo.controller;

import com.example.demo.model.Smestaj;
import com.example.demo.model.SmestajPhoto;
import com.example.demo.model.dto.ImageData;
import com.example.demo.service.FileService;
import com.example.demo.service.SmestajPhotoService;
import com.example.demo.ws.WSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import schema.wsdl.PhotoWS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/photos")
public class SmestajPhotoController {


    private FileService fileService;
    private SmestajPhotoService smestajPhotoService;
    @Autowired
    private WSClient wsClient;


    @Autowired
    public SmestajPhotoController(FileService fileService, SmestajPhotoService smestajPhotoService) {
        this.fileService = fileService;
        this.smestajPhotoService = smestajPhotoService;
    }

    @GetMapping("/get/{smestajId}")
    @ResponseBody
    public List<ImageData> getPhotosFromSmestaj(@PathVariable("smestajId") long id) throws IOException {
        List<SmestajPhoto> photos = smestajPhotoService.getPhotosBySmestaj(id);
        List<ImageData> encodedImages = new ArrayList<>();
        for (SmestajPhoto photo : photos) {
            String format = fileService.getImageFormat(photo.getPath());
            String imageContent = fileService.getImageAsBase64String(photo.getPath());
            encodedImages.add(new ImageData(format, imageContent));
        }
        return encodedImages;
    }

    @PostMapping(path = "/{smestajId}/upload")
    public boolean uploadFiles(@PathVariable("smestajId") long id, @RequestParam("photos") MultipartFile file) {
        boolean isUploadSuccess = fileService.uploadFiles(file);
        if (isUploadSuccess) {  //ako je upload uspesan----->cuva se putanja
            String fName = file.getOriginalFilename();
            try {

                String putanja = fileService.getImageAsBase64String(fName);
                smestajPhotoService.savePhoto(id,putanja);
                PhotoWS photoWS = new PhotoWS();
                photoWS.setPath(putanja);
                photoWS.setSmestajId(id);
                wsClient.createPhoto(photoWS);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isUploadSuccess;
    }


    @DeleteMapping(path = "/{photoId}")
    public boolean deletePhoto(@PathVariable("photoId") long id){

        smestajPhotoService.deletePhoto(id);

        return true;
    }
 }
