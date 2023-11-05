package com.app.web.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.entidad.Categoria;
import com.app.web.entidad.Prenda;
import com.app.web.repositorio.CategoriaRepositorio;
import com.app.web.repositorio.PrendaRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class PrendaServicio {

	@Autowired
    private PrendaRepositorio repositorio;

    

    public List<Prenda> listarTodasLasPrendas() {
    	
    	return repositorio.findByVisibilidadTrue();
        
        
        
    }
    
    public Prenda guardarPrenda(Prenda prenda) {
    	return repositorio.save(prenda);
    	
    }
    

    public Prenda obtenerPrendaPorId(int id) {
    	return repositorio.findById(id).get();
		
	}


    public Prenda actualizarPrenda(Prenda prenda) {
        return repositorio.save(prenda);
    }

    public void ocultarPrenda(int id) {
    	repositorio.softDelete(id);
    }
    
    public int obtenerMaximoId() {
    	
    	return repositorio.obtenerMaximoId();
    	
    }
    
    public List<Prenda> obtenerPrendasPorCategoria(Categoria categoria) {
    	return repositorio.findByCategoriaAndVisibilidadIsTrue(categoria);
		
	}
    
    
    
}

