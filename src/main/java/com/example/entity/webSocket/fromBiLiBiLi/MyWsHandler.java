package com.example.entity.webSocket.fromBiLiBiLi;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * webSocket目的： 响应实时性的数据
 * 应用场景： 消息通知-不刷新网页画面得到消息提示；聊天对话；弹幕；股票基金基金指数趋势图；联机游戏；网站实时统计数量等
 * 为什么需要：为了突破http请求单向限制；全双工通信协议-B/S任何一方都可以发起请求；
 * 通信过程：基于现有http协议做了一个协议升级-http协议前加ws开头，同时header中加参数（upgrade: websocket; Connection: upgrade等）告知浏览器这是个协议升级，服务器返回状态码101同意升级
 */
@Slf4j
@Component
@EnableScheduling
public class MyWsHandler extends AbstractWebSocketHandler {

    private final static Map<String, SessionBean> sessionBeanMap = new ConcurrentHashMap<>();

    private final static AtomicInteger clientMarker = new AtomicInteger(0);

    /**
     * 连接建立后
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        SessionBean sessionBean = new SessionBean(session, clientMarker.getAndIncrement());
        sessionBeanMap.put(session.getId(), sessionBean);
        log.info(sessionBeanMap.get(session.getId()).getClientId() + "建立了连接");
        List<Integer> clientIds = sessionBeanMap.keySet().stream().map(sessionBeanMap::get)
                .collect(Collectors.toList())
                .stream().map(SessionBean::getClientId)
                .collect(Collectors.toList());
        // 群发建立连接消息
        sessionBeanMap.forEach((sessionId, sessionBeanDB) -> {
            try {
                // 服务器以WebSocketSession连接着所有客户端，所谓群发，就是服务器通过所有session对客户端发送消息
                WebSocketSession sessionGroup = sessionBeanDB.getSession();
                // 当使用session对客户端发送消息是一定要做判断，如果连接已经关闭，session会报错
                if (sessionGroup.isOpen()) {
                    sessionGroup.sendMessage(new TextMessage(StringUtils.join(clientIds, ",") + "建立了连接"));
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error("群发消息失败：{}", e.getMessage());
            }
        });
    }

    /**
     * 处理文本消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        log.info("收到了{}的消息{}", sessionBeanMap.get(session.getId()).getClientId(), message.getPayload());

    }

    /**
     * 处理传输错误
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        if (session.isOpen()) {
            session.close();
        }
        sessionBeanMap.remove(session.getId());
        log.info("{}传输异常", session.getId());

    }

    /**
     * 关闭连接后
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        log.info("{}-{}关闭了连接", session.getId(), sessionBeanMap.get(session.getId()).getClientId());
    }

    @Scheduled(fixedDelay = 2000)
    public void sendMsg() {
        sessionBeanMap.forEach((sessionId, sessionBean) -> {
            try {
                WebSocketSession session = sessionBean.getSession();
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage("心跳-" + sessionBean.getClientId()));
                }
            } catch (IOException e) {
                log.error("定时心跳异常：{}", e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
