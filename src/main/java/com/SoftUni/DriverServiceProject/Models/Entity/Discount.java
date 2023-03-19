package com.SoftUni.DriverServiceProject.Models.Entity;

import com.SoftUni.DriverServiceProject.Models.Enums.DiscountEnumName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="discounts")
public class Discount extends BaseEntity{

    private DiscountEnumName name;
    private Float rate;

    public Discount() {
    }

    @Column
    public DiscountEnumName getName() {
        return name;
    }

    public void setName(DiscountEnumName name) {
        this.name = name;
    }

    @Column
    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}


