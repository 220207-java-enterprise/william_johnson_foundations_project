package com.revature.erm.daos;

public interface CrudDAO<T> {
    void save(T newObject);
    T getById(String id);
    T[] getAll(); // TODO return a dynamically sizeable
    void update(T updatedObject);
    void deleteById(String id);

    static void staticInterfaceMethod() {

    }

    default void defaultInterfaceMethod() {

    }
}
