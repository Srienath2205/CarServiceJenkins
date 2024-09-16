package com.srienath.restapp.repoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.srienath.restapp.model.Inventory;
import com.srienath.restapp.repo.InventoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class InventoryRepositoryImpl implements InventoryRepository {

	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public Inventory getById(int id) {
        return entityManager.find(Inventory.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Inventory> getAll() {
        return entityManager.createQuery("FROM Inventory").getResultList();
    }

    @Override
    public void create(Inventory inventory) {
        entityManager.persist(inventory);
    }

    @Override
    public void update(Inventory inventory) {
        entityManager.merge(inventory);
    }

    @Override
    public void delete(int id) {
        Inventory inventory = entityManager.find(Inventory.class, id);
        if (inventory != null) {
            entityManager.remove(inventory);
        }
    }

}
