package com.optum.clm.consumer.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public abstract class Model {

	private Long id;

	private LocalDateTime createdDate;

	private LocalDateTime lastModifiedDate;
}
