package com.green.jpaexam.provider.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProviderInsVo {
    private long id;
    private String name;
    private String createdAt;
}
