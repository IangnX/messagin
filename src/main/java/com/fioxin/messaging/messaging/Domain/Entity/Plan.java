/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fioxin.messaging.messaging.domain.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author FioxinCel
 */
@Data
@Entity
@Table(name = "Plans")
public class Plan {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(nullable=false, unique=false)
    private String name;
    @Column(nullable=false)
    private String paymentType;
    private Integer term;
    @Column(name = "category_id")
    private Integer categoryId;
    private boolean status;
    
    @OneToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;
    
    
    @OneToMany(mappedBy = "plan")
    private List<Subscription> subscription;
    
}
