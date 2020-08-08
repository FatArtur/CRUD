import model.Developer;
import repository.DeveloperRepository;
import repository.io.JavaIODeveloperRepositoryImpl;
import view.AccountView;
import view.SkillView;

import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {

//        SkillView vR = new SkillView();
//        vR.run();
//        AccountView aV = new AccountView();
//        aV.run();
        DeveloperRepository dV = new JavaIODeveloperRepositoryImpl();
        List<Developer> list = dV.getAll();
        list.forEach(s-> System.out.println("ID: " + s.getId() + " Account: " + s.getAccount() + " SKILL: " +
                s.getSkill()));

    }
}
