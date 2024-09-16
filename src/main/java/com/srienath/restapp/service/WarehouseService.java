package com.srienath.restapp.service;

import java.util.List;
import com.srienath.restapp.model.Warehouse;

public interface WarehouseService {
	Warehouse getById(int id);

    List<Warehouse> getAll();

    void create(Warehouse warehouse);

    void update(Warehouse warehouse);

    void delete(int id);
}
