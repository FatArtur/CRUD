package repository.io;

import model.Account;
import model.AccountStatus;
import repository.AccountRepository;
import java.util.List;
import java.util.stream.Collectors;

public class JavaIOAccountRepositoryImpl implements AccountRepository {
    private final static String FILE_NAME = "account.txt";

    @Override
    public Account save(Account val) throws Exception {
        List<Account> list = getAllInternal();
        val.setId(nextNum(list));
        val.setAccountStatus(AccountStatus.ACTIVE);
        list.add(val);
        IOSystem.write(FILE_NAME, convertToString(list));
        return val;
    }

    private Long nextNum(List<Account> list) {
        return list.stream().mapToLong(Account::getId).max().getAsLong() + 1;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        List<Account> list = getAllInternal();
        if (!list.removeIf(s -> s.getId().equals(id))) {
            throw new Exception("Отсутсвует данный ID");
        }
        IOSystem.write(FILE_NAME, convertToString(list));

    }

    @Override
    public Account getByID(Long id) throws Exception {
        List<Account> account = getAllInternal();
        return account.stream().filter(s -> s.getId().equals(id)).findFirst().
                orElse(null);
    }

    @Override
    public List<Account> getAll() throws Exception {
        return getAllInternal();
    }

    @Override
    public Account update(Account val) throws Exception {
        List<Account> account = getAllInternal();
        account.forEach(s -> {
            if (s.getId().equals(val.getId())) {
                s.setName(val.getName());
                s.setAccountStatus(val.getAccountStatus());
            }
        });
        IOSystem.write(FILE_NAME, convertToString(account));
        return val;
    }

    private List<Account> convertToData(List<String> val) {
        return val.stream().map(s -> s.split(",")).map(s -> {
            Account account = new Account();
            account.setId(Long.parseLong(s[0]));
            account.setName(s[1]);
            account.setAccountStatus(AccountStatus.valueOf(s[2]));
            return account;
        }).collect(Collectors.toList());
    }

    private List<String> convertToString(List<Account> val) {
        return val.stream().map(s -> s.getId() + "," + s.getName() + "," +
                s.getAccountStatus()).collect(Collectors.toList());
    }

    private List<Account> getAllInternal() throws Exception {
        List<String> list = IOSystem.read(FILE_NAME);
        return convertToData(list);
    }
}
