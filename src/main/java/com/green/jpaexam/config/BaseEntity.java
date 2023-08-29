package com.green.jpaexam.config;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) //어노테이션 필수
@SuperBuilder
@NoArgsConstructor
public class BaseEntity {

    @CreatedDate //spring 에서 제공하는것
//    @CreationTimestamp //하이버네이트에서 제공하는것
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate //spring 에서 제공하는 어노테이션
//    @UpdateTimestamp //하이버네이트에서 제공하는것
    private LocalDateTime updatedAt;
    //사진같이 수정이 불가한 것에는 updatedAt이 없을 수 있다



}
