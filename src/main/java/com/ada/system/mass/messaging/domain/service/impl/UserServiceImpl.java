package com.ada.system.mass.messaging.domain.service.impl;


import java.util.List;

import com.ada.system.mass.messaging.domain.entity.User;
import com.ada.system.mass.messaging.domain.repository.UserJpaRepository;
import com.ada.system.mass.messaging.domain.service.IUserService;
import com.ada.system.mass.messaging.domain.service.ISubscriptionService;
import com.ada.system.mass.messaging.domain.entity.Subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FioxinCel
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserJpaRepository userRepo;
    
    @Autowired
    private ISubscriptionService subService;
    
    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(int id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public boolean deleteUser(int id) {
        User user = userRepo.findById(id).get();
        if(user !=null){
            List<Subscription> subs = subService.findSubscriptionByIdUserAndStatus(id, true);
            if(!subs.isEmpty()){
                subs.forEach( s -> disableSubscription(s));               
             } 
             user.setStatus(false);    
             userRepo.save(user);
              return true;
        }   
        return false;
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User actually, User newUser) {
        actually.setName(newUser.getName());
        actually.setEmail(newUser.getEmail());
        actually.setPhone(newUser.getPhone());
        actually.setSendBalance(newUser.isSendBalance());
        return userRepo.save(actually);
    }
     
   private void disableSubscription(Subscription sub){
       subService.deleteSubscription(sub.getId());
   }

    @Override
    public User getUserByOriginCod(String OriginCod) {
      return   userRepo.findByOriginCod(OriginCod).orElse(null);
    }

}
