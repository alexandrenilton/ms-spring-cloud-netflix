package com.dev.crud.entity;

import com.dev.crud.data.vo.ProdutoVO;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "produto")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "estoque", nullable = false, length = 10)
    private Integer estoque;

    @Column(name = "preco", nullable = false, length = 10)
    private Double preco;

    /* Converter de ProdutoVO para Produto*/
    public static Produto create(ProdutoVO produtoVO) {
        return new ModelMapper().map(produtoVO, Produto.class);
    }
}
