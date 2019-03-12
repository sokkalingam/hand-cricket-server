package com.project.handcricket.email;

import com.project.handcricket.model.Email;
import com.project.handcricket.model.Player;
import com.project.handcricket.services.EncryptDecryptService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private JavaMailSenderImpl javaMailSender;

  @Value("${spring.mail.username}")
  private String email;

  @Value("${spring.mail.password}")
  private String password;

  private EncryptDecryptService encryptDecryptService;

  @Autowired
  public EmailService(JavaMailSenderImpl javaMailSender, EncryptDecryptService encryptDecryptService) {
    this.javaMailSender = javaMailSender;
    this.encryptDecryptService = encryptDecryptService;
  }

  public void sendMail(SimpleMailMessage simpleMailMessage) {
    if (simpleMailMessage.getTo().length > 0
        && EmailValidator.getInstance().isValid(simpleMailMessage.getTo()[0])) {
        javaMailSender.setUsername(encryptDecryptService.decrypt(email));
        javaMailSender.setPassword(encryptDecryptService.decrypt(password));
        javaMailSender.send(simpleMailMessage);
    }
  }

  public void sendFeedbackEmail(Email email) {
    sendMail(EmailHelper.getFeedbackEmail(email));
  }

  public synchronized void sendHighScore(Player topScorer, Player currentPlayer) {
    if (currentPlayer == null || currentPlayer.getEmail() == null) return;
    _sendHighScoreEmail(topScorer, currentPlayer);
  }

  public synchronized void sendHighWins(Player topScorer, Player currentPlayer) {
    if (currentPlayer == null || currentPlayer.getEmail() == null) return;
    _sendHighWinsEmail(topScorer, currentPlayer);
  }

  private synchronized void _sendHighScoreEmail(Player topScorer, Player currentPlayer) {
    if ((topScorer.getEmail() == null) ||
        (topScorer.getEmail() != null && !currentPlayer.getEmail().equals(topScorer.getEmail())))
      sendMail(EmailHelper.getHighScoreEmail(topScorer, currentPlayer));
  }

  private synchronized void _sendHighWinsEmail(Player topScorer, Player currentPlayer) {
    if ((topScorer.getEmail() == null) ||
        (topScorer.getEmail() != null && !currentPlayer.getEmail().equals(topScorer.getEmail())))
      sendMail(EmailHelper.getHighWinsEmail(topScorer, currentPlayer));
  }
}
