package controller;

import model.Account;
import model.AccountStatus;
import model.Developer;
import model.Skill;
import repository.DeveloperRepository;
import repository.io.JavaIODeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {
    private DeveloperRepository repo = new JavaIODeveloperRepositoryImpl();

    public Developer create(String val) throws Exception {
        Developer developer = new Developer();
        developer.setName(val);
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
