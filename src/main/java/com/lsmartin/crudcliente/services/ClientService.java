package com.lsmartin.crudcliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsmartin.crudcliente.dto.ClientDTO;
import com.lsmartin.crudcliente.entities.Client;
import com.lsmartin.crudcliente.entities.ClientRepository;
import com.lsmartin.crudcliente.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public  ClientDTO findById(Long id) {
		
		Client client = clientRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado") );
		
		return new ClientDTO(client);
	}

}
