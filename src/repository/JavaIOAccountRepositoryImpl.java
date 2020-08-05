package repository;

import model.Account;
import model.AccountStatus;
import model.Skill;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JavaIOAccountRepositoryImpl implements AccountRepository{
    private final static String FILE_NAME = "account.txt";

    @Override
    public Account save(Account val) throws Exception {
        List<Account> list = getAllInternal();
        val.setId(list.stream().mapToLong(Account::getId).max().getAsLong() + 1);
        list.add(val);
        IOSystem.write(FILE_NAME, convertToString(list));
        return val;
    }

    @Override
    public void deleteById(Long id) throws Exception {

    }

    @Override
    public Account getByID(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Account> getAll() throws Exception {
        return null;
    }

    @Override
    public Account update(Account val) throws Exception {
        return null;
    }

    private List<Account> convertToData(List<String> val) {
        return  val.stream().map(s-> s.split(",")).map(s->{
            Account account = new Account();
            account.setId(Long.parseLong(s[0]));
            account.setName(s[1]);
            account.setAccountStatus(AccountStatus.valueOf(s[2]));
            return account;
        }).collect(Collectors.toList());
    }

    private List<String> convertToString(List<Account> val) {
        return val.stream().map(s-> s.getId() + "," + s.getName() + "," +
                s.getAccountStatus()).collect(Collectors.toList());
    }
    private List<Account> getAllInternal() throws Exception {
        List<String> list = IOSystem.read(FILE_NAME);
        return convertToData(list);
    }
}
