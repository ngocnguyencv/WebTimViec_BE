package com.casemd6_be.controller;

import com.casemd6_be.model.Account;
import com.casemd6_be.model.ApplyJob;
import com.casemd6_be.model.Job;
import com.casemd6_be.model.dto.UpImage;
import com.casemd6_be.model.query.CheckQuantityApplyJob;
import com.casemd6_be.model.query.ListApplyJob;
import com.casemd6_be.model.query.ListJobCompanyAccount;
import com.casemd6_be.service.AccountService;
import com.casemd6_be.service.ApplyJobService;
import com.casemd6_be.service.JobService;
import com.casemd6_be.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/applyJob")
public class ApplyJobAPI {
    @Autowired
    ApplyJobService applyJobService;

    @Autowired
    AccountService accountService;

    @Autowired
    SendEmailService sendEmailService;

    @Autowired
    JobService jobService;

    @GetMapping("/{id}")
    public ResponseEntity<ApplyJob> findOneApplyJobById(@PathVariable int id) {
        return new ResponseEntity<>(applyJobService.findOneApplyJobById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ApplyJob>> findAllApplyJob() {
        return new ResponseEntity<>(applyJobService.findAllApplyJob(), HttpStatus.OK);
    }

    @GetMapping("/cv/{idApply}")
    public ResponseEntity<ListApplyJob> showApplyJobOfUserByCompanyCV(@PathVariable int idApply) {
        return new ResponseEntity<>(applyJobService.showApplyJobOfUserByCompanyCV(idApply), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApplyJob> applyJobUser(@RequestBody ApplyJob applyJob,@RequestParam(name = "email") String email,@RequestParam(name = "idJob") int idJob) {
        Account account = accountService.findAccountByUsername(email);
        List<ApplyJob> applyJobList = applyJobService.findAllApplyJob();

        account.setId(account.getId());
        applyJob.setAccount(account);
        Job job = new Job();
        job.setId(idJob);
        applyJob.setJob(job);
        int a = applyJob.getAccount().getId();
        int b = applyJob.getJob().getId();

        for (int i = 0; i < applyJobList.size(); i++) {
            if(a == applyJobList.get(i).getAccount().getId() && b == applyJobList.get(i).getJob().getId()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        applyJob.setCount(0);
        applyJob.setStatus(1);
        applyJob.setMessage("Đang chờ phê duyệt");

        applyJobService.save(applyJob);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cancel")
    public ResponseEntity<ApplyJob> CancelApplyJobUser(@RequestBody ApplyJob applyJob) {
        ApplyJob applyJob1 = applyJobService.findOneApplyJobById(applyJob.getId());

        applyJob1.setStatus(0);
        applyJob1.setMessage("Đã hủy nộp CV!");
        applyJob1.setMessage("Đã hủy!");
        applyJobService.save(applyJob1);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cancelApplyJobOfCompany")
    public ResponseEntity<ApplyJob> cancelApplyJobOfCompany(@RequestBody ApplyJob applyJob) {
        ApplyJob applyJob1 = applyJobService.findOneApplyJobById(applyJob.getId());
        Account account = accountService.findbyid(applyJob1.getAccount().getId());
        ListJobCompanyAccount job = jobService.getOneJobbyID(applyJob1.getJob().getId());

        applyJob1.setStatus(3);
        applyJob1.setMessage("Yêu cầu ứng tuyển của bạn đã bị hủy!");

        applyJobService.save(applyJob1);
        sendEmailService.sendMail(account.getEmail(), "Thông báo từ doanh nghiệp: " + job.getNameAcc(),
                "Xin chào: " +account.getName()+ "; " +
                        "Đơn xin việc của bạn vào mã công việc:" + job.getCodeJob() + " không được chấp nhận. Xin cảm ơn sự quan tâm của các bạn đến công ty chúng tôi!");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/confirmApplyJobOfCompany")
    public ResponseEntity<ApplyJob> confirmApplyJobOfCompany(@RequestBody ApplyJob applyJob) {
        ApplyJob applyJob1 = applyJobService.findOneApplyJobById(applyJob.getId());

        Account account = accountService.findbyid(applyJob1.getAccount().getId());
        ListJobCompanyAccount job = jobService.getOneJobbyID(applyJob1.getJob().getId());

        applyJob1.setStatus(2);
        applyJob1.setMessage("Yêu cầu ứng tuyển đã được xác nhận!");

        applyJobService.save(applyJob1);
        sendEmailService.sendMail(account.getEmail(), "Thông báo từ doanh nghiệp: " + job.getNameAcc(),
                "Xin chào: " +account.getName()+ "; " +
                        "Đơn xin việc của bạn vào mã công việc:" + job.getCodeJob() + " đã được chấp nhận. Chúng tôi sẽ gửi lịch phỏng vấn tới email của bạn sau!");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Xác nhận tuyển từ công ty
    @PostMapping("/confirmRecruitOfCompany")
    public ResponseEntity<ApplyJob> confirmRecruitOfCompany(@RequestBody ApplyJob applyJob) {
        ApplyJob applyJob1 = applyJobService.findOneApplyJobById(applyJob.getId());
        Account account = accountService.findbyid(applyJob1.getAccount().getId());
        ListJobCompanyAccount job = jobService.getOneJobbyID(applyJob1.getJob().getId());
        CheckQuantityApplyJob checkQuantityApplyJob = applyJobService.checkQuantityApplyJob(applyJob1.getJob().getId());
        if(checkQuantityApplyJob.getCountJob() < checkQuantityApplyJob.getQuantity()){
            applyJob1.setCount(1);
            applyJob1.setMessage("Xin chúc mừng bạn đã trúng tuyển!");
            applyJobService.save(applyJob1);
            sendEmailService.sendMail(account.getEmail(), "Thông báo từ doanh nghiệp: " + job.getNameAcc(),
                    "Xin chào: " +account.getName()+ "; " +
                            "Chúc mừng bạn đã trúng tuyển!");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Xác nhận không tuyển từ công ty
    @PostMapping("/cancelRecruitOfCompany")
    public ResponseEntity<ApplyJob> cancelRecruitOfCompany(@RequestBody ApplyJob applyJob) {
        ApplyJob applyJob1 = applyJobService.findOneApplyJobById(applyJob.getId());

        Account account = accountService.findbyid(applyJob1.getAccount().getId());
        ListJobCompanyAccount job = jobService.getOneJobbyID(applyJob1.getJob().getId());

        applyJob1.setCount(2);
        applyJob1.setMessage("Xin lỗi, CV của bạn chưa phù hợp với công việc.\n" +
                "Hẹn gặp lại bạn ở những lần tuyển dụng sau!");
        applyJobService.save(applyJob1);
        sendEmailService.sendMail(account.getEmail(), "Thông báo từ doanh nghiệp: " + job.getNameAcc(),
                "Xin chào: " +account.getName()+ "; " +
                        "Bạn không đủ điều kiện trúng tuyển");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Quay lại trạng thái duyệt tuyển từ công ty
    @PostMapping("/backRecruitOfCompany")
    public ResponseEntity<ApplyJob> backRecruitOfCompany(@RequestBody ApplyJob applyJob) {
        ApplyJob applyJob1 = applyJobService.findOneApplyJobById(applyJob.getId());

        applyJob1.setCount(0);
        applyJob1.setMessage("Đang chờ phê duyệt...");
        applyJobService.save(applyJob1);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/upImg")
    public UpImage upImg(@RequestParam MultipartFile fileImg) {
        String nameImg = fileImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(fileImg.getBytes(), new File("C:\\Users\\Admin\\Desktop\\CASE_MODUL_6\\Find_Jobs_FE\\src\\assets\\img\\" + nameImg));
            return new UpImage("assets/img/" + nameImg) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Tìm tất cả các Job của một người dùng đã apply
    @GetMapping("/account/{email}")
    public ResponseEntity<List<ListApplyJob>> showAllApplyJobByUser(@PathVariable String email) {
        return new ResponseEntity<>(applyJobService.showAllApplyJobByUser(email), HttpStatus.OK);
    }

    // Tìm tất cả các Job của công ty theo ứng viên
    @GetMapping("/company/{idCompany}")
    public ResponseEntity<List<ListApplyJob>> showAllApplyJobOfUserByCompany(@PathVariable int idCompany) {
        return new ResponseEntity<>(applyJobService.showAllApplyJobOfUserByCompany(idCompany), HttpStatus.OK);
    }

    // Tìm kiếm các Job theo tên người dùng, tiêu đề, mã công việc
    @GetMapping("/company/search")
    public ResponseEntity<List<ListApplyJob>> searchApplyJobsWithUser(@RequestParam(name = "key") String key,@RequestParam(name = "idCompany")  int idCompany) {
        return new ResponseEntity<>(applyJobService.searchApplyJobsWithUser('%' + key + '%',idCompany), HttpStatus.OK);}
}
