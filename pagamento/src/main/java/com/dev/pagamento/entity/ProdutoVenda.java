package com.dev.pagamento.entity;

import com.dev.pagamento.data.vo.ProdutoVO;
import com.dev.pagamento.data.vo.ProdutoVendaVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto_venda")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProdutoVenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_produto", nullable = false, length = 10)
    private Long idProduto;

    @Column(name = "quantidade", nullable = false, length = 10)
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venda")
    private Venda venda;


    public static ProdutoVenda create(ProdutoVendaVO produtoVendaVO) {
        return new ModelMapper().map(produtoVendaVO, ProdutoVenda.class);
    }
}
