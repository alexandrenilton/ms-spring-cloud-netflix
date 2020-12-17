package com.dev.crud.data.vo;

import com.dev.crud.entity.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@JsonPropertyOrder({"id","nome","estoque","preco"})
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class ProdutoVO extends RepresentationModel<ProdutoVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonPropertyOrder("id")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("estoque")
    private Integer estoque;

    @JsonProperty("preco")
    private Double preco;

    /* converter de Produto para ProdutoVO*/
    public static ProdutoVO create(Produto produto) {
        return new ModelMapper().map(produto, ProdutoVO.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
