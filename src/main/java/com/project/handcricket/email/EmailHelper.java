package com.project.handcricket.email;

import com.project.handcricket.model.Email;
import com.project.handcricket.model.Player;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.mail.SimpleMailMessage;

public class EmailHelper {

  public static SimpleMailMessage getFeedbackEmail(Email email) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo("handcricketgame@gmail.com");
    mailMessage.setSubject("User Feedback");
    mailMessage.setText(email.toString());
    return mailMessage;
  }

  public static SimpleMailMessage getHighScoreEmail(Player topScorer, Player currentPlayer) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(currentPlayer.getEmail());
    mailMessage.setSubject(topScorer.getName() + " beat your Score! Scored " + topScorer.getRuns() + " runs");
    mailMessage.setText(topScorer.getName() + " scored " + topScorer.getRuns() +
        " and beat your score of " + currentPlayer.getRuns() + " runs\n\n" +
        "Go to www.handcricketgame.com to play and set your new high score");
    return mailMessage;
  }

  public static SimpleMailMessage getHighWinsEmail(Player topScorer, Player currentPlayer) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(currentPlayer.getEmail());
    mailMessage.setSubject(topScorer.getName() + " beat your Wins! Won " + topScorer.getWins() + " times");
    mailMessage.setText(topScorer.getName() + " won " + topScorer.getWins() +
        " times and beat your " + currentPlayer.getWins() + " wins\n\n" +
        "Go to www.handcricketgame.com to play and set your new high score");
    return mailMessage;
  }

}
