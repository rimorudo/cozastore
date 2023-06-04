package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.request.ProductRequest;
import com.cybersoft.cozastore.payload.response.BaseResponse;
import com.cybersoft.cozastore.service.ProductService;
import com.cybersoft.cozastore.service.imp.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductByCategory(@PathVariable int id){
        BaseResponse response = new BaseResponse();
        response.setData(iProductService.getProductByCategoryId(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *
     * Cach 1: Chuyen file ve dang base64
     * - Tu file chuyen thanh chuoi, day chuoi len server
     * - Tu chuoi file da duoc chuyen, chuyen chuoi do thanh file.
     * - Uu diem: Vi file da chuyen thanh chuoi nen luu tru duoc duoi dang chuoi
     * - Nhuoc diem: x1.5 kich thuoc file
     * Cach 2: Su dung MultipartFile
     * - Mo mot luong doc vao file (Stream)
     *
     */
    @PostMapping("")
    public ResponseEntity<?> addProduct(@Valid ProductRequest productRequest ) throws IOException {

//        String fileName = file.getOriginalFilename();
//        //duong dan luu tru file
//        String rootFolder = "D:\\Bo_BCJV01\\git_01\\image";
//        System.out.println("rootFolder = "+ rootFolder);
//
//        Path pathRoot = Paths.get(rootFolder);
//        System.out.println("pathRoot = "+ pathRoot);
//
//        if(!Files.exists(pathRoot)){
//
//            Files.createDirectory(pathRoot);
//        }
//        //Resolve = "/"
//        Files.copy(file.getInputStream(),pathRoot.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
//
        try{
            String fileName = productRequest.getFile().getOriginalFilename();
            String rootFolder = "D:\\Bo_BCJV01\\git_01\\image";

            Path pathRoot = Paths.get(rootFolder);
            if(!Files.exists(pathRoot)){

                Files.createDirectory(pathRoot);
            }
            //Resolve = "/"
            Files.copy(productRequest.getFile().getInputStream(),pathRoot.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            iProductService.addProduct(productRequest);
        }catch (Exception e){

        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
