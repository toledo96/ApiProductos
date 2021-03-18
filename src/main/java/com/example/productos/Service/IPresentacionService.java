package com.example.productos.Service;

import org.springframework.data.domain.*;
import java.util.*;
import com.example.productos.Entity.Presentacion;

public interface IPresentacionService {

    public List<Presentacion> findAll(Sort sort);
    public Presentacion findById(long id);
    public void delete(long id);
    public Presentacion save(Presentacion presentacion);
    
}
