package com.secure.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    //@RequestMapping(value = "/jobs",method = RequestMethod.GET)
    public ResponseEntity <List<Job>> findAll()
    {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    //@RequestMapping(value = "/jobs",method = RequestMethod.POST)
    public ResponseEntity<String> createJob(@RequestBody Job job)
    {
        jobService.createJob(job);
        return new ResponseEntity<String>("Job added successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}",method = RequestMethod.GET)
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if(job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteJob (@PathVariable Long id){
        boolean deleted = jobService.deleteJobByID(id);
        if(deleted)
            return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJobById(id,updatedJob);
        if(updated)
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
