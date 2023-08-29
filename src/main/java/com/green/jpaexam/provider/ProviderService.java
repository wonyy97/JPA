package com.green.jpaexam.provider;

import com.green.jpaexam.entity.ProductEntity;
import com.green.jpaexam.entity.ProviderEntity;
import com.green.jpaexam.provider.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProviderService {
    private final ProviderRepository rep;


    @Transactional
    public ProviderInsVo saveProvider(ProviderReqDto dto) {
        ProviderEntity entity = ProviderEntity.builder()
                .name(dto.getName())
                .build();
        rep.save(entity);

        return ProviderInsVo.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt().toString())
                .build();

    }

    public List<ProviderRes> getProvider() {
        List<ProviderEntity> list = rep.findAll(Sort.by(Sort.Direction.ASC, "name"));
        List<ProviderRes> result = list.stream().map(item ->
                ProviderRes.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .build()
        ).toList();
        return result;

    }


    public ProviderUpdVo updProvider(ProviderUpdDto dto) {
        ProviderEntity entity = rep.findById(dto.getId()).get();
//        ProviderEntity entity = rep.getReferenceById(dto.getId());


        entity.setName(dto.getName());
        rep.save(entity);

        return ProviderUpdVo.builder()
                .id(entity.getId())
                .name(entity.getName())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public void delProvider(Long id) {
        rep.deleteById(id);
    }

}
