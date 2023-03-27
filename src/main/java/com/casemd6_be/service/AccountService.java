package com.casemd6_be.service;

import com.casemd6_be.model.Account;
import com.casemd6_be.model.Role;
import com.casemd6_be.repository.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    IAccountRepo iAccountRepo;

    public List<Account> showAll() {
        return (List<Account>) iAccountRepo.findAll();
    }

    public void save(Account account) {
        iAccountRepo.save(account);
    }

    public Account findAccountByUsername(String email) {
        return iAccountRepo.findAccountByEmail(email);
    }

    public Account findAccountByPhone(String phone) {
        return iAccountRepo.findAccountsByPhone(phone);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = iAccountRepo.findAccountByEmail(email);
        List<Role> roles = new ArrayList<>();
        roles.add(account.getRole());
        return new User(account.getEmail(), account.getPassword(), roles);
    }

    public Account findbyid(int id){
        return iAccountRepo.findAccountById(id);
    }

    public List<Account> getallAccountEqual2(){
        return iAccountRepo.listAccountIdRoleEqual2();
    }

    public List<Account> searchUserByName(String name){
        return iAccountRepo.searchAccountRoleEqual2(name);
    }

    public List<Account> getallAccountEqual2limit3(){
        return iAccountRepo.listAccountIdRoleEqual2limit3();
    }

    public Account findAccountByEmailAndPassword(String email,String password) {
        return iAccountRepo.findAccountByEmailAndPassword(email,password);
    }
}
