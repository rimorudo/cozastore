package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.CountryEntity;
import com.cybersoft.cozastore.entity.OrderDetailEntity;
import com.cybersoft.cozastore.entity.OrderEntity;
import com.cybersoft.cozastore.entity.ids.OrderDetailIds;
import com.cybersoft.cozastore.payload.request.OrderProductRequest;
import com.cybersoft.cozastore.payload.request.OrderRequest;
import com.cybersoft.cozastore.repository.OrderDetailRepository;
import com.cybersoft.cozastore.repository.OrderRepository;
import com.cybersoft.cozastore.service.imp.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public boolean addOrder(OrderRequest orderRequest){

        CountryEntity country = new CountryEntity();
        country.setId(orderRequest.getCountryId());

        OrderEntity orderEntity =  new OrderEntity();
        orderEntity.setCountry(country);

        //Khi save thanh cong thi thuoc tinh id cua OrderEntity se co gia tri

        orderRepository.save(orderEntity);

//Them tiep du lieu cho bang order detail

        for (OrderProductRequest data : orderRequest.getListProduct()){
            OrderDetailIds ids  = new OrderDetailIds();
            ids.setOrderId(orderEntity.getId());
            ids.setProductId(data.getId());

            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setIds(ids);
            orderDetailEntity.setQuantity(data.getQuantity());
            orderDetailEntity.setPrice(data.getPrice());

            orderDetailRepository.save(orderDetailEntity);
        }

        return false;
    }
}
