package com.srienath.restapp.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.srienath.restapp.model.Job;
import com.srienath.restapp.repo.JobRepository;
import com.srienath.restapp.service.JobService;

@Service
public class JobServiceImpl implements JobService {
	
	private JobRepository jobRepository;
	
    public JobServiceImpl(JobRepository jobRepository) {
		super();
		this.jobRepository = jobRepository;
	}

	@Override
    public Job getById(int id) {
        return jobRepository.findById(id);
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    @Override
    public void create(Job job) {
        jobRepository.save(job);
    }

    @Override
    public void update(Job job) {
        jobRepository.update(job);
    }

    @Override
    public void delete(int id) {
        jobRepository.deleteById(id);
    }
}
