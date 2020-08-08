package repository.io;

import model.Account;
import model.AccountStatus;
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
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {

    }

    @Override
    public Developer getByID(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Developer> getAll() throws Exception {
        return getAllInternal();
    }

    @Override
    public Developer update(Developer val) throws Exception {
        return null;
    }

    private List<Developer> convertToData(List<String> val) {
        return  val.stream().map(s-> s.split("/")).map(s->{
            Developer developer = new Developer();
            developer.setId(Long.parseLong(s[0]));
            developer.setAccount(convertToAccount(s[1]));
            developer.setSkill(convertToSkill(s[2]));
            return developer;
        }).collect(Collectors.toList());
    }

    private Account convertToAccount(String val){
        String[] s = val.split(",");
        Account account = new Account();
        account.setName(s[0]);
        account.setAccountStatus(AccountStatus.valueOf(s[1]));
        return account;
    }

    private List<Skill> convertToSkill(String val) {
        String[] txt = val.split(";");
        return Arrays.stream(txt).map(s-> s.split(",")).map(s->{
            Skill skill = new Skill();
            skill.setId(Long.parseLong(s[0]));
            skill.setName(s[1]);
            return skill;
        }).collect(Collectors.toList());
    }

    private List<String> convertToString(List<Developer> val) {
        return val.stream().map(s-> s.getId() + "/" + convertAccountToString(s.getAccount()) + "/" +
                convertSkillToString(s.getSkill())).collect(Collectors.toList());
    }

    private String convertAccountToString(Account val){
        return val.getName() + "," + val.getAccountStatus();
    }

    private String convertSkillToString(List<Skill> val){
        List<String> list = val.stream().map(s-> s.getId() + "," + s.getName()).collect(Collectors.toList());
        return list.stream().reduce((s1,s2) -> s1 + ";" + s2).orElse(null);
    }

    private List<Developer> getAllInternal() throws Exception {
        List<String> list = IOSystem.read(FILE_NAME);
        return convertToData(list);
    }
}
