package com.casemd6_be.repository;

import com.casemd6_be.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.websocket.server.PathParam;
import java.util.List;

public interface IAccountRepo extends CrudRepository<Account, Integer> {
    Account findAccountByEmail(String email);
    Account findAccountsByPhone(String phone);
    Account findAccountById(int id);
    Account findAccountByEmailAndPassword(String email,String password);

    @Query(nativeQuery = true,value = "SELECT * FROM casemodule6.account where account.role_id=2")
    List <Account> listAccountIdRoleEqual2();
    @Query(nativeQuery = true,value = "SELECT * FROM casemodule6.account where account.role_id=2 and account.name like :name")
    List <Account> searchAccountRoleEqual2(@PathParam("name") String name);
    @Query(nativeQuery = true,value = "SELECT * FROM casemodule6.account where account.role_id=2 limit 3")
    List <Account> listAccountIdRoleEqual2limit3();
}
