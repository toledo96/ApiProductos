package com.example.productos.Service;

import java.util.List;

import com.example.productos.Dao.IPresentacionDao;
import com.example.productos.Dao.IProductoDao;
import com.example.productos.Entity.Presentacion;
import com.example.productos.Entity.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoDao productoDao;

    @Autowired
    private IPresentacionDao presentacionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return productoDao.findAll(sort);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Producto> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return productoDao.findAll(pageable);
    }

    @Override
    @Transactional
    public Producto findById(long id) {
        // TODO Auto-generated method stub
        return productoDao.findById(id);
    }

    @Override
    @Transactional
    public void delete(long id) {
        // TODO Auto-generated method stub
        productoDao.deleteById(id);
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        // TODO Auto-generated method stub
        Presentacion presentacion = presentacionDao.findById(producto.getPresentacion().getId());
        producto.setPresentacion(presentacion);
        return productoDao.save(producto);
    }
    
}
