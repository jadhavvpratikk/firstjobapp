package com.secure.firstjobapp.job.impl;

import com.secure.firstjobapp.job.Job;
import com.secure.firstjobapp.job.JobRepository;
import com.secure.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>();

    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobByID(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
            if(optionalJob.isPresent()){
                Job j = optionalJob.get();
                j.setTitle(updatedJob.getTitle());
                j.setDescription(updatedJob.getDescription());
                j.setMinSalary(updatedJob.getMinSalary());
                j.setMaxSalary(updatedJob.getMaxSalary());
                j.setLocation(updatedJob.getLocation());
                jobRepository.save(j);
                return true;
            }

        return false;
    }


}
