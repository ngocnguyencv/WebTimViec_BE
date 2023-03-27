package com.casemd6_be.service;

import com.casemd6_be.model.Company;
import com.casemd6_be.model.query.CompanyAndAccount;
import com.casemd6_be.model.query.ListJobCompanyAccount;
import com.casemd6_be.repository.ICompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    ICompanyRepo iCompanyRepo;

    public void createCompany(Company company) {
        iCompanyRepo.save(company);
    }

    public CompanyAndAccount getAllCompany(String email) {
        return iCompanyRepo.joinCompanyAndAccountByEmail(email);
    }

    public ListJobCompanyAccount getOneCompany(int id){
        return iCompanyRepo.joinCompanyAndJobAndAccountbyid(id);
    }

    public List<ListJobCompanyAccount> searchJobByTitleAndEmailOfCompany(String email,String key){
        return iCompanyRepo.searchJobByTitleAndEmailOfCompany(email,key);
    }

    public List<CompanyAndAccount> getallCompanyNoEamil(){
        return iCompanyRepo.joinCompanyAndAccount();
    }

    public List<CompanyAndAccount> searchBynameCompany(String name){
        return iCompanyRepo.searchBynameCompany(name);
    }

    public List<CompanyAndAccount> getallLimit3(){
        return this.iCompanyRepo.getallCompanylimit3();
    }

    public Company findOne(int id) {
        return iCompanyRepo.findById(id).get();
    }
}
