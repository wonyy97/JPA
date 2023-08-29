package com.green.jpaexam.provider;

import com.green.jpaexam.entity.ProviderEntity;
import com.green.jpaexam.provider.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/provider")
public class ProviderController {
    private final ProviderService service;

    @PostMapping
    public ResponseEntity<ProviderInsVo> postProvider(@RequestBody ProviderReqDto dto){
        ProviderInsVo vo = service.saveProvider(dto);
        return ResponseEntity.ok(vo);
    }

    @GetMapping
    public ResponseEntity<List<ProviderRes>> getProvider(){
        return ResponseEntity.ok(service.getProvider()); //ok가 200
    }

    @PutMapping
    public ResponseEntity<ProviderUpdVo> putProvider(@RequestBody ProviderUpdDto dto){
        ProviderUpdVo vo = service.updProvider(dto);
        return ResponseEntity.ok(vo);
    }

    @DeleteMapping
    public ResponseEntity<Integer> delProvider(@RequestParam Long id){
        service.delProvider(id);
        return ResponseEntity.ok(1);
    }
}
