package com.eventfy.Cadastro_Eventfy.controller;


import com.eventfy.Cadastro_Eventfy.dto.OrganizadorInsercaoDTO;
import com.eventfy.Cadastro_Eventfy.dto.OrganizadorResponseDTO;
import com.eventfy.Cadastro_Eventfy.service.OrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/organizadores")
public class OrganizadorController {

    @Autowired
    private OrganizadorService organizadorService;

    @GetMapping("/verificar-email")
    public ResponseEntity<?> verificarEmail(@RequestParam String email) {

        boolean existe = organizadorService.emailJaCadastrado(email);
        return ResponseEntity.ok().body(Collections.singletonMap("existe", existe));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<OrganizadorResponseDTO> cadastrar(@RequestBody OrganizadorInsercaoDTO organizador) {
        System.out.println("Dados recebidos: " + organizador);
        if (organizadorService.emailJaCadastrado(organizador.getEmailOrganizador())) {
            return ResponseEntity.badRequest().body(null);
        }
        OrganizadorResponseDTO novoOrganizador = organizadorService.inserir(organizador);
        return ResponseEntity.ok(novoOrganizador);
    }

    @GetMapping
    public ResponseEntity<List<OrganizadorResponseDTO>> listarTodos() {
        List<OrganizadorResponseDTO> organizadores = organizadorService.listarTodos();
        return ResponseEntity.ok(organizadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizadorResponseDTO> buscarPorId(@PathVariable Long id) {
        OrganizadorResponseDTO organizador = organizadorService.buscarPorId(id);
        if (organizador != null) {
            return ResponseEntity.ok(organizador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (organizadorService.deletar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}