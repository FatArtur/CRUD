package main.java.com.fatartur.crud.controller;

import main.java.com.fatartur.crud.model.Account;
import main.java.com.fatartur.crud.model.Developer;
import main.java.com.fatartur.crud.model.Skill;
import main.java.com.fatartur.crud.repository.AccountRepository;
import main.java.com.fatartur.crud.repository.DeveloperRepository;
import main.java.com.fatartur.crud.repository.SkillRepository;
import main.java.com.fatartur.crud.repository.io.JavaIOAccountRepositoryImpl;
import main.java.com.fatartur.crud.repository.io.JavaIODeveloperRepositoryImpl;
import main.java.com.fatartur.crud.repository.io.JavaIOSkillRepositoryImpl;

import java.util.List;

public class DeveloperController {
    private DeveloperRepository repo = new JavaIODeveloperRepositoryImpl();

    public List<Account> getAccounts() throws Exception{
        AccountRepository accountRepository = new JavaIOAccountRepositoryImpl();
        return accountRepository.getAll();
    }

    public Account giveAccount(String s) throws Exception{
        AccountRepository accountRepository = new JavaIOAccountRepositoryImpl();
        return accountRepository.getByID(Long.parseLong(s));
    }

    public List<Skill> getSkills() throws Exception{
        SkillRepository skillRepository = new JavaIOSkillRepositoryImpl();
        return skillRepository.getAll();
    }

    public Skill giveSkill(String s) throws Exception{
        SkillRepository skillRepository = new JavaIOSkillRepositoryImpl();
        return skillRepository.getByID(Long.parseLong(s));
    }

    public Developer create(String val, Account account, List<Skill> skills) throws Exception {
        Developer developer = new Developer();
        developer.setName(val);
        developer.setAccount(account);
        developer.setSkill(skills);
        return repo.save(developer);
    }

    public void delete(String val) throws Exception {
        repo.deleteById(Long.parseLong(val));
    }

    public Developer getByID(String val) throws Exception {
        Developer developer = repo.getByID(Long.parseLong(val));
        if (developer == null) {
            System.out.println("Введено не корректное значение");
        }
        return developer;
    }

    public List<Developer> getAll() throws Exception {
        return repo.getAll();
    }

    public Developer update(String val1, String val2, Account account, List<Skill> skill) throws Exception {
        Developer developer = new Developer();
        developer.setId(Long.parseLong(val1));
        developer.setName(val2);
        developer.setAccount(account);
        developer.setSkill(skill);
        return repo.update(developer);
    }


}
