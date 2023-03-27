package com.casemd6_be.service;

import com.casemd6_be.model.ApplyJob;
import com.casemd6_be.model.query.CheckQuantityApplyJob;
import com.casemd6_be.model.query.ListApplyJob;
import com.casemd6_be.repository.IApplyJobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyJobService {
    @Autowired
    IApplyJobRepo iApplyJobRepo;

    public List<ApplyJob> findAllApplyJob() {
        return (List<ApplyJob>) iApplyJobRepo.findAll();
    }

    public ApplyJob findOneApplyJobById(int idApply) {
        return iApplyJobRepo.findApplyJobsById(idApply);
    }

    public void save(ApplyJob applyJob) {
        iApplyJobRepo.save(applyJob);
    }

    public void delete(int id) {
        iApplyJobRepo.deleteById(id);
    }

    // Tìm tất cả các Job của một người dùng đã apply
    public List<ListApplyJob> showAllApplyJobByUser(String email) {
        return iApplyJobRepo.showAllApplyJobByUser(email);
    }

    // Tìm tất cả các Job của công ty theo ứng viên
    public List<ListApplyJob> showAllApplyJobOfUserByCompany(int idCompany) {
        return iApplyJobRepo.showAllApplyJobOfUserByCompany(idCompany);
    }

    public ListApplyJob showApplyJobOfUserByCompanyCV(int idApply) {
        return iApplyJobRepo.showApplyJobOfUserByCompanyCV(idApply);
    }

    // Tìm kiếm các Job theo tên người dùng, tiêu đề, mã công việc
    public List<ListApplyJob> searchApplyJobsWithUser(String key, int idCompany) {
        return iApplyJobRepo.searchApplyJobsWithUser(key, idCompany);
    }

    // Check số lượng ứng viên mà doanh nghiệp đã tuyển
    public CheckQuantityApplyJob checkQuantityApplyJob(int idJob) {
        return iApplyJobRepo.checkQuantityApplyJob(idJob);
    }
}
