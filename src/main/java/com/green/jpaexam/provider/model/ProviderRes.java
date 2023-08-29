package com.green.jpaexam.provider.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProviderRes {
    private Long id;
    private String name;
}
