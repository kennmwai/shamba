package com.kenm.spring.farmservice.dto;

import java.util.List;

import com.kenm.spring.farmleaseservice.dto.FarmLeaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FarmResourceDTO {
	private FarmDTO farm;
	private List<FarmLeaseDTO> lease;

	// private List<Link> links;

}
