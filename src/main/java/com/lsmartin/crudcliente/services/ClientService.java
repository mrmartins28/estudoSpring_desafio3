package com.lsmartin.crudcliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsmartin.crudcliente.dto.ClientDTO;
import com.lsmartin.crudcliente.entities.Client;
import com.lsmartin.crudcliente.entities.ClientRepository;
import com.lsmartin.crudcliente.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
		
		try {
			Client client =  clientRepository.getReferenceById(id) ;
			client.setName(dto.getName());
			client.setCpf(dto.getCpf());
			client.setIncome(dto.getIncome());
			client.setBirthDate(dto.getBirthDate());
			client.setChildren(dto.getChildren());
			
			client = clientRepository.save(client);
			return new ClientDTO(client);
			
		} catch (EntityNotFoundException e) {
			
			throw new ResourceNotFoundException("Recurso não encontrado");
		}	
		
		
	}
	
	@Transactional
	public  void delete(Long id) {	
		
		if(!clientRepository.existsById(id)) {
			
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		
		clientRepository.deleteById(id);;
		
	}
	
	@Transactional(readOnly = true)
	public  Page<ClientDTO> findAll(Pageable pageable) {	
		
		
		Page<Client> list = clientRepository.findAll(pageable);
	
		return list.map(x -> new ClientDTO(x));
	}
	
	
	
}
