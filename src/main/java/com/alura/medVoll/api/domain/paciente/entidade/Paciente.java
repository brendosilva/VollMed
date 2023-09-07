package com.alura.medVoll.api.domain.paciente.entidade;


import com.alura.medVoll.api.domain.paciente.AtualizarPaciente;
import com.alura.medVoll.api.domain.paciente.CadastroPaciente;
import com.alura.medVoll.api.domain.endereco.entidade.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String nome;
   private String email;
   private String telefone;
   private String cpf;

   @Embedded
   private Endereco endereco;

   public Paciente(CadastroPaciente dados) {
      this.nome = dados.nome();
      this.email = dados.email();
      this.telefone = dados.telefone();
      this.cpf = dados.cpf();
      this.endereco = new Endereco(dados.endereco());
   }

   public void atualizaInfo(AtualizarPaciente dados) {
      if (dados.nome() != null) this.nome = dados.nome();
      if(dados.telefone() != null) this.telefone = dados.telefone();
      if (dados.endereco() != null) this.endereco.atualizarEndereco(dados.endereco());
   }
}
