package com.casemd6_be.repository;

import com.casemd6_be.model.ApplyJob;
import com.casemd6_be.model.query.CheckQuantityApplyJob;
import com.casemd6_be.model.query.ListApplyJob;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IApplyJobRepo extends CrudRepository<ApplyJob, Integer> {

    ApplyJob findApplyJobsById(int idApply);

    @Query(nativeQuery = true,value = "select account.id as idAccount,email,account.name as nameAcc,account.status as statusAcc,\n" +
            "apply_job.id as idApply, count,cv,message,apply_job.status as statusApply,\n" +
            "job.id as idJob,job.code as codeJob,job.status as statusJob,title,\n" +
            "company.id as idCompany,short_name,role.id as idRole\n" +
            "from account join apply_job on account.id = apply_job.account_id\n" +
            "join job on job.id = apply_job.job_id\n" +
            "join company on company.id = job.company_id \n" +
            "join role on role.id = account.role_id WHERE role.id = 2 and email=:email")
    List<ListApplyJob> showAllApplyJobByUser(@Param("email") String email);

    // Tìm tất cả các Job của công ty theo ứng viên
    @Query(nativeQuery = true,value = "select account.id as idAccount,email,account.name as nameAcc,account.status as statusAcc,\n" +
            "apply_job.id as idApply, count,cv,message,apply_job.status as statusApply,\n" +
            "job.id as idJob,job.code as codeJob,job.status as statusJob,title,\n" +
            "company.id as idCompany,short_name,role.id as idRole\n" +
            "from account join apply_job on account.id = apply_job.account_id\n" +
            "join job on job.id = apply_job.job_id\n" +
            "join company on company.id = job.company_id \n" +
            "join role on role.id = account.role_id WHERE role.id = 2 and company.id=:id")
    List<ListApplyJob> showAllApplyJobOfUserByCompany(@Param("id") int id);

    @Query(nativeQuery = true,value = "select account.id as idAccount,email,account.name as nameAcc,account.status as statusAcc,\n" +
            "apply_job.id as idApply, count,cv,message,apply_job.status as statusApply,\n" +
            "job.id as idJob,job.code as codeJob,job.status as statusJob,title,\n" +
            "company.id as idCompany,short_name,role.id as idRole\n" +
            "from account join apply_job on account.id = apply_job.account_id\n" +
            "join job on job.id = apply_job.job_id\n" +
            "join company on company.id = job.company_id \n" +
            "join role on role.id = account.role_id WHERE apply_job.id=:id")
    ListApplyJob showApplyJobOfUserByCompanyCV(@Param("id") int id);

    @Query(nativeQuery = true,value = "select account.id as idAccount,email,account.name as nameAcc,account.status as statusAcc,\n" +
            "       apply_job.id as idApply, count,cv,message,apply_job.status as statusApply,\n" +
            "       job.id as idJob,job.code as codeJob,job.status as statusJob,title,\n" +
            "       company.id as idCompany,short_name,role.id as idRole\n" +
            "            from account join apply_job on account.id = apply_job.account_id\n" +
            "            join job on job.id = apply_job.job_id\n" +
            "            join company on company.id = job.company_id \n" +
            "            join role on role.id = account.role_id WHERE (role.id = 2 and company.id=?2) and  (account.name like ?1 or title like ?1 or job.code like ?1)")
    List<ListApplyJob> searchApplyJobsWithUser(String key,int idCompany);

    // Check số lượng ứng viên mà doanh nghiệp đã tuyển
    @Query(nativeQuery = true,value = "SELECT job_id as idJob,sum(count) as countJob,quantity from apply_job join job on job.id = apply_job.job_id\n" +
            "where job_id = ?1 and (count =1 or count =0) GROUP BY job_id")
    CheckQuantityApplyJob checkQuantityApplyJob(int idJob);
}
