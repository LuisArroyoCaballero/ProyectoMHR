package org.iesalixar.controller;

import java.util.List;
import java.util.Optional;

import org.iesalixar.model.Monstruo;
import org.iesalixar.model.Usuario;
import org.iesalixar.services.RegistroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	RegistroServiceImpl userService;

	
	@GetMapping("")
	public String usuario(@RequestParam(required = false, name = "error") String error, Model model) {

		
		List<Usuario> usuarios = userService.getAllByRol();
		model.addAttribute("usuarios", usuarios);
		
		
		if (error != null) {
			model.addAttribute("error", "Error al visualizar las armaduras");
		}

		return "usuarios";

	}
	
	@GetMapping("/removeUsuario")
	public String removeUsuario(@RequestParam(required = true, name = "codigo") String codigo, @RequestParam(required = false, name = "error") String error, Model model) {

		if (codigo == null) {
			model.addAttribute("errorUsuario", "Usuario no seleccionado");
			return "redirect:/usuarios?error=notselected";
		}

		//Optional<Usuario> usuario = userService.findUsuarioById(Long.parseLong(codigo));
		
		userService.deleteUsuarioById(Long.parseLong(codigo));
		
		return "redirect:/usuarios";
		
		

	}
		
	

}
