package com.app.web.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.web.dto.UsuarioRegistroDTO;
import com.app.web.servicio.PrendaServicio;
import com.app.web.servicio.UsuarioServicio;

@Controller

public class RegistroUsuarioControlador {
	@Autowired
	private UsuarioServicio servicio;

	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}
	
	@GetMapping("/registro")
	public String mostrarFormularioDeRegistro(){
		
		return "/Login/Registro";
	}
	
	@PostMapping("/registro/guardar")
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
		servicio.guardarUsuario(registroDTO);
		return "redirect:/registro?exito";
		
	}
}
