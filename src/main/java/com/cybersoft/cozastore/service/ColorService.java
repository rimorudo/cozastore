package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.ColorEntity;
import com.cybersoft.cozastore.payload.response.ColorResponse;
import com.cybersoft.cozastore.service.imp.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import com.cybersoft.cozastore.repository.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ColorService implements IColorService {

    @Autowired
    ColorRepository colorRepository;

    @Override
    public List<ColorResponse> getAllColor(){
        //Dữ liệu lấy được từ database
        List<ColorEntity> list = colorRepository.findAll();
        //Dữ liệu trả ra cho FE
        List<ColorResponse> responseList = new ArrayList<>();
        for (ColorEntity item : list ) {
            //Duyệt qua từng dòng dữ liệu query được từ
            ColorResponse colorResponse = new ColorResponse();
            colorResponse.setId(item.getId());
            colorResponse.setName(item.getName());

            responseList.add(colorResponse);
        }

        return responseList;
    }

}
