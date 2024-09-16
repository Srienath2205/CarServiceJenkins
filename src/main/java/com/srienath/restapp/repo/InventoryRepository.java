package com.srienath.restapp.repo;

import com.srienath.restapp.model.Inventory;
import java.util.List;

public interface InventoryRepository {
    public Inventory getById(int id);
    public List<Inventory> getAll();
    public void create(Inventory inventory);
    public void update(Inventory inventory);
    public void delete(int id);
}
