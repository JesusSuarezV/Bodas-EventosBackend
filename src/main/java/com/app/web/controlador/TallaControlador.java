package com.app.web.controlador;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.app.web.entidad.Prenda;
import com.app.web.entidad.Talla;
import com.app.web.repositorio.CategoriaRepositorio;
import com.app.web.servicio.CategoriaServicio;
import com.app.web.servicio.PrendaServicio;
import com.app.web.servicio.TallaServicio;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class TallaControlador {

	@Autowired
	private TallaServicio servicio;

	@GetMapping({ "/tallas" })
	public String listarTallass(Model model) {

		model.addAttribute("tallas", servicio.listarTodasLasTallas());
		return "Tallas/Talla";
	}

	@GetMapping("/tallas/nueva_talla")
	public String mostrarFormularioDeRegistrarTallas(Model modelo) {
		Talla talla = new Talla();
		modelo.addAttribute("talla", talla);
		return "Tallas/Crear_Talla";

	}

	@PostMapping("/tallas")
	public String guardarTalla(@RequestParam("medida") String medida) throws IOException {

		int id = servicio.obtenerMaximoId() + 1;
		Talla talla = new Talla(id, medida, true);
		servicio.guardarTalla(talla);
		return "redirect:/tallas";

	}

	@GetMapping("/tallas/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable int id, Model modelo) {
		modelo.addAttribute("talla", servicio.obtenerTallaPorId(id));
		return "Tallas/Editar_Talla";
	}

	@PostMapping("/tallas/{id}")
	public String actualizarTalla(@PathVariable int id, @RequestParam("medida") String medida) throws IOException {
		System.out.println("TEST2");
		Talla tallaExistente = servicio.obtenerTallaPorId(id);
		tallaExistente.setMedida(medida);
		servicio.actualizarTalla(tallaExistente);

		return "redirect:/tallas";
	}

	@GetMapping("/tallas/{id}")
	public String ocultarTalla(@PathVariable int id) {
		servicio.ocultarTalla(id);
		return "redirect:/tallas";
	}

}