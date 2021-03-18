package com.example.productos.Service;



import org.springframework.data.domain.*;
import java.util.*;
import com.example.productos.Entity.Producto;

public interface IProductoService {

    public List<Producto> findAll(Sort sort);
    public Page<Producto> findAll(Pageable pageable);
    public Producto findById(long id);
    public void delete(long id);
    public Producto save(Producto producto);


    
}
