package com.ada.system.mass.messaging.domain.service;

import java.util.Date;
import java.util.List;

import com.ada.system.mass.messaging.domain.entity.NotificationMessage;
import com.ada.system.mass.messaging.domain.entity.Reporte;
import java.util.Map;

public interface IMessageService {
    List<NotificationMessage> getAllMessages();
    NotificationMessage getMessage(int id);
    Map<String, Object> sendMessage(String codEmpresa, String finalMessage,List<NotificationMessage> messages);
    List<NotificationMessage> getMessagesByIdUser(int idUser);
    List<NotificationMessage> findByReceiverNumberAndUserId(int idUser, String receiverNumber);
    List<NotificationMessage> getMessagesByCreatedAt(int idUser, Date createdAt);
    List<NotificationMessage> getMessagesByStatus(int idUser, String status);
}
