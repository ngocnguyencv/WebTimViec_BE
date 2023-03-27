package com.casemd6_be.controller;

import com.casemd6_be.model.Account;
import com.casemd6_be.model.dto.AccountToken;
import com.casemd6_be.service.AccountService;
import com.casemd6_be.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginAPI {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountToken> login(@RequestBody Account account) {
        Account checkAccount = accountService.findAccountByEmailAndPassword(account.getEmail(), account.getPassword());
        if(checkAccount != null){
            // Tạo ra 1 đối tượng xác thực
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword())
            );
            // Nơi chứa đối tượng đang đăng nhập
            // Truyền đối tượng đăng nhập vào securityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Tạo ra token
            String token = jwtService.createToken(authentication);
            Account account1 = accountService.findAccountByUsername(account.getEmail());
            return new ResponseEntity<>(new AccountToken(account1.getId(), account1.getEmail(), account1.getRole(),token),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<Account> findAccountByEmail(@PathVariable String email) {
        return new ResponseEntity<>(accountService.findAccountByUsername(email), HttpStatus.OK);
    }
}
