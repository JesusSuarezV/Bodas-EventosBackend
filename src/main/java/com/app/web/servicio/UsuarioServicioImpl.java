package com.app.web.servicio;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.web.dto.UsuarioRegistroDTO;
import com.app.web.entidad.Rol;
import com.app.web.entidad.Usuario;
import com.app.web.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
	@Autowired
	private UsuarioRepositorio repositorio;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repositorio.findByCorreo(username);
		if(usuario == null || !usuario.isVisibilidad()) {
			throw new UsernameNotFoundException("Usuario o Contraseña invalidos");
		}
		
		if (!usuario.isVisibilidad()) {
	        throw new UsernameNotFoundException("User is not visible");
	    }
		return new User(usuario.getCorreo(), usuario.getContraseña(), mapearAutoridadesRoles(usuario.getRol()));
	}

	@Override
	public Usuario guardarUsuario(UsuarioRegistroDTO registroDTO) {

		Usuario usuario = new Usuario(registroDTO.getCedula(), registroDTO.getNombre(), registroDTO.getApellido(),
				registroDTO.getCorreo(), passwordEncoder.encode(registroDTO.getContraseña()), true, Arrays.asList(new Rol(1, "AUX")));

		return repositorio.save(usuario);

	}
	
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return repositorio.findAll();
	}

}
