package com.project.handcricket.email;

import com.project.handcricket.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

  @Autowired
  private EmailService emailService;

  @RequestMapping(method = RequestMethod.POST, value = "/feedback")
  public String sendEmail(@RequestBody Email email) {
    emailService.sendFeedbackEmail(email);
    return "Email Sent";
  }

}
