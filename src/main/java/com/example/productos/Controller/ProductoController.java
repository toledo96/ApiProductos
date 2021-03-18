package com.example.productos.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.productos.Entity.Producto;
import com.example.productos.Service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
// @RequestMapping(value = "/productos", headers="Accept=application/json", consumes = { "multipart/form-data" })
@RequestMapping(value = "/productos")
public class ProductoController {
    // localhost:8080/productos

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> findAll(@RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        Sort sortByName = Sort.by("id");
        List<Producto> productos = productoService.findAll(sortByName);
        if (productos.size() > 0) {
            return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Producto>>(HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Producto> findById(@PathVariable long id) {
        Producto producto = productoService.findById(id);
        ResponseEntity<Producto> responseEntity = null;

        if (producto != null) {
            responseEntity = new ResponseEntity<Producto>(producto, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<Producto>(HttpStatus.NO_CONTENT);
        }
        return responseEntity;

    }

    @PostMapping()
    public ResponseEntity<Map<String,Object>> insert(@Valid Producto producto,BindingResult result) {
        
        Map<String,Object> responseAsMap = new HashMap<String,Object>();
        ResponseEntity<Map<String,Object>> responseEntity = null;
        List<String> errores = null;
        if(result.hasErrors()){
            errores = new ArrayList<String>();
            for(ObjectError error : result.getAllErrors()){
                errores.add(error.getDefaultMessage());
            }
            responseAsMap.put("errores", errores);
            responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

    
        Producto productoFromDB = productoService.save(producto);
        if(productoFromDB != null){
            responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.OK);               
        }else{
            responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.INTERNAL_SERVER_ERROR);
        }  
        
        return responseEntity;
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String,Object>> update(@PathVariable long id, @Valid  Producto producto,BindingResult result) {
        
        Map<String,Object> responseAsMap = new HashMap<String,Object>();
        ResponseEntity<Map<String,Object>> responseEntity = null;
        List<String> errores = null;
        if(result.hasErrors()){
            errores = new ArrayList<String>();
            for(ObjectError error : result.getAllErrors()){
                errores.add(error.getDefaultMessage());
            }
            responseAsMap.put("errores", errores);
            responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        producto.setId(id);
        Producto productoFromDB = productoService.save(producto);
        if(productoFromDB != null){
            responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.OK);               
        }else{
            responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.INTERNAL_SERVER_ERROR);
        }  
        
        return responseEntity;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable long id){
        Map<String,Object> responseAsMap = new HashMap<String,Object>();
        ResponseEntity<Map<String,Object>> responseEntity = null;
        productoService.delete(id);
        responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.OK); 
        return responseEntity;
    }



}
