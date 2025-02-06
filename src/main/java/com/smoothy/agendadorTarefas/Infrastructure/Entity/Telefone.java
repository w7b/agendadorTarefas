package com.smoothy.agendadorTarefas.Infrastructure.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

@Table(name = "telefone")
public class Telefone{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ddd", length = 3)
    private String ddd;
    @Column(name = "numero", length = 10)
    private String numero;

    //Subir o ddd pra cima do numero
}