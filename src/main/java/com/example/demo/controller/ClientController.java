package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClientController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("{id}")
	public Cliente listarClientePorId(@PathVariable Long id){
		return clienteRepository.findById(id).get();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@DeleteMapping("{id}")
	public void excluir(@PathVariable Long id) {
		clienteRepository.deleteById(id);
	}
	
	@PutMapping("{id}")
	public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente clienteAtual = clienteRepository.findById(id).get();
		BeanUtils.copyProperties(cliente, clienteAtual, "id");
		return clienteRepository.save(clienteAtual);
	}
	
	
}
