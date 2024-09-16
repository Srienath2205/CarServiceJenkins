package com.srienath.restapp.service;

import java.util.List;
import com.srienath.restapp.model.Job;

public interface JobService {
	public Job getById(int id);

    public List<Job> getAll();

    public void create(Job job);

    public void update(Job job);

    public void delete(int id);
}
