package com.casemd6_be.controller;

import com.casemd6_be.model.Account;
import com.casemd6_be.model.dto.UpImage;
import com.casemd6_be.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class AccountAPI {
    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> editAccount(@RequestBody Account account){
        Account account1 = accountService.findAccountByUsername(account.getEmail());
        if (accountService.findAccountByPhone(account.getPhone()) == null || Objects.equals(account.getPhone(), account1.getPhone()))
         {
            accountService.save(account);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/editUser/{email}")
    public ResponseEntity<Account>finduserbyid(@PathVariable String email){
        return new ResponseEntity<>(accountService.findAccountByUsername(email),HttpStatus.OK);
    }

    @PostMapping("/editUser")
    private ResponseEntity<?>saveUser(@RequestBody Account account){
        accountService.save(account);
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

    @GetMapping("/show/{id}")
    public ResponseEntity<Account>findAccountByID(@PathVariable int id){
        return new ResponseEntity<>(accountService.findbyid(id),HttpStatus.OK);
    }
}
