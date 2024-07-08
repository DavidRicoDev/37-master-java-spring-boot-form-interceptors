package es.davidrico.springboot.forminterceptors.services;

import java.util.List;

import es.davidrico.springboot.forminterceptors.models.domain.Role;

public interface RoleService {
	
	public List<Role> listar();
	
	public Role obtenerPorId(Integer id);

}

