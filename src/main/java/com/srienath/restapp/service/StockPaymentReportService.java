package com.srienath.restapp.service;

import java.util.List;
import com.srienath.restapp.model.StockPaymentReport;

public interface StockPaymentReportService {
	StockPaymentReport getById(int id);

    List<StockPaymentReport> getAll();

    void create(StockPaymentReport stockPaymentReport);

    void update(StockPaymentReport stockPaymentReport);

    void delete(int id);
}
