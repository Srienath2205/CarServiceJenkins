package com.srienath.restapp.repo;

import java.util.List;
import com.srienath.restapp.model.Job;

public interface JobRepository {
	public Job findById(int id);

    public List<Job> findAll();

    public void save(Job job);

    public void update(Job job);

    public void deleteById(int id);
}
