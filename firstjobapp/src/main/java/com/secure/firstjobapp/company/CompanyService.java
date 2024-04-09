package com.secure.firstjobapp.company;

import com.secure.firstjobapp.job.Job;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    boolean updateCompany(Company company,Long id);

    void createCompany(Company company);
}
