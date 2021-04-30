/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fioxin.messaging.messaging.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fioxin.messaging.messaging.Domain.Entity.User;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author FioxinCel
 */
@Entity
@Table(name = "subscriptions")
@Data
public class Subscription {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "category_id")
    private Integer categoryId;
    private Double price;
    private Date startDate;
    private Date endDate;
    private boolean status;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Plan plan;
    
}