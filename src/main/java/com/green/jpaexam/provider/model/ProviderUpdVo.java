package com.green.jpaexam.provider.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProviderUpdVo {
    private long id;
    private String name;
    private LocalDateTime updatedAt;
}
