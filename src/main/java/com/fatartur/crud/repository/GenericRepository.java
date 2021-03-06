package main.java.com.fatartur.crud.repository;

import java.util.*;

public interface GenericRepository <T,ID>{

    T save(T val) throws Exception;

    void deleteById(Long id) throws Exception;

    T getByID (Long id) throws Exception;

    List<T> getAll() throws Exception;

    T update(T val) throws Exception;
}
