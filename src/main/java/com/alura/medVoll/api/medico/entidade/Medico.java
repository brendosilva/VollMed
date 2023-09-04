package com.alura.medVoll.api.medico.entidade;


import com.alura.medVoll.api.endereco.entidade.Endereco;
import com.alura.medVoll.api.medico.AtalizarMedico;
import com.alura.medVoll.api.medico.CadastroMedico;
import com.alura.medVoll.api.medico.Especialidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    private Boolean active;


    public Medico(CadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.active = true;
    }

    public void atualizarInfo(AtalizarMedico atualizarMedicos) {
       if (atualizarMedicos.nome() != null) this.nome = atualizarMedicos.nome();
       if(atualizarMedicos.telefone() != null) this.telefone = atualizarMedicos.telefone();
       if (atualizarMedicos.endereco() != null) this.endereco.atualizarEndereco(atualizarMedicos.endereco());
    }

    public void excluir() {
        this.active = false;
    }
}
