package com.dev.crud.services;

import com.dev.crud.data.vo.ProdutoVO;
import com.dev.crud.entity.Produto;
import com.dev.crud.exception.ResourceNotFoundException;
import com.dev.crud.message.ProdutoSendMessage;
import com.dev.crud.repository.ProdutoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRespository respository;
    private final ProdutoSendMessage produtoSendMessage;

    @Autowired
    public ProdutoService(ProdutoRespository respository, ProdutoSendMessage produtoSendMessage) {
        this.respository = respository;
        this.produtoSendMessage = produtoSendMessage;
    }

    public ProdutoVO create(ProdutoVO produtoVO) {
        ProdutoVO pvo = ProdutoVO.create(respository.save(Produto.create(produtoVO)));

        // send to queue
        produtoSendMessage.sendMessage(pvo);

        return pvo;
    }

    public Page<ProdutoVO> findAll(Pageable pageable) {
        var page = respository.findAll(pageable);
        return page.map(this::convertToProdutoVO);
    }
    private ProdutoVO convertToProdutoVO(Produto produto) {
        return ProdutoVO.create(produto);
    }

    public ProdutoVO findById(Long id) {
        var entity = respository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("No records found for the id.: " + id));
        return ProdutoVO.create(entity);
    }

    public ProdutoVO update(ProdutoVO produtoVO) {
        final Optional<Produto> optionalProduto = respository.findById(produtoVO.getId());

        if (!optionalProduto.isPresent()) {
            new ResourceNotFoundException("No records found for the id.: " + produtoVO.getId());
        }
        return ProdutoVO.create(respository.save(Produto.create(produtoVO)));
    }

    public void delete(Long id) {
        var entity = respository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("No records found for the id.: " + id));
        respository.delete(entity);
    }
}
