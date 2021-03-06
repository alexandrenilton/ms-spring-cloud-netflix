package com.dev.crud.repository;

import com.dev.crud.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRespository extends JpaRepository<Produto, Long> {

}
