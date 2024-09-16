package com.srienath.restapp.repoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.srienath.restapp.model.StockPaymentReport;
import com.srienath.restapp.repo.StockPaymentReportRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class StockPaymentReportRepositoryImpl implements StockPaymentReportRepository {

	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public StockPaymentReport findById(int id) {
        return entityManager.find(StockPaymentReport.class, id);
    }

    @Override
    public List<StockPaymentReport> findAll() {
        return entityManager.createQuery("from StockPaymentReport", StockPaymentReport.class).getResultList();
    }

    @Override
    public void save(StockPaymentReport stockPaymentReport) {
        entityManager.persist(stockPaymentReport);
    }

    @Override
    public void update(StockPaymentReport stockPaymentReport) {
        entityManager.merge(stockPaymentReport);
    }

    @Override
    public void deleteById(int id) {
        StockPaymentReport stockPaymentReport = findById(id);
        if (stockPaymentReport != null) {
            entityManager.remove(stockPaymentReport);
        }
    }

}
