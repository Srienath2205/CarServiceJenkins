package com.srienath.restapp.service;

import java.util.List;
import com.srienath.restapp.model.Inventory;

public interface InventoryService {
	Inventory getById(int id);
    List<Inventory> getAll();
    void create(Inventory inventory);
    void update(Inventory inventory);
    void delete(int id);
}
