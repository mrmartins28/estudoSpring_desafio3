package com.lsmartin.crudcliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
	
	@Transactional
	public  ClientDTO insert(ClientDTO dto) {
		
		Client client = new Client();
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
		
		client = clientRepository.save(client);
		
		return new ClientDTO(client);
	}
	
	@Transactional
	public  ClientDTO update(ClientDTO dto, Long id) {
		
		Client client =  clientRepository.findById(id).get() ;
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
		
		client = clientRepository.save(client);
		
		return new ClientDTO(client);
	}
	
	@Transactional
	public  void delete(Long id) {	
		
		if(!clientRepository.existsById(id)) {
			
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		
		clientRepository.deleteById(id);;
		
	}
	
	/*
	public Client dtoToEntity(ClientDTO dto) {
		
		Client client = new Client();
		client.setId(dto.getId());
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
		
		return client;
		
	}
	*/
	
}
