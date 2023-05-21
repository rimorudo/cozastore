package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.CategoryEntity;
import com.cybersoft.cozastore.payload.response.CategoryResponse;
import com.cybersoft.cozastore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class HomeService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryResponse> getAllCategory(){
        //Du lieu lay duoc tu database
        List<CategoryEntity> list = categoryRepository.findAll();
        //Du lieu tra ra cho FE
        List<CategoryResponse> responseList = new ArrayList<>();
        for (CategoryEntity item : list){
            //Duyet qua tung dong du lieu query duoc tu CategoryEntity
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(item.getId());
            categoryResponse.setName(item.getName());
            responseList.add(categoryResponse);
        } return responseList;
    }
}

