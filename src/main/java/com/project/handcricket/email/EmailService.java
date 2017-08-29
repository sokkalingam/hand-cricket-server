package com.project.handcricket.email;

import com.project.handcricket.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
    mailMessage.setSubject("User Feedback");
    mailMessage.setText(email.toString());
    mailMessage.setFrom("Feedback");
    javaMailSender.send(mailMessage);
  }
}
