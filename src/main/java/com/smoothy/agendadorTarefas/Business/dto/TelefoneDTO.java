package com.smoothy.agendadorTarefas.Business.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TelefoneDTO {
    private Long id;
    private String numero;
    private String ddd;

}
