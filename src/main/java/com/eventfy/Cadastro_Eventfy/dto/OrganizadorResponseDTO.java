package com.eventfy.Cadastro_Eventfy.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrganizadorResponseDTO {
    private Long idOrganizador;
    private String nomeOrganizador;
    private byte[] fotoOrganizador;
    private String emailOrganizador;
    private String contatoOrganizador;
    private LocalDateTime dataCadastro;
}
