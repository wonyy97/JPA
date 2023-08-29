package com.green.jpaexam.entity;

import com.green.jpaexam.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "t_provider")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

public class ProviderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "providerEntity", cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "provider_id")
    @ToString.Exclude //양방향일때는 얘가 필수
    private List<ProductEntity> productEntityList = new ArrayList();


}

