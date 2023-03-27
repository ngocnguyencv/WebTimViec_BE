package com.casemd6_be.controller;

import com.casemd6_be.model.Account;
import com.casemd6_be.model.Job;
import com.casemd6_be.model.query.ListJobCompanyAccount;
import com.casemd6_be.model.query.CompanyAndAccount;
import com.casemd6_be.service.AccountService;
import com.casemd6_be.service.CompanyService;
import com.casemd6_be.service.SendEmailService;
import com.casemd6_be.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminAPI {
    @Autowired
    JobService jobService;
    @Autowired
    CompanyService companyService;
    @Autowired
    AccountService accountService;
    @Autowired
    SendEmailService sendEmailService;

    // Admin Job, Active, blog Job
    @GetMapping("/showAdminJob")
    public ResponseEntity<List<ListJobCompanyAccount>> getAdminJob() {
        return new ResponseEntity<>(jobService.getAdminJob(), HttpStatus.OK);
    }

    @GetMapping("blogJob/{id}")
    public ResponseEntity<?> AdminBlockJob(@PathVariable int id) {
        Job job =  jobService.findJobById(id);
        int value = 2;  //Khóa
        int value2 = 1; // Mở
        if (job.getStatus() == 2 || job.getStatus() == 0) {
            job.setStatus(value2);
            jobService.save(job);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            job.setStatus(value);
            jobService.save(job);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/ShowCompanyAdmin")
    public ResponseEntity<List<CompanyAndAccount>> getallCompanyNoEamil() {
        return new ResponseEntity<>(companyService.getallCompanyNoEamil(), HttpStatus.OK);
    }

    @GetMapping("blogComPany/{id}")
    public ResponseEntity<?> AdminBlockCompany(@PathVariable int id) {
        Account account = accountService.findbyid(id);
        boolean value = false;//Khoa
        boolean value2 = true;
        if (account.getStatus() == true) {
            account.setStatus(value);
            accountService.save(account);
            sendEmailService.sendMail(account.getEmail(), "Thông báo", "Tài khoản: " + account.getName() + " đã bị khóa bởi hệ thống vui lòng liên hệ tổng đài 19006868 để được hỗ trợ!");

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            account.setStatus(value2);
            accountService.save(account);
            sendEmailService.sendMail(account.getEmail(), "Thông báo", "Tài khoản: " + account.getName() + " đã được mở khóa!" +
                    "Tài khoản: " + account.getEmail() + ", Mật khẩu: " + account.getPassword() +
                    "Bạn đã có thể đăng nhập! " +
                    "Chào mừng bạn trở lại");
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/showUserRole2")
    public ResponseEntity<List<Account>> getallAccountEqual2() {
        return new ResponseEntity<>(accountService.getallAccountEqual2(), HttpStatus.OK);
    }

    @GetMapping("BlogUser/{id}")
    public ResponseEntity<?> AdminBlockUser(@PathVariable int id) {
        Account account = accountService.findbyid(id);
        boolean value = false;//Khoa
        boolean value2 = true;
        if (account.getStatus() == true) {
            account.setStatus(value);
            accountService.save(account);
            sendEmailService.sendMail(account.getEmail(), "Thông báo", "Tài khoản: " +
                    account.getName() + " đã bị khóa bởi hệ thống vui lòng liên hệ tổng đài 19006868 để được hỗ trợ!");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            account.setStatus(value2);
            accountService.save(account);
            sendEmailService.sendMail(account.getEmail(), "Thông báo", "Tài khoản: " + account.getName() + " đã được mở khóa!" +
                    "Tài khoản: " + account.getEmail() + ", Mật khẩu: " + account.getPassword() +
                    "Bạn đã có thể đăng nhập! " +
                    "Chào mừng bạn trở lại!");

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/searchUser/{name}")
    public ResponseEntity<List<Account>> searchUserByName(@PathVariable String name) {
        // String value= '%'+name+'%';
        return new ResponseEntity<>(accountService.searchUserByName('%' + name + '%'), HttpStatus.OK);
    }

    @GetMapping("/searchCompany/{name}")
    public ResponseEntity<List<CompanyAndAccount>> searchCompanyByName(@PathVariable String name) {
        return new ResponseEntity<>(companyService.searchBynameCompany('%' + name + '%'), HttpStatus.OK);
    }
    @GetMapping("/adminCompany")
    public ResponseEntity<List<CompanyAndAccount>> getallCompanylimit3(){
        return new ResponseEntity<>(companyService.getallLimit3(),HttpStatus.OK);
    }
    @GetMapping("/adminUser")
    public ResponseEntity<List<Account>> getallUserlimit3(){
        return new ResponseEntity<>(accountService.getallAccountEqual2limit3(),HttpStatus.OK);
    }
    @GetMapping("/AdminJob")
    public ResponseEntity<List<ListJobCompanyAccount>> getAdminJoblimit3() {
        return new ResponseEntity<>(jobService.getAdminJoblimt3(), HttpStatus.OK);
    }
}
