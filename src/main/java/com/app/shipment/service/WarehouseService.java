package com.app.shipment.service;

import com.app.shipment.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    private WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    //GET

    public void getAllWarehouses() {}

    public void getStockFromWarehouseById() {}

    //POST

    public void addNewWarehouse() {}

    //PUT

    public void updateLocation() {}

    //DELETE

    public void deleteWarehouseById() {
        //!!stock need to be empty, before it is executed. Otherwise, it won't be possible.
    }
}
