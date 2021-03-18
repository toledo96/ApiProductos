package com.example.productos.Service;

import java.util.List;

import com.example.productos.Dao.IPresentacionDao;
import com.example.productos.Entity.Presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PresentacionServiceImp implements IPresentacionService {

    @Autowired
    private IPresentacionDao presentacionDao;

    @Override
    public List<Presentacion> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return  presentacionDao.findAll(sort);
    }

    @Override
    public Presentacion findById(long id) {
        // TODO Auto-generated method stub
        return presentacionDao.findById(id);
    }

    @Override
    public void delete(long id) {
        // TODO Auto-generated method stub
        presentacionDao.deleteById(id);
    }

    @Override
    public Presentacion save(Presentacion producto) {
        // TODO Auto-generated method stub
        return presentacionDao.save(producto);
    }
    
}
