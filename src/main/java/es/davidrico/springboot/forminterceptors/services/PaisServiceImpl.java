package es.davidrico.springboot.forminterceptors.services;

import java.util.Arrays;
import java.util.List;

import es.davidrico.springboot.forminterceptors.models.domain.Pais;
import org.springframework.stereotype.Service;


@Service
public class PaisServiceImpl implements PaisService {
	
	private List<Pais> lista;

	public PaisServiceImpl() {
		this.lista = Arrays.asList(
				new Pais(1, "ES", "España"),
				new Pais(2, "FR", "Francia"),
				new Pais(3, "IT", "Italia"), 
				new Pais(4, "PO", "Portugal"), 
				new Pais(5, "IN", "Inglaterra"),
				new Pais(6, "AL", "Alemania"),
				new Pais(7, "CR", "Croacia"));
	}

	@Override
	public List<Pais> listar() {

		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		
		Pais resultado = null;
		
		for(Pais pais: this.lista) {
			
			if(id == pais.getId()) {
				resultado = pais;
				break;
			}
		}
		
		return resultado;
	}

}
