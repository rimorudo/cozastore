package com.cybersoft.cozastore.payload.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProductRequest {

    @NotNull
    private MultipartFile file;

    @NotBlank(message = "Vui long nhap ten")
    private String name;

    @Positive(message = "vui long nhap gia > 0")
    private double price;
    private String description;
    private int quanity;
    private int sizeId;
    private int colorId;
    private int categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeID) {
        this.sizeId = sizeID;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorID) {
        this.colorId = colorID;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryID) {
        this.categoryId = categoryID;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
