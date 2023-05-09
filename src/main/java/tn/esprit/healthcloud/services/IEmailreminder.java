package tn.esprit.healthcloud.services;

import com.sun.istack.ByteArrayDataSource;

import javax.mail.MessagingException;

public interface IEmailreminder {
    void sendEmailreminder(String to, String subject, String body) throws MessagingException;

}
