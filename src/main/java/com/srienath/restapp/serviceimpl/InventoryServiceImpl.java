package com.srienath.restapp.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.srienath.restapp.model.Inventory;
import com.srienath.restapp.repo.InventoryRepository;
import com.srienath.restapp.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {
    private InventoryRepository inventoryRepository;
    
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
		super();
		this.inventoryRepository = inventoryRepository;
	}

	@Override
    public Inventory getById(int id) {
        return inventoryRepository.getById(id);
    }

    @Override
    public List<Inventory> getAll() {
        return inventoryRepository.getAll();
    }

    @Override
    public void create(Inventory inventory) {
        inventoryRepository.create(inventory);
    }

    @Override
    public void update(Inventory inventory) {
        inventoryRepository.update(inventory);
    }

    @Override
    public void delete(int id) {
        inventoryRepository.delete(id);
    }
}
