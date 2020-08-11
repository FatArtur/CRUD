package repository.io;

import controller.AccountController;
import controller.SkillController;
import model.Account;
import model.Developer;
import model.Skill;
import repository.DeveloperRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    private final static String FILE_NAME = "developer.txt";

    @Override
    public Developer save(Developer val) throws Exception {
        List<Developer> list = getAll();
        val.setId(nextNum(list));
        list.add(val);
        IOSystem.write(FILE_NAME, convertToString(list));
        return val;
    }

    private Long nextNum(List<Developer> list){
        return list.stream().mapToLong(Developer::getId).max().getAsLong() + 1;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        List<Developer> list = getAllInternal();
        if (!list.removeIf(s -> s.getId().equals(id))) {
            throw new Exception("Отсутсвует данный ID");
        }
        IOSystem.write(FILE_NAME, convertToString(list));
    }

    @Override
    public Developer getByID(Long id) throws Exception {
        List<Developer> developer = getAllInternal();
        return developer.stream().filter(s -> s.getId().equals(id)).findFirst().
                orElse(null);
    }

    @Override
    public List<Developer> getAll() throws Exception {
        return getAllInternal();
    }

    @Override
    public Developer update(Developer val) throws Exception {
        List<Developer> developers = getAllInternal();
        developers.forEach(s -> {
            if (s.getId().equals(val.getId())) {
                s.setName(val.getName());
                s.setAccount(val.getAccount());
                s.setSkill(val.getSkill());
            }
        });
        IOSystem.write(FILE_NAME, convertToString(developers));
        return val;
    }

    private List<Developer> convertToData(List<String> val) {
        return  val.stream().map(s-> s.split("/")).map(s->{
            Developer developer = new Developer();
            developer.setId(Long.parseLong(s[0]));
            developer.setName(s[1]);
            developer.setAccount(convertToAccount(s[2]));
            developer.setSkill(convertToSkill(s[3]));
            return developer;
        }).collect(Collectors.toList());
    }

    private Account convertToAccount(String val) {
        AccountController account = new AccountController();
        try {
            return account.getByID(val);
        } catch (Exception e){
            return null;
        }
    }

    private List<Skill> convertToSkill(String val) {
        String[] txt = val.split(",");
        return Arrays.stream(txt).map(s->{
            SkillController skillController = new SkillController();
            try {
                return skillController.getByID(s);
            } catch (Exception e){
                return null;
            }
        }).collect(Collectors.toList());
    }

    private List<String> convertToString(List<Developer> val) {
        return val.stream().map(s-> s.getId() + "/" + s.getName() + "/" + s.getAccount().getId() + "/" +
                convertSkillToString(s.getSkill())).collect(Collectors.toList());
    }

    private String convertSkillToString(List<Skill> val){
        List<String> list = val.stream().map(s-> s.getId() + "").collect(Collectors.toList());
        return list.stream().reduce((s1,s2) -> s1 + "," + s2).orElse(null);
    }

    private List<Developer> getAllInternal() throws Exception {
        List<String> list = IOSystem.read(FILE_NAME);
        return convertToData(list);
    }
}
