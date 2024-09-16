package com.srienath.restapp.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.srienath.restapp.model.StockPaymentReport;
import com.srienath.restapp.repo.StockPaymentReportRepository;
import com.srienath.restapp.service.StockPaymentReportService;

@Service
public class StockPaymentReportServiceImpl implements StockPaymentReportService {
	
    private StockPaymentReportRepository stockPaymentReportRepository;
    
    public StockPaymentReportServiceImpl(StockPaymentReportRepository stockPaymentReportRepository) {
		super();
		this.stockPaymentReportRepository = stockPaymentReportRepository;
	}

	@Override
    public StockPaymentReport getById(int id) {
        return stockPaymentReportRepository.findById(id);
    }

    @Override
    public List<StockPaymentReport> getAll() {
        return stockPaymentReportRepository.findAll();
    }

    @Override
    public void create(StockPaymentReport stockPaymentReport) {
        stockPaymentReportRepository.save(stockPaymentReport);
    }

    @Override
    public void update(StockPaymentReport stockPaymentReport) {
        stockPaymentReportRepository.update(stockPaymentReport);
    }

    @Override
    public void delete(int id) {
        stockPaymentReportRepository.deleteById(id);
    }
}
