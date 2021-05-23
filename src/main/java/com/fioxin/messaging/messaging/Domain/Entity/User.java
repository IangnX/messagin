package com.fioxin.messaging.messaging.Domain.Entity;

import com.fioxin.messaging.messaging.domain.entity.Subscription;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;




/**
 * User
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
   
    @Column(nullable=false, unique=false) 
    private String name;

    @Column(nullable=false, unique=false)
    private String dni;
    @Column(nullable=false, unique=false)
    private String email;

    @Column(nullable=false, unique=true)
    private String phone;
    
    @Column(nullable=false)
    private Date createdAt;
    private boolean status;

    @OneToMany(mappedBy = "user")
    private List<NotificationMessage> messages;

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscription;
    
    public User() {
    }

    public List<Subscription> getSubscription() {
        return subscription;
    }

    public void setSubscription(List<Subscription> subscription) {
        this.subscription = subscription;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isStatus() {
        return this.status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    

    public List<NotificationMessage> getMessages() {
        return this.messages;
    }

    public void setMessages(List<NotificationMessage> messages) {
        this.messages = messages;
    }
    
}