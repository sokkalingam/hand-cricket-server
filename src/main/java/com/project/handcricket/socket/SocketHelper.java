package com.project.handcricket.socket;

import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;

public class SocketHelper {

  public static String getSimpSessionId(AbstractSubProtocolEvent event) {
    return event.getMessage().getHeaders().get("simpSessionId").toString();
  }
}
