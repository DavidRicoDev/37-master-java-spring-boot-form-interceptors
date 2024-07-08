package es.davidrico.springboot.forminterceptors.services;

import java.util.ArrayList;
import java.util.List;

import es.davidrico.springboot.forminterceptors.models.domain.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
	
	private List<Role> roles;
	
	public RoleServiceImpl() {
		this.roles = new ArrayList<>();
		this.roles.add(new Role(1, "Administrador", "ROLE_ADMIN"));
		this.roles.add(new Role(2, "Usuario", "ROLE_USER"));
		this.roles.add(new Role(3, "Moderador", "ROLE_MODERATOR"));
	}

	@Override
	public List<Role> listar(){
		
		return roles;
	}
	
	@Override
	public Role obtenerPorId(Integer id) {
		
	Role resultado = null;
		
		for(Role role: roles) {
			if(id == role.getId()) {
				resultado = role;
				break;
			}
		}
		
		return resultado;
		
	}
}