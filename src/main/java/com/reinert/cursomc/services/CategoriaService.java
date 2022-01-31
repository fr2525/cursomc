package com.reinert.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.reinert.cursomc.domain.Categoria;
import com.reinert.cursomc.repositories.CategoriaRepository;
import com.reinert.cursomc.services.exceptions.DataIntegrityException;
import com.reinert.cursomc.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

import javax.naming.ldap.PagedResultsControl;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		 Optional<Categoria> obj = repo.findById(id);
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName(), null));
		} 
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	public void delete(Integer id) {
		find(id);
		try {
		   repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir categoria que possue produtos");
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll(); 
	}
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction ) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
}

