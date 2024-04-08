package com.secure.firstjobapp.job.impl;

import com.secure.firstjobapp.job.Job;
import com.secure.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;
    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        for (Job job : jobs ){
            if(job.getId().equals(id)){
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteJobByID(Long id) {
        Iterator<Job> ij = jobs.iterator();
        while(ij.hasNext()){
            Job job = ij.next();
            if(job.getId().equals(id)){
                ij.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        for(Job j : jobs){
            if(j.getId().equals(id)){
                j.setTitle(updatedJob.getTitle());
                j.setDescription(updatedJob.getDescription());
                j.setMinSalary(updatedJob.getMinSalary());
                j.setMaxSalary(updatedJob.getMaxSalary());
                j.setLocation(updatedJob.getLocation());
                return true;
            }
        }
        return false;
    }


}
