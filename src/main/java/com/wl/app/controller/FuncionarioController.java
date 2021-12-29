package com.wl.app.controller;

import java.util.List;

import com.wl.app.model.Funcionario;
import com.wl.app.repository.FuncionarioRepository;
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


@RestController
@RequestMapping("api/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@GetMapping
	public List<Funcionario> listar() {

		return funcionarioRepository.findAll();
	}
	
	@GetMapping("{id}")
	public Funcionario listarFuncionarioPorId(@PathVariable Long id){

		return funcionarioRepository.findById(id).get();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario adicionar(@RequestBody Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	@DeleteMapping("{id}")
	public void excluir(@PathVariable Long id) {

		funcionarioRepository.deleteById(id);
	}
	
	@PutMapping("{id}")
	public Funcionario atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario) {
		Funcionario funcionarioAtual = funcionarioRepository.findById(id).get();
		BeanUtils.copyProperties(funcionario, funcionarioAtual, "id");
		return funcionarioRepository.save(funcionarioAtual);
	}
	
	
}
