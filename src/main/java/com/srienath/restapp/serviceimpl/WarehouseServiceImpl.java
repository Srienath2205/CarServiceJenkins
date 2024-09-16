package com.srienath.restapp.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.srienath.restapp.model.Warehouse;
import com.srienath.restapp.repo.WarehouseRepository;
import com.srienath.restapp.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {
	private WarehouseRepository warehouseRepository;
	
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
		super();
		this.warehouseRepository = warehouseRepository;
	}

	@Override
    public Warehouse getById(int id) {
        return warehouseRepository.findById(id);
    }

    @Override
    public List<Warehouse> getAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public void create(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    @Override
    public void update(Warehouse warehouse) {
        warehouseRepository.update(warehouse);
    }

    @Override
    public void delete(int id) {
        warehouseRepository.deleteById(id);
    }
}
