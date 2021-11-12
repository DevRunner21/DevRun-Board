package com.devrun.backend.common.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public class BaseEntity extends BaseTimeEntity{

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    public void changeCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void changeUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

}
