package com.srienath.restapp.controller;

import com.srienath.restapp.model.Job;
import com.srienath.restapp.service.JobService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/job")
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable int id) {
        Job job = jobService.getById(id);
        if (job != null) {
            return job;
        } else {
            throw new RuntimeException("Job not found");
        }
    }

    @GetMapping("/all")
    public List<Job> getAllJobs() {
        return jobService.getAll();
    }

    @PostMapping("/add")
    public String createJob(@RequestBody Job job) {
        try {
            jobService.create(job);
            return "Job created successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating job";
        }
    }

    @PutMapping("/update/{id}")
    public String updateJob(@PathVariable int id, @RequestBody Job job) {
        Job existingJob = jobService.getById(id);
        if (existingJob == null) {
            return "Job not found for update";
        }
        try {
            job.setJobID(id);
            jobService.update(job);
            return "Job updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating job";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteJob(@PathVariable int id) {
        Job existingJob = jobService.getById(id);
        if (existingJob == null) {
            return "Job not found for deletion";
        }
        try {
            jobService.delete(id);
            return "Job deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting job";
        }
    }
}
