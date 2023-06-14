package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.SizeEntity;
import com.cybersoft.cozastore.payload.response.SizeResponse;
import com.cybersoft.cozastore.repository.SizeRepository;
import com.cybersoft.cozastore.service.imp.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SizeService implements ISizeService {
    @Autowired
    SizeRepository sizeRepository;

    @Override
    public List<SizeResponse> getAllSize() {
        //Dữ liệu lấy được từ database
        List<SizeEntity> list = sizeRepository.findAll();
        //Dữ liệu trả ra cho FE
        List<SizeResponse> responseList = new ArrayList<>();
        for (SizeEntity item : list ) {
            //Duyệt qua từng dòng dữ liệu query được từ
            SizeResponse sizeResponse = new SizeResponse();
            sizeResponse.setId(item.getId());
            sizeResponse.setName(item.getName());

            responseList.add(sizeResponse);
        }

        return responseList;
    }
}
