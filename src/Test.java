import model.Skill;
import repository.GenericRepository;
import repository.JavaIOSkillRepositoryImpl;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {

        GenericRepository gr = new JavaIOSkillRepositoryImpl();
        List<Skill> list = gr.getAll();
        for (Skill e: list) {
            System.out.println(e.getId() + ": " + e.getName());
        }

        Skill kr = (Skill) gr.getByID(new Long(4));
        System.out.println("+++++++++");
        System.out.println(kr.getId() + "- " + kr.getName());

        Skill newSkill = new Skill();
        newSkill.setName("Python");
//        newSkill.setId((long)5);
        gr.deleteById((long)4);
        gr.save(newSkill);
//        gr.update(newSkill);


    }
}
