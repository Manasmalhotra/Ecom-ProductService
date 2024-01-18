package com.example.productservice.service;

import com.example.productservice.model.Price;
import com.example.productservice.repository.PriceRepository;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService{
    PriceRepository priceRepository;
    public PriceServiceImpl(PriceRepository priceRepository){
        this.priceRepository=priceRepository;
    }

    public Price createPrice(Price p){
        return priceRepository.save(p);
    }
}
