import model.Skill;
import repository.GenericRepository;
import repository.JavaIOSkillRepositoryImpl;
import view.ViewRepository;

import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {

        ViewRepository vR = new ViewRepository();
        vR.run();

    }
}
