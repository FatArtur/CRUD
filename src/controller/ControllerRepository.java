package controller;

import model.Skill;
import repository.GenericRepository;
import repository.JavaIOSkillRepositoryImpl;
import java.util.List;

public class ControllerRepository {
    private GenericRepository grSkill = new JavaIOSkillRepositoryImpl();

    public Skill create(String val) throws Exception {
        Skill skill = new Skill();
        skill.setName(val);
        grSkill.save(skill);
        return skill;
    }

    public void delete(String val) throws Exception {
        grSkill.deleteById(Long.parseLong(val));
    }

    public Skill getByID(String val) throws Exception {
        Skill skill = (Skill) grSkill.getByID(Long.parseLong(val));
        System.out.println("ID = " + skill.getId() + " Skill = " + skill.getName());
        return skill;
    }

    public void getAll() throws Exception {
        List<Skill> list = grSkill.getAll();
        list.stream().forEach(s-> System.out.println("ID = " + s.getId() + " Skill = " + s.getName()));
    }

    public Skill update(String val1, String val2) throws Exception{
        Skill skill = new Skill();
        skill.setId(Long.parseLong(val1));
        skill.setName(val2);
        return (Skill) grSkill.update(skill);
    }

}
