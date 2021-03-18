package com.example.productos.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.productos.Entity.Presentacion;
import org.springframework.data.domain.Sort;
import java.util.List;
import org.springframework.data.jpa.repository.Query;


public interface IPresentacionDao extends JpaRepository<Presentacion,Long>{

    @Query(value = "SELECT p FROM Presentacion p")
    public List<Presentacion> findAll(Sort sort);

    // @Query(value = "FROM Presentacion where id=:id")
    @Query(value = "SELECT p FROM Presentacion p where id = :id")
    public Presentacion findById(long id);

    
}
