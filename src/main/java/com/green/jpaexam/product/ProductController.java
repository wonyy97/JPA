package com.green.jpaexam.product;

import com.green.jpaexam.product.model.ProductDto;
import com.green.jpaexam.product.model.ProductRes;
import com.green.jpaexam.product.model.ProductResQdsl;
import com.green.jpaexam.product.model.ProductUpdDto;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService SERVICE;

    @PostMapping
    public ResponseEntity<ProductRes> postProduct(@RequestBody ProductDto dto) {
        ProductRes res = SERVICE.saveProduct(dto);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<Page<ProductRes>> getProductAll(@PageableDefault(sort = "number", direction = Sort.Direction.DESC, size = 20) Pageable page) {
        return ResponseEntity.ok(SERVICE.getProductAll(page)); //ok가 200
    }

    @GetMapping("/jpql")
    public ResponseEntity<List<ProductRes>> getProductAllJpql(@PageableDefault(sort = "number", direction = Sort.Direction.DESC, size = 20) Pageable page) {
        return ResponseEntity.ok(SERVICE.getProductAllJpql(page)); //ok가 200
    }

    @GetMapping("/qdsl")
    public ResponseEntity<List<ProductResQdsl>> getProductAllQdsl(
                                                @ParameterObject @PageableDefault(page = 0, sort = "number", direction = Sort.Direction.DESC, size = 20)
                                                Pageable pageable
                                                , @RequestParam(required = false) String search) {
        return ResponseEntity.ok(SERVICE.getProductAllQdsl(pageable, search));
    }

    @GetMapping("/{number}")
    public ResponseEntity<ProductRes> getProduct(@PathVariable Long number) {
        return ResponseEntity.ok(SERVICE.getProduct(number));
    }

    @PutMapping
    public ResponseEntity<ProductRes> updProduct(@RequestBody ProductUpdDto dto) {
        return ResponseEntity.ok(SERVICE.updProduct(dto));
    }

//    @DeleteMapping("/{number}")
//    public void delProduct(@PathVariable Long number) {
//        SERVICE.delProduct(number);
//    }

    @DeleteMapping
    public ResponseEntity<Integer> delProduct(@RequestParam Long number) {
        SERVICE.delProduct(number);
        return ResponseEntity.ok(1);
    }
}
