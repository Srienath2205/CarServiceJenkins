package com.srienath.restapp.repoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.srienath.restapp.model.Warehouse;
import com.srienath.restapp.repo.WarehouseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class WarehouseRepositoryImpl implements WarehouseRepository {

	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public Warehouse findById(int id) {
        return entityManager.find(Warehouse.class, id);
    }

    @Override
    public List<Warehouse> findAll() {
        return entityManager.createQuery("from Warehouse", Warehouse.class).getResultList();
    }

    @Override
    public void save(Warehouse warehouse) {
        entityManager.persist(warehouse);
    }

    @Override
    public void update(Warehouse warehouse) {
        entityManager.merge(warehouse);
    }

    @Override
    public void deleteById(int id) {
        Warehouse warehouse = findById(id);
        if (warehouse != null) {
            entityManager.remove(warehouse);
        }
    }

}
