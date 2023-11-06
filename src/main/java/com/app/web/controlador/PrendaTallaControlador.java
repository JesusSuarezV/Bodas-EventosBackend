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
import com.app.web.entidad.PrendaTalla;
import com.app.web.entidad.Talla;
import com.app.web.entidad.compuesta.PrendaTallaId;
import com.app.web.repositorio.CategoriaRepositorio;
import com.app.web.servicio.CategoriaServicio;
import com.app.web.servicio.PrendaServicio;
import com.app.web.servicio.PrendaTallaServicio;
import com.app.web.servicio.TallaServicio;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class PrendaTallaControlador {

	@Autowired
	private PrendaTallaServicio servicio;
	@Autowired
	private PrendaServicio prendaServicio;
	@Autowired
	private TallaServicio tallaServicio;
	@Autowired
	private CategoriaServicio categoriaServicio;

	@GetMapping("/categoria/{categoriaId}/prenda/{prendaId}")
	public String mostrarTallaDePrendas(@PathVariable int prendaId, Model model) {

		Prenda prenda = prendaServicio.obtenerPrendaPorId(prendaId);
		List<PrendaTalla> prendaTallas = servicio.obtenerPrendaTallaPorPrenda(prenda);

		model.addAttribute("prenda", prenda);
		model.addAttribute("prendaTallas", prendaTallas);

		return "Prendas/PrendaTalla"; 
	}

	@GetMapping("/categoria/{categoriaId}/prenda/{prendaId}/talla/registrar")
	public String mostrarFormularioDeRegistrarTallaDePrendas(@PathVariable int categoriaId, @PathVariable int prendaId,
			Model modelo) {

		PrendaTalla prendaTalla = new PrendaTalla();
		List<Talla> tallas = tallaServicio.listarTodasLasTallas();
		Categoria categoria = categoriaServicio.obtenerCategoriaPorId(categoriaId);
		Prenda prenda = prendaServicio.obtenerPrendaPorId(prendaId);
		modelo.addAttribute("tallas", tallas);
		modelo.addAttribute("prendaTalla", prendaTalla);
		modelo.addAttribute("categoria", categoria);
		modelo.addAttribute("prenda", prenda);

		return "Prendas/Crear_PrendaTalla"; 
	}

	@GetMapping("/categoria/{categoriaId}/prenda/{prendaId}/talla/{tallaId}/editar")
	public String editarTallaDePrendas(@PathVariable int prendaId, @PathVariable int tallaId, Model model) {

		PrendaTalla prendaTalla = servicio.obtenerPrendaTallaPorId(new PrendaTallaId(
				prendaServicio.obtenerPrendaPorId(prendaId), tallaServicio.obtenerTallaPorId(tallaId)));
		model.addAttribute(prendaTalla);
		return "Prendas/Editar_PrendaTalla";
	}

	@PostMapping("/categoria/{categoriaId}/prenda/{prendaId}/talla/guardar")
	public String guardarPrendaDeTalla(@PathVariable int categoriaId, @PathVariable int prendaId,
			@RequestParam("id_talla") int tallaId, @RequestParam("precio") int precio, @RequestParam("cantidad") int cantidad) throws IOException {
		
		Prenda prenda = prendaServicio.obtenerPrendaPorId(prendaId);
		Talla talla = tallaServicio.obtenerTallaPorId(tallaId);
		PrendaTalla prendaTalla = new PrendaTalla(prenda, talla, precio, cantidad, true);

		servicio.crearPrendaTalla(prendaTalla);
		return "redirect:/categoria/{categoriaId}/prenda/{prendaId}";
	}

	@PostMapping("/categoria/{categoriaId}/prenda/{prendaId}/talla/{tallaId}/actualizar")
	public String actualizarPrendaDeTalla(@PathVariable int prendaId, @PathVariable int tallaId,
			@RequestParam("precio") int precio, @RequestParam("cantidad") int cantidad) throws IOException {
		Prenda prenda = prendaServicio.obtenerPrendaPorId(prendaId);
		Talla talla = tallaServicio.obtenerTallaPorId(tallaId);
		PrendaTallaId prendaTallaId = new PrendaTallaId(prenda, talla);
		PrendaTalla prendaTallaExistente = servicio.obtenerPrendaTallaPorId(prendaTallaId);

	
		prendaTallaExistente.setCantidad(cantidad);
		prendaTallaExistente.setPrecio(precio);
		servicio.actualizarPrendaTalla(prendaTallaExistente);
		return "redirect:/categoria/{categoriaId}/prenda/{prendaId}";
	}

	@GetMapping("/categoria/{categoriaId}/prenda/{prendaId}/talla/{tallaId}/ocultar")
	public String ocultarPrendaDeTalla(@PathVariable int prendaId, @PathVariable int tallaId) throws IOException {
		Prenda prenda = prendaServicio.obtenerPrendaPorId(prendaId);
		Talla talla = tallaServicio.obtenerTallaPorId(tallaId);
		PrendaTallaId prendaTallaId = new PrendaTallaId(prenda, talla);
		PrendaTalla prendaTalla = servicio.obtenerPrendaTallaPorId(prendaTallaId);

		servicio.ocultarPrendaTalla(prendaTalla);
		return "redirect:/categoria/{categoriaId}/prenda/{prendaId}";
	}
}