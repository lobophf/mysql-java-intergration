package com.thebe.db;

import java.util.Optional;
import java.util.List;

public interface Dao<T> {
	void save(T t);
	Optional<T> findById(int id);
	void update(T t);
	void delete(T t);
	List<T> getAll();
	
}