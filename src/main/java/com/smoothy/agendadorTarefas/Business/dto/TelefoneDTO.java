package com.smoothy.agendadorTarefas.Business.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TelefoneDTO {

    private Long id;
    private String ddd;
    private String numero;

}
