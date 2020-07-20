package repository;

import java.io.IOException;
import java.util.*;

public interface GenericRepository <T,ID>{

    void save(T val) throws Exception;

    void deleteById(Long id) throws Exception;

    T getByID (Long id) throws Exception;

    List<T> getAll() throws Exception;

    void update(T val) throws Exception;

    List<T> convertToData(List<String> val);

    List<String> convertToString(List<T> val);


}
