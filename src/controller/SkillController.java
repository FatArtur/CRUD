package controller;

import model.Skill;
import repository.io.JavaIOSkillRepositoryImpl;
import repository.SkillRepository;

import java.util.List;

public class SkillController {
    private SkillRepository repo = new JavaIOSkillRepositoryImpl();

    public Skill create(String val) throws Exception {
        Skill skill = new Skill();
        skill.setName(val);
        return repo.save(skill);
    }

    public void delete(String val) throws Exception {
        repo.deleteById(Long.parseLong(val));
    }

    public Skill getByID(String val) throws Exception {
        Skill skill = (Skill) repo.getByID(Long.parseLong(val));
        if (skill == null) {
            System.out.println("Введено не корректное значение");
        } else
            System.out.println("ID = " + skill.getId() + " Skill = " + skill.getName());
        return skill;
    }

    public void getAll() throws Exception {
        List<Skill> list = repo.getAll();
        list.stream().forEach(s -> System.out.println("ID = " + s.getId() + " Skill = " + s.getName()));
    }

    public Skill update(String val1, String val2) throws Exception {
        Skill skill = new Skill();
        skill.setId(Long.parseLong(val1));
        skill.setName(val2);
        return (Skill) repo.update(skill);
    }

}
