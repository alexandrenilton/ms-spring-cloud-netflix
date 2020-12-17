package com.dev.pagamento.entity;

import com.dev.pagamento.data.vo.ProdutoVO;
import com.dev.pagamento.data.vo.VendaVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;


@Entity
@Table(name = "produto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Produto {
    @Id
    private Long id;

    @Column(name = "estoque", nullable = false, length = 10)
    private Integer estoque;

    public static Produto create(ProdutoVO produtoVO) {
        return new ModelMapper().map(produtoVO, Produto.class);
    }
}
