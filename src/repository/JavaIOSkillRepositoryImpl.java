package repository;

import model.Skill;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaIOSkillRepositoryImpl implements SkillRepository{
    private final static String FILE_NAME = "skill.txt";
    @Override
    public void save(Skill val) throws Exception {
        List<Skill> list = getAll();
        val.setId((long)list.size() + 1);
        list.add(val);
        IOSystem.write(FILE_NAME, convertToString(list));
    }

    @Override
    public void deleteById(Long id) throws Exception {
        List<Skill> list = getAll();
        final long[] i = {1};
        if  (list.removeIf(s -> s.getId().equals(id))) {
            list.forEach(s -> { s.setId(i[0]++);});
            IOSystem.write(FILE_NAME, convertToString(list));
        }
        else throw new Exception("Отсутсвует данный ID");


//        Skill removeSkill = null;
//        for (Skill val: list) {
//            if (val.getId().equals(id)) {
//                removeSkill = val;
//            }
//        }
//        if (removeSkill != null) {
//            list.remove(removeSkill);
//            IOSystem.write(FILE_NAME, convertToString(list));
//        }
//        else throw new Exception("Отсутсвует данный ID");

    }

    @Override
    public Skill getByID(Long id) throws Exception {
        Skill result = null;
        List<Skill> skills = getAll();
        result= skills.stream().filter(s-> s.getId().equals(id)).findFirst().get();
//        for (Skill val: skills) {
//            if (val.getId().equals(id)) {result = val;}
//        }
        if (result != null) return result;
        else throw new Exception("Отсутсвует данный ID");
    }

    @Override
    public List<Skill> getAll() throws Exception {
        List<String> list = IOSystem.read(FILE_NAME);
        return convertToData(list);
    }

    @Override
    public void update(Skill val) throws Exception {
        deleteById(val.getId());
        save(val);
    }

    @Override
    public List<Skill> convertToData(List<String> val) {
        List<Skill> list = new ArrayList<>();
        for (String line: val) {
            String[] mas = line.split(",");
            Skill skill = new Skill();
            skill.setId(Long.parseLong(mas[0]));
            skill.setName(mas[1]);
            list.add(skill);
        }
        return list;
    }

    @Override
    public List<String> convertToString(List<Skill> val) {
        List<String> list = new ArrayList<>();
        for (Skill data: val) {
            list.add(data.getId() + "," + data.getName());
        }
        return list;
    }
}
