package com.srienath.restapp.repo;

import com.srienath.restapp.model.StockPaymentReport;
import java.util.List;

public interface StockPaymentReportRepository {

    public StockPaymentReport findById(int id);

    public List<StockPaymentReport> findAll();

    public void save(StockPaymentReport stockPaymentReport);

    public void update(StockPaymentReport stockPaymentReport);

    public void deleteById(int id);
}


