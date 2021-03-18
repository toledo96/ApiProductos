package com.example.productos.Dao;


import java.util.List;

import com.example.productos.Entity.Producto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IProductoDao extends JpaRepository<Producto,Long>{

    @Query(value = "select p from Producto p left join fetch p.presentacion")
    public List<Producto> findAll(Sort sort);
    //paginación
    // @Query(value = "select p from Producto p left join fetch p.presentacion",
    // countQuery = "select count(p) from Producto p left join p.presentacion")
    // public Page<Producto> findAll(Pageable pageable);

    @Query(value = "select p from Producto p left join fetch p.presentacion where p.id = :id")
    public Producto findById(long id);

}
