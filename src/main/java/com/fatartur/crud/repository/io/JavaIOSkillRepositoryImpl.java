package main.java.com.fatartur.crud.repository.io;

import main.java.com.fatartur.crud.model.Skill;
import main.java.com.fatartur.crud.repository.SkillRepository;

import java.util.List;
import java.util.stream.Collectors;

public class JavaIOSkillRepositoryImpl implements SkillRepository {
    private final static String FILE_NAME = "skill.txt";

    @Override
    public Skill save(Skill val) throws Exception {
        List<Skill> list = getAllInternal();
        val.setId(nextNum(list));
        list.add(val);
        IOSystem.write(FILE_NAME, convertToString(list));
        return val;
    }

    private Long nextNum(List<Skill> list) {
        return list.stream().mapToLong(Skill::getId).max().getAsLong() + 1;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        List<Skill> list = getAllInternal();
        if (!list.removeIf(s -> s.getId().equals(id))) {
            throw new Exception("Отсутсвует данный ID");
        }
        IOSystem.write(FILE_NAME, convertToString(list));
    }

    @Override
    public Skill getByID(Long id) throws Exception {
        List<Skill> skills = getAllInternal();
        return skills.stream().filter(s -> s.getId().equals(id)).findFirst().
                orElse(null);
    }

    @Override
    public List<Skill> getAll() throws Exception {
        return getAllInternal();
    }

    @Override
    public Skill update(Skill val) throws Exception {
        List<Skill> skills = getAllInternal();
        skills.forEach(s -> {
            if (s.getId().equals(val.getId())) {
                s.setName(val.getName());
            }
        });
        IOSystem.write(FILE_NAME, convertToString(skills));
        return val;
    }

    private List<Skill> convertToData(List<String> val) {
        return val.stream().map(s -> s.split(",")).map(s -> {
            Skill skill = new Skill();
            skill.setId(Long.parseLong(s[0]));
            skill.setName(s[1]);
            return skill;
        }).collect(Collectors.toList());
    }

    private List<String> convertToString(List<Skill> val) {
        return val.stream().map(s -> s.getId() + "," + s.getName()).collect(Collectors.toList());
    }

    private List<Skill> getAllInternal() throws Exception {
        List<String> list = IOSystem.read(FILE_NAME);
        return convertToData(list);
    }

}
