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


	@GetMapping("/prenda/nueva_prenda")
	public String mostrarFormularioDeRegistrarPrendas(Model modelo) {
		System.out.println("Soy un Test");
		Prenda prenda = new Prenda();
		List<Categoria> categorias = categoriaServicio.listarTodasLasCategorias(); 
		modelo.addAttribute("categorias", categorias);
		modelo.addAttribute("prenda", prenda);
		return "Prendas/Crear_Prenda";

	}

	@PostMapping("/prenda/guardar")
	public String guardarPrenda(@RequestParam("id") int id, @RequestParam("nombre") String nombre,
			@RequestParam("imagen") MultipartFile imagen, @RequestParam("id_categoria") int idCategoria)
			throws IOException {


		byte[] imagenBytes = imagen.getBytes();

		Categoria categoria = categoriaServicio.obtenerCategoriaPorId(idCategoria);

		id = servicio.obtenerMaximoId() + 1;
		Prenda prenda = new Prenda(id, nombre, imagenBytes, categoria, true);
		servicio.guardarPrenda(prenda);
		return "redirect:/categoria/" + idCategoria + "/prenda";

	}

	@GetMapping("/prenda/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable int id, Model modelo) {
		System.out.println("TEST");
		modelo.addAttribute("prenda", servicio.obtenerPrendaPorId(id));
		return "Prendas/Editar_Prenda";
	}

	@PostMapping("/prenda/{id}/actualizar")
	public String actualizarPrenda(@PathVariable int id, @RequestParam("nombre") String nombre,
			@RequestParam("imagen") MultipartFile imagen) throws IOException {
		System.out.println("TEST2");
		Prenda prendaExistente = servicio.obtenerPrendaPorId(id);

		prendaExistente.setNombre(nombre);
		prendaExistente.setImagen(imagen.getBytes());
		int idCategoria = prendaExistente.getCategoria().getId();

		servicio.actualizarPrenda(prendaExistente);

		return "redirect:/categoria/" + idCategoria + "/prenda";
	}

	@GetMapping("/prenda/eliminar/{id}")
	public String ocultarPrenda(@PathVariable int id) {
		servicio.ocultarPrenda(id);
		int idCategoria = servicio.obtenerPrendaPorId(id).getCategoria().getId();
		return "redirect:/categoria/" + idCategoria + "/prenda";
	}
	
	@GetMapping("/categoria/{categoriaId}/prenda")
	public String mostrarPrendasDeCategoria(@PathVariable int categoriaId, Model model) {
	    Categoria categoria = categoriaServicio.obtenerCategoriaPorId(categoriaId);
	    List<Prenda> prendas = servicio.obtenerPrendasPorCategoria(categoria);

	    model.addAttribute("categoria", categoria);
	    model.addAttribute("prendas", prendas);

	    return "Prendas/Prenda"; 
	}
	

}