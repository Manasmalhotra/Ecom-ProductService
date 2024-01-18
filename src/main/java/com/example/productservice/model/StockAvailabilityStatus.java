package com.example.productservice.model;

public enum StockAvailabilityStatus {

    IN_STOCK("In-Stock"),
    OUT_OF_STOCK("OUT_OF_STOCK");
    String value;
    StockAvailabilityStatus(String value){
        this.value=value;
    }
}
