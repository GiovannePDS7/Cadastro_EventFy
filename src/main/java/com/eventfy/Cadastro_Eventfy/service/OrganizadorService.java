package com.eventfy.Cadastro_Eventfy.service;

import com.eventfy.Cadastro_Eventfy.dto.OrganizadorInsercaoDTO;
import com.eventfy.Cadastro_Eventfy.dto.OrganizadorResponseDTO;
import com.eventfy.Cadastro_Eventfy.entity.Organizador;
import com.eventfy.Cadastro_Eventfy.mapper.ModelMapperCustom;
import com.eventfy.Cadastro_Eventfy.repository.OrganizadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrganizadorService {
    private static final Logger log = LoggerFactory.getLogger(OrganizadorService.class);

    @Autowired
    private ModelMapperCustom mapper;
    @Autowired
    private OrganizadorRepository organizadorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean emailJaCadastrado(String email) {
        return organizadorRepository.findByEmailOrganizador(email).isPresent();
    }

    public OrganizadorResponseDTO inserir(OrganizadorInsercaoDTO organizador) {
        if (organizador.getSenhaOrganizador() == null) {
            throw new IllegalArgumentException("Senha não pode ser nula");
        }

        var insert = mapper.map(organizador, Organizador.class);
        var senhaCifrada = passwordEncoder.encode(organizador.getSenhaOrganizador());
        log.info("Senha cifrada => {}", senhaCifrada);
        insert.setSenhaOrganizador(senhaCifrada);
        return mapper.map(organizadorRepository.save(insert), OrganizadorResponseDTO.class);
    }


    public List<OrganizadorResponseDTO> listarTodos() {
        List<Organizador> organizadores = organizadorRepository.findAll();
        return organizadores.stream()
                .map(org -> mapper.map(org, OrganizadorResponseDTO.class))
                .collect(Collectors.toList());
    }

    public OrganizadorResponseDTO buscarPorId(Long id) {
        return organizadorRepository.findById(id)
                .map(org -> mapper.map(org, OrganizadorResponseDTO.class))
                .orElse(null);
    }

    public OrganizadorResponseDTO atualizar(Long id, OrganizadorInsercaoDTO organizadorDTO) {
        return organizadorRepository.findById(id)
                .map(organizadorExistente -> {
                    organizadorExistente.setNomeOrganizador(organizadorDTO.getNomeOrganizador());
                    organizadorExistente.setEmailOrganizador(organizadorDTO.getEmailOrganizador());

                    if (organizadorDTO.getSenhaOrganizador() != null && !organizadorDTO.getSenhaOrganizador().isEmpty()) {
                        var senhaCifrada = passwordEncoder.encode(organizadorDTO.getSenhaOrganizador());
                        organizadorExistente.setSenhaOrganizador(senhaCifrada);
                    }

                    return mapper.map(organizadorRepository.save(organizadorExistente), OrganizadorResponseDTO.class);
                })
                .orElse(null);
    }

    public boolean deletar(Long id) {
        if (organizadorRepository.existsById(id)) {
            organizadorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
