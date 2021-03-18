package com.example.productos.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.productos.Entity.Presentacion;
import com.example.productos.Service.IPresentacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.data.domain.Sort;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE })
@RequestMapping(value = "/presentacion")
public class PresentacionController {

    @Autowired
    private IPresentacionService presentacionService;

    @GetMapping
    public ResponseEntity<List<Presentacion>> findAll() {

        Sort sortByName = Sort.by("id");
        List<Presentacion> presentacion = presentacionService.findAll(sortByName);
        if (presentacion.size() > 0) {
            return new ResponseEntity<List<Presentacion>>(presentacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Presentacion>>(HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Presentacion> findById(@PathVariable long id) {
        Presentacion presentacion = presentacionService.findById(id);
        ResponseEntity<Presentacion> responseEntity = null;

        if (presentacion != null) {
            responseEntity = new ResponseEntity<Presentacion>(presentacion, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<Presentacion>(HttpStatus.NO_CONTENT);
        }
        return responseEntity;

    }

    @PostMapping()
    public ResponseEntity<Map<String,Object>> insert(@Valid Presentacion presentacion,BeanPropertyBindingResult result) {
        
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
        Presentacion productoFromDB = presentacionService.save(presentacion);
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
        presentacionService.delete(id);
        responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.OK); 
        return responseEntity;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String,Object>> update(@PathVariable long id, @Valid Presentacion presentacion,BindingResult result) {
        
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

        presentacion.setId(id);
        Presentacion productoFromDB = presentacionService.save(presentacion);
        if(productoFromDB != null){
            responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.OK);               
        }else{
            responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.INTERNAL_SERVER_ERROR);
        }  
        
        return responseEntity;
    }


}
