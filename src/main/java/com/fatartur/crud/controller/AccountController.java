package main.java.com.fatartur.crud.controller;

import main.java.com.fatartur.crud.model.Account;
import main.java.com.fatartur.crud.model.AccountStatus;
import main.java.com.fatartur.crud.repository.AccountRepository;
import main.java.com.fatartur.crud.repository.io.JavaIOAccountRepositoryImpl;
import main.java.com.fatartur.crud.repository.io.jsonIO.JavaIOAccountRepJSON;

import java.util.List;


public class AccountController {
    private AccountRepository repo = new JavaIOAccountRepJSON();

    public Account create(String val) throws Exception {
        Account account = new Account();
        account.setName(val);
        return repo.save(account);
    }

    public void delete(String val) throws Exception {
        repo.deleteById(Long.parseLong(val));
    }

    public Account getByID(String val) throws Exception {
        Account account = repo.getByID(Long.parseLong(val));
        if (account == null) {
            System.out.println("Введено не корректное значение");
        }
        return account;
    }

    public void getAll() throws Exception {
        List<Account> list = repo.getAll();
        list.stream().forEach(s -> System.out.println("ID = " + s.getId() + " Skill = " + s.getName() +
                " STATUS: " + s.getAccountStatus()));
    }

    public Account update(String val1, String val2, AccountStatus val3) throws Exception {
        Account account = new Account();
        account.setId(Long.parseLong(val1));
        account.setName(val2);
        account.setAccountStatus(val3);
        return repo.update(account);
    }
}
