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
public class PrendaControlador {

	@Autowired
	private PrendaServicio servicio;
	@Autowired
	private CategoriaServicio categoriaServicio;

	@GetMapping({ "/prendas" })
	public String listarCategorias(Model model) {

		model.addAttribute("prendas", servicio.listarTodasLasPrendas());
		return "Prendas/Prenda"; // Este es el nombre de la plantilla Thymeleaf
	}

	@GetMapping("/prendas/nueva_prenda")
	public String mostrarFormularioDeRegistrarPrendas(Model modelo) {
		System.out.println("Soy un Test");
		Prenda prenda = new Prenda();
		List<Categoria> categorias = categoriaServicio.listarTodasLasCategorias(); // Obtener la lista de categorías
		modelo.addAttribute("categorias", categorias);
		modelo.addAttribute("prenda", prenda);
		return "Prendas/Crear_Prenda";

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

	@PostMapping("/prendas")
	public String guardarPrenda(@RequestParam("id") int id, @RequestParam("nombre") String nombre,
			@RequestParam("imagen") MultipartFile imagen, @RequestParam("id_categoria") int idCategoria)
			throws IOException {


		// Convierte la imagen a un array de bytes
		byte[] imagenBytes = imagen.getBytes();

		Categoria categoria = categoriaServicio.obtenerCategoriaPorId(idCategoria);

		// Crea una instancia de Categoria y asigna los valores
		Prenda prenda = new Prenda(id, nombre, imagenBytes, categoria, true);
		
		servicio.guardarPrenda(prenda);
		return "redirect:/prendas";

	}

	@GetMapping("/prendas/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable int id, Model modelo) {
		System.out.println("TEST");
		modelo.addAttribute("prenda", servicio.obtenerPrendaPorId(id));
		return "Prendas/Editar_Prenda";
	}

	@PostMapping("/prendas/{id}")
	public String actualizarPrenda(@PathVariable int id, @RequestParam("nombre") String nombre,
			@RequestParam("imagen") MultipartFile imagen) throws IOException {
		// Validar si la categoría con el ID proporcionado existe
		System.out.println("TEST2");
		Prenda prendaExistente = servicio.obtenerPrendaPorId(id);

		// Actualizar los campos de la categoría existente
		prendaExistente.setNombre(nombre);
		prendaExistente.setImagen(imagen.getBytes());
		

		servicio.actualizarPrenda(prendaExistente);

		return "redirect:/prendas";
	}

	@GetMapping("/prendas/{id}")
	public String ocultarPrenda(@PathVariable int id) {
		servicio.ocultarPrenda(id);
		return "redirect:/prendas";
	}
	
	@GetMapping("/categorias/prendas/{categoriaId}")
	public String mostrarPrendasDeCategoria(@PathVariable int categoriaId, Model model) {
	    // Lógica para obtener las prendas de la categoría utilizando el ID
	    Categoria categoria = categoriaServicio.obtenerCategoriaPorId(categoriaId);
	    List<Prenda> prendas = servicio.obtenerPrendasPorCategoria(categoria);

	    model.addAttribute("categoria", categoria);
	    model.addAttribute("prendas", prendas);

	    return "Prendas/Prenda"; // Nombre de la vista para mostrar las prendas
	}
	

}