package com.fioxin.messaging.messaging.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import com.fioxin.messaging.messaging.Domain.Entity.User;
import com.fioxin.messaging.messaging.Domain.Entity.NotificationMessage;
import com.fioxin.messaging.messaging.Domain.Entity.SendMessageRequest;
import com.fioxin.messaging.messaging.Domain.Service.IMessageService;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {
    
    @Autowired
    private IMessageService messageService;

    @GetMapping("/all")
    public List<NotificationMessage> getAllMessages(){
        return messageService.getAllMessages();
    }
    @GetMapping("/{id}")
    public NotificationMessage getMessage(@PathVariable int id){
        return messageService.getMessage(id);
    }
    
    
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable int id){
        messageService.deleteMessage(id);
    }

    @GetMapping("/{idUser}/{number}")
    public  List<NotificationMessage> getMessagesByReceiverNumb(@PathVariable int idUser, @PathVariable String number){
        return messageService.findByReceiverNumberAndUserId(idUser, number);
    }

    @GetMapping("/user/{idUser}")
    public List<NotificationMessage> getMessagesByUserId(@PathVariable int idUser){
        return messageService.getMessagesByIdUser(idUser);
    }

    @PostMapping("/save")
    public SendMessageRequest sendMessage(@RequestBody SendMessageRequest message){
        List<NotificationMessage> messages = message.getMessages();
        int idUser = message.getUser().getId();
        return messageService.sendMessage(messages, idUser);
          
    }
    
    @GetMapping("/prueba")
    public ResponseEntity<?> prueba(){
        Map<String, Object> response = new HashMap<>();
        SendMessageRequest msg = new SendMessageRequest();
        try {
            msg = messageService.prueba();
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al realizar la consulta en la Base de Datos");
            response.put("ERROR", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Elemento", msg);
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
