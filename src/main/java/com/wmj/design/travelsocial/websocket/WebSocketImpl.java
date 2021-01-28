package com.wmj.design.travelsocial.websocket;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmj.design.travelsocial.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value="/websocket/{fromUserId}/{toUserId}/{userName}")
@Component
public class WebSocketImpl {

    private static CopyOnWriteArraySet<WebSocketImpl> webSocketSet = new CopyOnWriteArraySet<WebSocketImpl>();
    private Session session;
    private String toUserId;
    private String fromUserId;
    private String userName;
    private static Map<String,Session> map = new HashMap<String, Session>();
    @OnOpen
    public void onOpen(Session session,@PathParam("fromUserId") String fromUserId,@PathParam("userName") String userName){
        this.session = session;
        this.fromUserId = fromUserId;
        this.userName = userName;
        map.put(fromUserId,session);
        webSocketSet.add(this);
        System.out.println("有新连接加入:"+userName+ ",当前在线人数为" + webSocketSet.size());
        /*this.session.getAsyncRemote().sendText("你成功连接上WebSocket-->当前在线人数为："+webSocketSet.size());*/
    }
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }
    @OnMessage
    public void onMessage(String message,@PathParam("fromUserId") String fromUserId,
                          @PathParam("toUserId") String toUserId,@PathParam("userName") String userName){
        System.out.println("来自客户端的消息-->"+userName+":"+message);
        ObjectMapper objectMapper = new ObjectMapper();
        SocketMsg socketMsg;
        try{
            socketMsg = objectMapper.readValue(message,SocketMsg.class);
            if (socketMsg.getType() == 1){
                socketMsg.setFromUser(fromUserId);
                socketMsg.setToUser(toUserId);
                Session fromSession = map.get(socketMsg.getFromUser());
                Session toSession = map.get(socketMsg.getToUser());
                if (toSession != null){
                    synchronized(fromSession){
                        fromSession.getAsyncRemote().sendText(userName +":"+socketMsg.getMsg());
                    }
                    synchronized(toSession){
                        toSession.getAsyncRemote().sendText(userName+":"+socketMsg.getMsg());
                    }
                }else{
                    fromSession.getAsyncRemote().sendText("系统消息，对方不在线");
                }
            }else{
                broadcast(socketMsg.getMsg());
            }
        }catch (JsonParseException e){
            e.printStackTrace();
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发送错误");
        error.printStackTrace();
    }
    /**
     * 群发自定义消息
     */
    public void broadcast(String message){
        for (WebSocketImpl item:webSocketSet){
            item.session.getAsyncRemote().sendText(message);
        }
    }
}
