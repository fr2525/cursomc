package com.reinert.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinert.cursomc.domain.Cliente;
import com.reinert.cursomc.repositories.ClienteRepository;
import com.reinert.cursomc.services.exceptions.ObjectNotFoundException;

import java.util.Optional;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		 Optional<Cliente> obj = repo.findById(id);
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName(), null));
		} 		 
	}

