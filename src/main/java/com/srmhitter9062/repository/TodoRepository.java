package com.srmhitter9062.repository;

import com.srmhitter9062.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {

    Todo findById(int id);

}
