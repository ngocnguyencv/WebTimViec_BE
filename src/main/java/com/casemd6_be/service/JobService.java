package com.casemd6_be.service;

import com.casemd6_be.model.Job;
import com.casemd6_be.model.query.ListJobCompanyAccount;
import com.casemd6_be.model.query.ListTopCompany;
import com.casemd6_be.repository.IJobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobService {
    @Autowired
    IJobRepo iJobRepo;

    public List<Job> findAll() {
        return (List<Job>) iJobRepo.findAll();
    }

    public List<ListJobCompanyAccount> getAllJobByEmail(String email) {
        return iJobRepo.joinCompanyAndJobAndAccountByEmail(email);
    }

    public Job findJobById(int id) {
        return iJobRepo.findJobsById(id);
    }

    public void save(Job job) {
        iJobRepo.save(job);
    }

    public List<ListJobCompanyAccount> getAllJob_Latest() {
        List<ListJobCompanyAccount> ListJob_Latest = iJobRepo.joinCompanyAndJobAndAccount();
        Collections.reverse(ListJob_Latest);
        return ListJob_Latest;
    }

    public ListJobCompanyAccount getOneJobbyID(int id) {
        return iJobRepo.joinCompanyAndJobAndAccountbyid(id);
    }

    public List<ListJobCompanyAccount> searchByCompany(String short_name) {
        return iJobRepo.searchByCompany(short_name);
    }

    // 7 API search job
    public List<ListJobCompanyAccount> searchJobsByTitleOrAddress(String key) {
        return iJobRepo.searchJobsByTitleOrAddress(key);
    }

    public List<ListJobCompanyAccount> searchJobsByNameCategory(int idCategory) {
        return iJobRepo.searchJobsByNameCategory(idCategory);
    }

    public List<ListJobCompanyAccount> searchJobsByNameLocation(int idLocation) {
        return iJobRepo.searchJobsByNameLocation(idLocation);
    }

    public List<ListJobCompanyAccount> searchJobsByTitleAndAddressAndCategory(String key, int idCategory) {
        return iJobRepo.searchJobsByTitleAndAddressAndCategory(key, idCategory);
    }

    public List<ListJobCompanyAccount> searchJobsByTitleAndAddressAndLocation(String key, int idLocation) {
        return iJobRepo.searchJobsByTitleAndAddressAndLocation(key, idLocation);
    }

    public List<ListJobCompanyAccount> searchJobsByCategoryAndLocation(int idCategory, int idLocation) {
        return iJobRepo.searchJobsByCategoryAndLocation(idCategory, idLocation);
    }

    public List<ListJobCompanyAccount> searchJobsByTitleAddressCategoryLocation(String key, int idCategory, int idLocation) {
        return iJobRepo.searchJobsByTitleAddressCategoryLocation(key, idCategory, idLocation);
    }

    // Hien thi Job User
    public List<ListJobCompanyAccount> getShowAllJob() {
        return iJobRepo.joinCompanyAndJobAndAccount();
    }

    // Hien thi Job Admin
    public List<ListJobCompanyAccount> getAdminJob() {
        return iJobRepo.joinCompanyAndJobAndAccount2();
    }

    public List<ListTopCompany> gettopCompany() {
        return iJobRepo.joinCompanyAndJobAndAccount1();
    }

    //Sort Job by salary
    public List<ListJobCompanyAccount> sortJobBySalaryMin() {
        return iJobRepo.sortJobBySalaryMin();
    }

    public List<ListJobCompanyAccount> sortJobBySalary1000() {
        return iJobRepo.sortJobBySalary1000();
    }

    public List<ListJobCompanyAccount> sortJobBySalary2000() {
        return iJobRepo.sortJobBySalary2000();
    }

    public List<ListJobCompanyAccount> sortJobBySalaryMax() {
        return iJobRepo.sortJobBySalaryMax();
    }

    public List<ListJobCompanyAccount> getAdminJoblimt3() {
        return iJobRepo.joinCompanyAndJobAndAccount2limit3();
    }

    public Job findById(Integer id) {
        return iJobRepo.findById(id).get();
    }

    public List<Job> findAllJobsInCompanyId(Long id) {
        return iJobRepo.findJobsByCompanyId(id);
    }

    // lấy vị trí cuối cùng của mảng job
    public Job findJobForCreateCodeJob() {
        return iJobRepo.findJobForCreateCodeJob();
    }
}
