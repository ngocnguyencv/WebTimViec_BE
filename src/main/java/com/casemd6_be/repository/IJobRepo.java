package com.casemd6_be.repository;

import com.casemd6_be.model.Job;
import com.casemd6_be.model.query.ListJobCompanyAccount;
import com.casemd6_be.model.query.ListTopCompany;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import javax.websocket.server.PathParam;

public interface IJobRepo extends CrudRepository<Job, Integer> {
    List<Job> findJobsByCompanyId(Long id);

    @Query(nativeQuery = true, value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where Job.status = 1 and expired_date >= now()")
    List<ListJobCompanyAccount> joinCompanyAndJobAndAccount();

    @Query(nativeQuery = true, value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where Job.id=:id and job.status = 1 and expired_date >= now()")
    ListJobCompanyAccount joinCompanyAndJobAndAccountbyid(@PathParam("id") int id);

    @Query(nativeQuery = true, value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id \n" +
            "where company.short_name like :short_name ")
    List<ListJobCompanyAccount> searchByCompany(@PathParam("short_name") String short_name);

    @Query(nativeQuery = true,value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where email =:email and (Job.status = 1 or Job.status = 2 or job.status = 0) and expired_date >= now()")
    List<ListJobCompanyAccount> joinCompanyAndJobAndAccountByEmail(@Param("email") String email);

    Job findJobsById(Integer id);

    // Hien thi Job User
    @Query(nativeQuery = true, value = "select company_id, short_name, avatar, sum(quantity) as sum_quantity from job \n" +
            "left join company on company_id = company.id \n" +
            "join account on account.id = company.account_id\n" +
            "group by company_id order by sum(quantity) desc\n" +
            "limit 6")
    List<ListTopCompany> joinCompanyAndJobAndAccount1();

    // Hien thi Job Admin, Hien thi, khoa Job Admin
    @Query(nativeQuery = true, value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where (Job.status = 1 or Job.status = 2 or job.status = 0) and expired_date >= now()")
    List<ListJobCompanyAccount> joinCompanyAndJobAndAccount2();

    // 7 API Search job

    @Query(nativeQuery = true,value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where (title LIKE ?1 or account.address LIKE ?1) and job.status = 1 and expired_date >= now()")
    List<ListJobCompanyAccount> searchJobsByTitleOrAddress(String key);

    @Query(nativeQuery = true,value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where category.id = ?1 and job.status = 1 and expired_date >= now()")
    List<ListJobCompanyAccount> searchJobsByNameCategory(int idCategory);

    @Query(nativeQuery = true,value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where location.id = ?1 and job.status = 1 and expired_date >= now()")
    List<ListJobCompanyAccount> searchJobsByNameLocation(int idLocation);

    @Query(nativeQuery = true,value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where (title LIKE ?1 or account.address LIKE ?1) and category.id = ?2 and job.status = 1 and expired_date >= now() ")
    List<ListJobCompanyAccount> searchJobsByTitleAndAddressAndCategory(String key,int idCategory);

    @Query(nativeQuery = true,value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where (title LIKE ?1 or account.address LIKE ?1) and location.id = ?2 and job.status = 1 and expired_date >= now() ")
    List<ListJobCompanyAccount> searchJobsByTitleAndAddressAndLocation(String key,int idLocation);

    @Query(nativeQuery = true,value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where category.id = ?1 and location.id = ?2 and job.status = 1 and expired_date >= now()")
    List<ListJobCompanyAccount> searchJobsByCategoryAndLocation(int idCategory,int idLocation);

    @Query(nativeQuery = true,value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where (title LIKE ?1 or account.address LIKE ?1) and category.id = ?2 and location.id = ?3 and job.status = 1 and expired_date >= now()")
    List<ListJobCompanyAccount> searchJobsByTitleAddressCategoryLocation(String key,int idCategory,int idLocation);

    // Sort Job by salary
    @Query(nativeQuery = true,value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where salary_min <=500 and salary_max <=500 and job.status = 1 and expired_date >= now()")
    List<ListJobCompanyAccount> sortJobBySalaryMin();

    @Query(nativeQuery = true,value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where salary_min >500 and salary_min <=1000 and salary_max > 500 and salary_max <=1000 and job.status = 1 and expired_date >= now()")
    List<ListJobCompanyAccount> sortJobBySalary1000();

    @Query(nativeQuery = true,value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where salary_min >1000 and salary_min <=2000 and salary_max > 1000 and salary_max <=2000 and job.status = 1 and expired_date >= now()")
    List<ListJobCompanyAccount> sortJobBySalary2000();

    @Query(nativeQuery = true,value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id\n" +
            "join category on category.id = job.category_id\n" +
            "join location on location.id = job.location_id where salary_min >1000 and salary_min <=2000 and salary_max > 1000 and salary_max <=2000 and job.status = 1 and expired_date >= now()")
    List<ListJobCompanyAccount> sortJobBySalaryMax();
    @Query(nativeQuery = true, value = "select job.id as idJob,account.address ,job.code as codeJob,job.description as descriptionJob,exp_year, expired_date,gender,\n" +
            "quantity,salary_min as min,salary_max as max,job.status as statusJob,title,company.code as codeCompany,google_map,number_of_employees,\n" +
            "short_name,website,avatar,banner,account.description as descriptionAcc,email,account.name as nameAcc,phone,account.status as statusAcc,\n" +
            "category.name as nameCategory, location.name as nameLocation\n" +
            "from job join company on company.id = job.company_id\n" +
            "join account on company.account_id=account.id \n" +
            "join category on category.id = job.category_id \n" +
            "join location on location.id = job.location_id  where (Job.status = 1 or Job.status = 2 or job.status = 0) and expired_date >= now() limit 3  ;")
    List<ListJobCompanyAccount> joinCompanyAndJobAndAccount2limit3();

    @Query(nativeQuery = true, value = "SELECT * from job ORDER BY id desc limit 1")
    Job findJobForCreateCodeJob();
}
