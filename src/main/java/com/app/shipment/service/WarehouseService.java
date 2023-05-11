package com.app.shipment.service;

import com.app.shipment.exceptions.WarehouseNotFound;
import com.app.shipment.model.Product;
import com.app.shipment.model.Warehouse;
import com.app.shipment.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    //GET

    public List<Warehouse> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouses;
    }

    public Optional<Warehouse> getWarehouseByLocation(String location) {
        Optional<Warehouse> warehouse = warehouseRepository.findByLocation(location);
        if(warehouse.isEmpty()) {
            throw new WarehouseNotFound("Warehouse in " + location + "doesnt exist. Choose different.");
        }
        return warehouse;
    }

    public List<Product> getStockFromWarehouseByLocation(String location) {
        Optional<Warehouse> warehouse = warehouseRepository.findByLocation(location);
        if(warehouse.isEmpty()) {
            throw new WarehouseNotFound("Warehouse in " + location + "doesnt exist. Choose different.");
        }
        return warehouse.get().getStock();
    }

    //POST

    public void addNewWarehouse() {}

    //PUT

    public void updateLocation() {}

    //DELETE

    public void deleteWarehouseById() {
        //!!stock need to be empty, before it is executed. Otherwise, it won't be possible.
    }
}
