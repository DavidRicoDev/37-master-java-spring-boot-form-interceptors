package es.davidrico.springboot.forminterceptors.services;

import java.util.List;

import es.davidrico.springboot.forminterceptors.models.domain.Pais;

public interface PaisService {
	
	public List<Pais> listar();
	
	public Pais obtenerPorId(Integer id);

}
