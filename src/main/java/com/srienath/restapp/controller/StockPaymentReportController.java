package com.srienath.restapp.controller;

import com.srienath.restapp.model.StockPaymentReport;
import com.srienath.restapp.service.StockPaymentReportService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/stockPaymentReports")
@CrossOrigin(origins = "http://localhost:3000")
public class StockPaymentReportController {

    private final StockPaymentReportService stockPaymentReportService;

    public StockPaymentReportController(StockPaymentReportService stockPaymentReportService) {
        this.stockPaymentReportService = stockPaymentReportService;
    }

    @GetMapping("/{id}")
    public StockPaymentReport getStockPaymentReportById(@PathVariable int id) {
        StockPaymentReport stockPaymentReport = stockPaymentReportService.getById(id);
        if (stockPaymentReport != null) {
            return stockPaymentReport;
        } else {
            throw new RuntimeException("Stock Payment Report not found");
        }
    }

    @GetMapping("/all")
    public List<StockPaymentReport> getAllStockPaymentReports() {
        return stockPaymentReportService.getAll();
    }

    @PostMapping("/add")
    public String createStockPaymentReport(
            @RequestParam("restockRequest") String restockRequest,
            @RequestParam("dispatchDate") String dispatchDateStr,
            @RequestParam("deliveryDate") String deliveryDateStr,
            @RequestParam("deliveryStatus") String deliveryStatus,
            @RequestParam("amountPaid") double amountPaid,
            @RequestParam("paymentDate") String paymentDateStr,
            @RequestParam("paymentStatus") String paymentStatus) {

        try {
            StockPaymentReport stockPaymentReport = new StockPaymentReport();
            stockPaymentReport.setRestockRequest(restockRequest);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (dispatchDateStr != null) {
                Date dispatchDate = sdf.parse(dispatchDateStr);
                stockPaymentReport.setDispatchDate(dispatchDate);
            }
            if (deliveryDateStr != null) {
                Date deliveryDate = sdf.parse(deliveryDateStr);
                stockPaymentReport.setDeliveryDate(deliveryDate);
            }
            stockPaymentReport.setDeliveryStatus(deliveryStatus);
            stockPaymentReport.setAmountPaid(amountPaid);
            Date paymentDate = sdf.parse(paymentDateStr);
            stockPaymentReport.setPaymentDate(paymentDate);
            stockPaymentReport.setPaymentStatus(paymentStatus);

            stockPaymentReportService.create(stockPaymentReport);
            return "Stock Payment Report created successfully";
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error processing date format";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating Stock Payment Report";
        }
    }

    @PutMapping("/update/{id}")
    public String updateStockPaymentReport(
            @PathVariable int id,
            @RequestParam(value = "restockRequest", required = false) String restockRequest,
            @RequestParam(value = "dispatchDate", required = false) String dispatchDateStr,
            @RequestParam(value = "deliveryDate", required = false) String deliveryDateStr,
            @RequestParam(value = "deliveryStatus", required = false) String deliveryStatus,
            @RequestParam(value = "amountPaid", required = false) Double amountPaid,
            @RequestParam(value = "paymentDate", required = false) String paymentDateStr,
            @RequestParam(value = "paymentStatus", required = false) String paymentStatus) {

        StockPaymentReport existingReport = stockPaymentReportService.getById(id);
        if (existingReport == null) {
            return "Stock Payment Report not found for update";
        }

        try {
            if (restockRequest != null) {
                existingReport.setRestockRequest(restockRequest);
            }
            if (dispatchDateStr != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dispatchDate = sdf.parse(dispatchDateStr);
                existingReport.setDispatchDate(dispatchDate);
            }
            if (deliveryDateStr != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date deliveryDate = sdf.parse(deliveryDateStr);
                existingReport.setDeliveryDate(deliveryDate);
            }
            if (deliveryStatus != null) {
                existingReport.setDeliveryStatus(deliveryStatus);
            }
            if (amountPaid != null) {
                existingReport.setAmountPaid(amountPaid);
            }
            if (paymentDateStr != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date paymentDate = sdf.parse(paymentDateStr);
                existingReport.setPaymentDate(paymentDate);
            }
            if (paymentStatus != null) {
                existingReport.setPaymentStatus(paymentStatus);
            }

            stockPaymentReportService.update(existingReport);
            return "Stock Payment Report updated successfully";
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error processing date format";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating Stock Payment Report";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStockPaymentReport(@PathVariable int id) {
        StockPaymentReport existingReport = stockPaymentReportService.getById(id);
        if (existingReport == null) {
            return "Stock Payment Report not found for deletion";
        }
        try {
            stockPaymentReportService.delete(id);
            return "Stock Payment Report deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting Stock Payment Report";
        }
    }
}
