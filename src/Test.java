import model.Skill;
import repository.GenericRepository;
import repository.JavaIOSkillRepositoryImpl;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {

        GenericRepository gr = new JavaIOSkillRepositoryImpl();
//        List<Skill> list = gr.getAll();
//        for (Skill e: list) {
//            System.out.println(e.getId() + ": " + e.getSkill());
//        }

        Skill kr = (Skill) gr.getByID(new Long(2));
        System.out.println(kr.getId() + "- " + kr.getSkill());
    }
}
