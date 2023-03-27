package com.casemd6_be.controller;

import com.casemd6_be.model.Company;
import com.casemd6_be.model.dto.UpImage;
import com.casemd6_be.model.query.CompanyAndAccount;
import com.casemd6_be.model.query.ListJobCompanyAccount;
import com.casemd6_be.service.AccountService;
import com.casemd6_be.service.CompanyService;
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
@RequestMapping("/company")
public class CompanyAPI {
    @Autowired
    CompanyService companyService;

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<Company> editCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>(HttpStatus.OK);
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

    @GetMapping("/showCompany/{email}")
    public ResponseEntity<CompanyAndAccount> getAllJob(@PathVariable String email) {
        return new ResponseEntity<>(companyService.getAllCompany(email),HttpStatus.OK);
    }

    @GetMapping("/businessUserDetails/{id}")
    public ResponseEntity<ListJobCompanyAccount>getOneCompany(@PathVariable int id) {
        return new ResponseEntity<>(companyService.getOneCompany(id),HttpStatus.OK);
    }

    @GetMapping("/searchJobByTitleAndEmailOfCompany")
    public ResponseEntity<List<ListJobCompanyAccount>> searchJobByTitleAndEmailOfCompany(@RequestParam(name = "email") String email,@RequestParam(name = "title") String title) {
        return new ResponseEntity<>(companyService.searchJobByTitleAndEmailOfCompany(email,'%' + title + '%'),HttpStatus.OK);
    }
}
