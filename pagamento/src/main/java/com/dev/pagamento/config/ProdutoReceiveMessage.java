package com.dev.pagamento.config;

import com.dev.pagamento.data.vo.ProdutoVO;
import com.dev.pagamento.entity.Produto;
import com.dev.pagamento.repository.ProdutoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoReceiveMessage {
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoReceiveMessage(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public void receive(@Payload ProdutoVO produtoVO) {
        produtoRepository.save(Produto.create(produtoVO));
    }
}
