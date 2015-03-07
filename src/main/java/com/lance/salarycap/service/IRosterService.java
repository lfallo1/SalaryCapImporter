package com.lance.salarycap.service;

import java.util.List;

public interface IRosterService<T> {
	List<T> getAll();
	T getById(Integer id);
	Integer add(T obj);
}
