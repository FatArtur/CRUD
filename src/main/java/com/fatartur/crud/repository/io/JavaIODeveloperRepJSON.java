package main.java.com.fatartur.crud.repository.io;

import com.google.gson.Gson;
import main.java.com.fatartur.crud.controller.AccountController;
import main.java.com.fatartur.crud.controller.SkillController;
import main.java.com.fatartur.crud.model.Account;
import main.java.com.fatartur.crud.model.Developer;
import main.java.com.fatartur.crud.model.Skill;
import main.java.com.fatartur.crud.repository.DeveloperRepository;
import main.java.com.fatartur.crud.repository.io.IOSystem;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaIODeveloperRepJSON implements DeveloperRepository {
    private final static String FILE_NAME = "developer.json";

    @Override
    public Developer save(Developer val) throws Exception {
        List<Developer> list = getAll();
        val.setId(nextNum(list));
        list.add(val);
        IOSystem.write(FILE_NAME, convertToString(list));
        return val;
    }

    private Long nextNum(List<Developer> list){
        try {
            return list.stream().mapToLong(Developer::getId).max().getAsLong() + 1;
        }
        catch (Exception e){
            return (long)1;
        }
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
        return val.stream().map(s -> {
            Developer developer = new Gson().fromJson(s, Developer.class);
            return developer;
        }).collect(Collectors.toList());
    }

    private List<String> convertToString(List<Developer> val) {
        return val.stream().map(s -> {String json = new Gson().toJson(s); return json;}).collect(Collectors.toList());
    }

    private List<Developer> getAllInternal() throws Exception {
        List<String> list = IOSystem.read(FILE_NAME);
        return convertToData(list);
    }
}
