package com.app.web.controlador;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.app.web.entidad.Categoria;
import com.app.web.entidad.Prenda;
import com.app.web.repositorio.CategoriaRepositorio;
import com.app.web.servicio.CategoriaServicio;
import com.app.web.servicio.PrendaServicio;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class CategoriaControlador {

	@Autowired
	private CategoriaServicio servicio;
	@Autowired
	private PrendaServicio prendaServicio;

	@GetMapping({ "/categorias"})
	public String listarCategorias(Model model) {


		model.addAttribute("categorias", servicio.listarTodasLasCategorias());
		return "Categorias/Categoria"; // Este es el nombre de la plantilla Thymeleaf
	}

	@GetMapping("/categorias/nueva_categoria")
	public String mostrarFormularioDeRegistrarCategorias(Model modelo) {

		Categoria categoria = new Categoria();
		modelo.addAttribute("categoria", categoria);
		return "Categorias/Crear_Categoria";

	}


	@PostMapping("/categorias")
	public String guardarCategoria(@RequestParam("nombre") String nombre,
			@RequestParam("imagen") MultipartFile imagen) throws IOException {

		int maxId = servicio.obtenerMaximoId();
		System.out.println(maxId);
		int id = maxId + 1;
		System.out.println(id);
		// Convierte la imagen a un array de bytes
		byte[] imagenBytes = imagen.getBytes();

		// Crea una instancia de Categoria y asigna los valores
		Categoria categoria = new Categoria(id, nombre, imagenBytes, true);

		
		servicio.guardarCategoria(categoria);
		return "redirect:categorias";

	}

	@GetMapping("/categorias/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable int id, Model modelo) {
		System.out.println("TEST");
		modelo.addAttribute("categoria", servicio.obtenerCategoriaPorId(id));
		return "Categorias/Editar_Categoria";
	}

	@PostMapping("/categorias/{id}")
	public String actualizarCategoria(@PathVariable int id, @RequestParam("nombre") String nombre,
			@RequestParam("imagen") MultipartFile imagen) throws IOException {
	    // Validar si la categoría con el ID proporcionado existe
		System.out.println("TEST2");
	    Categoria categoriaExistente = servicio.obtenerCategoriaPorId(id);
	    
	    
	    // Actualizar los campos de la categoría existente
	    categoriaExistente.setNombre(nombre);
	    categoriaExistente.setImagen(imagen.getBytes());
	    
	    
	    servicio.actualizarCategoria(categoriaExistente);

	    return "redirect:/categorias";
	}
	
	@GetMapping("/categorias/{id}")
	public String ocultarCategoria(@PathVariable int id) {
		
		Categoria categoria = servicio.obtenerCategoriaPorId(id);
		
		List<Prenda> prendas = prendaServicio.obtenerPrendasPorCategoria(categoria);
		
		for (Prenda prenda : prendas) {
	        prenda.setVisibilidad(false);
	        prendaServicio.guardarPrenda(prenda); // Puedes guardar la prenda para actualizar su visibilidad
	    }
		
		servicio.ocultarCategoria(id);
		return "redirect:/categorias";
	}
	
	
	
	

	
	


}