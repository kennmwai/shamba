/**
 * 
 */
package com.kenm.spring.farmleaseservice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author User
 *
 */
@Entity
@Table(name = "farm_leases")
public class FarmLease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String farmerName;
    
    @Column(nullable = false)
    private String farmAddress;
    
    @Column(nullable = false)
    private String crop;
    
    @Column(nullable = false)
    private Integer acres;
    
    @Column(nullable = false)
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