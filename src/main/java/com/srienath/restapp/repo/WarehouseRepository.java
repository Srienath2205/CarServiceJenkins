package com.srienath.restapp.repo;

import com.srienath.restapp.model.Warehouse;
import java.util.List;

public interface WarehouseRepository {

    public Warehouse findById(int id);

    public List<Warehouse> findAll();

    public void save(Warehouse warehouse);

    public void update(Warehouse warehouse);

    public void deleteById(int id);
}

