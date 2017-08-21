package com.project.handcricket.email;

import com.project.handcricket.models.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

  private JavaMailSender javaMailSender;

  @Autowired
  public EmailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void sendMail(Email email) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo("handcricketgame@gmail.com");
    mailMessage.setSubject("User Feedback " + new Date());
    mailMessage.setText(email.toString());
    mailMessage.setFrom("Feedback");
    javaMailSender.send(mailMessage);
  }
}
