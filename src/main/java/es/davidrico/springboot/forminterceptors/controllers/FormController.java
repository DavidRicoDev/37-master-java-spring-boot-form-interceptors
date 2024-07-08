package es.davidrico.springboot.forminterceptors.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.davidrico.springboot.forminterceptors.editors.PaisPropertyEditor;
import es.davidrico.springboot.forminterceptors.editors.RolesEditor;
import es.davidrico.springboot.forminterceptors.models.domain.Pais;
import es.davidrico.springboot.forminterceptors.models.domain.Role;
import es.davidrico.springboot.forminterceptors.models.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.davidrico.springboot.forminterceptors.editors.NombreMayusculaEditor;
import es.davidrico.springboot.forminterceptors.services.PaisService;
import es.davidrico.springboot.forminterceptors.services.RoleService;
import es.davidrico.springboot.forminterceptors.validation.UsuarioValidador;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("usuario")
public class FormController {
	
	@Autowired
	private UsuarioValidador validador;
	
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PaisPropertyEditor paisEditor;
	
	@Autowired
	private RolesEditor roleEditor;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, true));
		
		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());
		
		binder.registerCustomEditor(Pais.class, "pais", paisEditor);
		binder.registerCustomEditor(Role.class, "roles", roleEditor);
	}
	
	@ModelAttribute("genero")
	public List<String> genero(){
		
		return Arrays.asList("Hombre", "Mujer");
	}
	
	@ModelAttribute("LsitaRoles")
	public List<Role> ListaRoles(){
		return this.roleService.listar();
	}
	
	
	@ModelAttribute("Paises")
	public List<String> Paises(){
		return Arrays.asList("España", "Francia", "Italia", "Portugal", "Inglaterra", "Alemania", "Croacia");
	}
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises(){
		return paisService.listar();
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString(){
		
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		
		return roles;
	}
	
	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap(){
		Map<String, String> roles = new HashMap<String, String>();
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR","Moderador");
		
		return roles;
	}
	
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap(){
		Map<String, String> paises = new HashMap<String, String>();
		paises.put("ES", "España");
		paises.put("FR", "Francia");
		paises.put("IT", "Italia");
		paises.put("PO", "Portugal");
		paises.put("IN", "Inglaterra");
		paises.put("AL", "Alemania");
		paises.put("CR", "Croacia");
		
		return paises;
	}
	
	
	@GetMapping("/form")
	public String form(Model model) {
		
	Usuario usuario = new Usuario();
	usuario.setNombre("Chani");
	usuario.setApellido("Vázquez");
	usuario.setIdentificador("123.456.789-K");
	usuario.setHabilitar(true);
	usuario.setValorSecreto("Algún valor secreto ****");
	usuario.setPais(new Pais(1, "ES", "España"));
	usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER")));
	
	model.addAttribute("titulo", "Formulario usuarios");
	model.addAttribute("usuario", usuario);
		
	return "form";
	}
	
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {

	if(result.hasErrors()) {
		
		model.addAttribute("titulo", "Resultado Form");
		return "form";
	}
	
	return "redirect:/ver";
	}
	
	@GetMapping("/ver")
	public String ver(@SessionAttribute(name="usuario", required = false) Usuario usuario, Model model, SessionStatus status) {
		
		if(usuario == null) {
			
			return "redirect:/form";
		}
		
		model.addAttribute("titulo", "Resultado Form");
		
		status.setComplete();
		return "resultado";
	}
}
