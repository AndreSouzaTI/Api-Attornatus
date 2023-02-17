package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dto.CadastroDto;
import dto.EnderecoDto;
import entity.Pessoa;
import repository.EnderecoRepository;
import repository.PessoaRepository;

@RestController
public class CadastroController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@PostMapping("/pessoa")
    public Pessoa criarPessoa(@RequestBody CadastroDto cadastro) {
        return pessoaRepository.save(cadastro.getPessoa());
    }

    @GetMapping("/pessoas")
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    @GetMapping("/enderecos")
    public List<EnderecoDto> listarEnderecos() {
        return pessoaRepository.getJoinInfo();
    }


    @GetMapping("/pessoa/{id}")
    public ResponseEntity consultarPessoa(@PathVariable Long id) {
        Optional<Pessoa> personById = pessoaRepository.findById(id);

        if(personById.isPresent()) {
            return new ResponseEntity(pessoaRepository.findById(id), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pessoa/{id}/enderecos")
    public ResponseEntity consultarEnderecosPessoa(@PathVariable Long id) {
        Optional<Pessoa> personById = pessoaRepository.findById(id);

        if(personById.isPresent()) {
            return new ResponseEntity(pessoaRepository.getJoinInfoById(id), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pessoa/{id}/enderecos/principal")
    public ResponseEntity consultarEnderecoPrincipalPessoa(@PathVariable Long id) {
        Optional<Pessoa> personById = pessoaRepository.findById(id);

        if(personById.isPresent()) {
            return new ResponseEntity(pessoaRepository.getJoinInfoMainById(id), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/pessoa")
    public ResponseEntity editarPessoa(@RequestBody CadastroDto cadastro) {
        Optional<Pessoa> personById = pessoaRepository.findById(cadastro.getPessoa().getId());

        if(personById.isPresent()) {
            return new ResponseEntity(pessoaRepository.save(cadastro.getPessoa()), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/endereco")
    public ResponseEntity criarEndereco(@RequestBody CadastroDto cadastro) {
        Optional<Pessoa> personById = pessoaRepository.findById(cadastro.getPessoa().getId());

        if(personById.isPresent()) {
            return new ResponseEntity(pessoaRepository.save(cadastro.getPessoa()), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}
