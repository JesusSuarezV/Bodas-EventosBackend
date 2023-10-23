package com.app.web.controlador;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.app.web.entidad.Categoria;
import com.app.web.repositorio.CategoriaRepositorio;
import com.app.web.servicio.CategoriaServicio;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class CategoriaControlador {

	@Autowired
	private CategoriaServicio servicio;

	@GetMapping({ "/categorias", "/" })
	public String listarCategorias(Model model) {

		List<Categoria> categorias = servicio.listarTodasLasCategorias();

		model.addAttribute("categorias", servicio.listarTodasLasCategorias());
		return "Categoria"; // Este es el nombre de la plantilla Thymeleaf
	}

	@GetMapping("/categorias/nueva_categoria")
	public String mostrarFormularioDeRegistrarCategorias(Model modelo) {

		Categoria categoria = new Categoria();
		modelo.addAttribute("categoria", categoria);
		System.out.println("OlaKAse1");
		return "Crear_Categoria";

	}

	/*
	 * @PostMapping("/categorias") public String guardarCategoria(@ModelAttribute
	 * Categoria categoria, @RequestParam("imagen") MultipartFile imagen) { //
	 * Verificar si se ha cargado una imagen System.out.println("OlaKAse2"); if
	 * (!imagen.isEmpty()) { try { // Obtener los bytes de la imagen byte[] bytes =
	 * imagen.getBytes(); // Guardar los bytes en el atributo imagen de la entidad
	 * categoria.setImagen(bytes); } catch (IOException e) { // Manejar errores de
	 * lectura de la imagen } }
	 */

	@PostMapping("/categorias")
	public String guardarCategoria(@RequestParam("id") int id, @RequestParam("nombre") String nombre,
			@RequestParam("imagen") MultipartFile imagen) throws IOException {

		// Convierte la imagen a un array de bytes
		byte[] imagenBytes = imagen.getBytes();

		// Crea una instancia de Categoria y asigna los valores
		Categoria categoria = new Categoria(id, nombre, imagenBytes);

		
		servicio.guardarCategoria(categoria);
		return "redirect:/categorias";

	}

	@GetMapping("/categorias/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable int id, Model modelo) {
		System.out.println("TEST");
		modelo.addAttribute("categoria", servicio.obtenerCategoriaPorId(id));
		return "Editar_Categoria";
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
	public String eliminarCategoria(@PathVariable int id) {
		servicio.eliminarCategoria(id);
		return "redirect:/categorias";
	}


}