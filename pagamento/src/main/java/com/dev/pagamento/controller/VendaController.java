package com.dev.pagamento.controller;


import com.dev.pagamento.data.vo.ProdutoVO;
import com.dev.pagamento.data.vo.VendaVO;
import com.dev.pagamento.entity.Venda;
import com.dev.pagamento.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/venda")
@RestController
public class VendaController {
    private final VendaService vendaService;
    private final PagedResourcesAssembler<VendaVO> assembler;

    @Autowired
    public VendaController(VendaService vendaService, PagedResourcesAssembler<VendaVO> assembler) {
        this.vendaService = vendaService;
        this.assembler = assembler;
    }

    @GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
    public VendaVO findById(@PathVariable("id")  Long id) {
        VendaVO vendaVO = vendaService.findById(id);
        vendaVO.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());
        return vendaVO;
    }

    @GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "12") int limit,
                                     @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection,"data"));
        Page<VendaVO> vendas = vendaService.findAll(pageable);
        vendas.stream()
                .forEach(v -> v.add(linkTo(methodOn(VendaController.class).findById(v.getId())).withSelfRel()));
        PagedModel<EntityModel<VendaVO>> pagedModel = assembler.toModel(vendas);

        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json","application/xml","application/x-yaml"},
            consumes = {"application/json","application/xml","application/x-yaml"})
    public VendaVO create(@RequestBody VendaVO vendaVO) {
        VendaVO venVo = vendaService.create(vendaVO);
        venVo.add(linkTo(methodOn(VendaController.class).findById(venVo.getId())).withSelfRel());
        return venVo;
    }

    @PutMapping(produces = {"application/json","application/xml","application/x-yaml"},
            consumes = {"application/json","application/xml","application/x-yaml"})
    public VendaVO update(@RequestBody VendaVO vendaVO) {
        VendaVO venVo = vendaService.update(vendaVO);
        venVo.add(linkTo(methodOn(VendaController.class).findById(vendaVO.getId())).withSelfRel());
        return venVo;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        vendaService.delete(id);
        return ResponseEntity.ok().build();
    }

}
