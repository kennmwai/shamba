/**
 * 
 */
package com.kenm.spring.farmleaseservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * @author User
 *
 */
@Entity
public class FarmLease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String farmerName;
    private String farmAddress;
    private String crop;
    private Integer acres;
    private Double pricePerAcre;

    // Getters and setters
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
}