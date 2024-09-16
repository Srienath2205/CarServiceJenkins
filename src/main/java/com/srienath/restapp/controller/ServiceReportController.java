package com.srienath.restapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.srienath.restapp.model.ServiceReport;
import com.srienath.restapp.service.ServiceReportService;
import com.srienath.restapp.service.JobService;

@RestController
@RequestMapping("/servicereports")
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceReportController {

    private final ServiceReportService serviceReportService;
    private final JobService jobService;

    public ServiceReportController(ServiceReportService serviceReportService, JobService jobService) {
        this.serviceReportService = serviceReportService;
        this.jobService = jobService;
    }

    @GetMapping("/{id}")
    public ServiceReport getServiceReportById(@PathVariable int id) {
        ServiceReport serviceReport = serviceReportService.getById(id);
        if (serviceReport != null) {
            return serviceReport;
        } else {
            throw new RuntimeException("Service Report not found");
        }
    }

    @GetMapping("/all")
    public List<ServiceReport> getAllServiceReports() {
        return serviceReportService.getAll();
    }

    @PostMapping("/add")
    public String createServiceReport(
            @RequestParam("jobID") int jobID,
            @RequestParam("serviceDone") String serviceDone,
            @RequestParam("amountPaid") double amountPaid,
            @RequestParam("paymentDate") String paymentDateStr,
            @RequestParam("paymentStatus") String paymentStatus) {

        try {
            ServiceReport serviceReport = new ServiceReport();
            serviceReport.setJob(jobService.getById(jobID));
            serviceReport.setServiceDone(serviceDone);
            serviceReport.setAmountPaid(amountPaid);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date paymentDate = sdf.parse(paymentDateStr);
            serviceReport.setPaymentDate(paymentDate);

            serviceReport.setPaymentStatus(paymentStatus);

            serviceReportService.create(serviceReport);
            return "Service Report created successfully";
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error processing payment date";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating Service Report";
        }
    }

    @PutMapping("/update/{id}")
    public String updateServiceReport(
            @PathVariable int id,
            @RequestParam(value = "jobID", required = false) Integer jobID,
            @RequestParam(value = "serviceDone", required = false) String serviceDone,
            @RequestParam(value = "amountPaid", required = false) Double amountPaid,
            @RequestParam(value = "paymentDate", required = false) String paymentDateStr,
            @RequestParam(value = "paymentStatus", required = false) String paymentStatus) {

        ServiceReport existingServiceReport = serviceReportService.getById(id);
        if (existingServiceReport == null) {
            return "Service Report not found for update";
        }

        try {
            if (jobID != null) {
                existingServiceReport.setJob(jobService.getById(jobID));
            }
            if (serviceDone != null) {
                existingServiceReport.setServiceDone(serviceDone);
            }
            if (amountPaid != null) {
                existingServiceReport.setAmountPaid(amountPaid);
            }
            if (paymentDateStr != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date paymentDate = sdf.parse(paymentDateStr);
                existingServiceReport.setPaymentDate(paymentDate);
            }
            if (paymentStatus != null) {
                existingServiceReport.setPaymentStatus(paymentStatus);
            }

            serviceReportService.update(existingServiceReport);
            return "Service Report updated successfully";
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error processing payment date";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating Service Report";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteServiceReport(@PathVariable int id) {
        ServiceReport existingServiceReport = serviceReportService.getById(id);
        if (existingServiceReport == null) {
            return "Service Report not found for deletion";
        }
        try {
            serviceReportService.delete(id);
            return "Service Report deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting Service Report";
        }
    }
}



