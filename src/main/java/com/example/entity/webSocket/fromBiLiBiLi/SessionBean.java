package com.example.entity.webSocket.fromBiLiBiLi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessionBean {

    private WebSocketSession session;

    private Integer clientId;

}
