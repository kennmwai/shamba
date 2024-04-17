package com.ken.spring.farmcommons.utils;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "created_at", "updated_at" }, allowGetters = true)
@Data
public abstract class AuditMetadata implements Serializable {

	private static final long serialVersionUID = 1L;

	@CreatedDate
	@Column(name = "CREATED_AT", updatable = false)
	private LocalDateTime createdAt;

	@CreatedBy
	@Column(name = "CREATED_BY", updatable = false)
	private String createdBy;

	@LastModifiedDate
	@Column(name = "UPDATED_AT")
	private LocalDateTime lastModifiedAt;

	@LastModifiedBy
	@Column(name = "UPDATED_BY")
	private String lastModifiedBy;
}
