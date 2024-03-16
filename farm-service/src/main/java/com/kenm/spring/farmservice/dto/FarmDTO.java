/**
 *
 */
package com.kenm.spring.farmservice.dto;

/**
 * @author User
 *
 */
import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class FarmDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Farmer name is required")
	private String farmerName;
	private String farmAddress;
	private String crop;
	private Integer acres;
	private Double pricePerAcre;

	public FarmDTO() {
		super();
	}

	public FarmDTO(Long id, String farmerName, String farmAddress, String crop, Integer acres,
			Double pricePerAcre) {
		super();
		this.id = id;
		this.farmerName = farmerName;
		this.farmAddress = farmAddress;
		this.crop = crop;
		this.acres = acres;
		this.pricePerAcre = pricePerAcre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFarmerName() {
		return farmerName;
	}

	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}

	public String getFarmAddress() {
		return farmAddress;
	}

	public void setFarmAddress(String farmAddress) {
		this.farmAddress = farmAddress;
	}

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public Integer getAcres() {
		return acres;
	}

	public void setAcres(Integer acres) {
		this.acres = acres;
	}

	public Double getPricePerAcre() {
		return pricePerAcre;
	}

	public void setPricePerAcre(Double pricePerAcre) {
		this.pricePerAcre = pricePerAcre;
	}

	@Override
	public String toString() {
		return "FarmDTO [id=" + id + ", farmerName=" + farmerName + ", farmAddress=" + farmAddress + ", crop="
				+ crop + ", acres=" + acres + ", pricePerAcre=" + pricePerAcre + "]";
	}

}