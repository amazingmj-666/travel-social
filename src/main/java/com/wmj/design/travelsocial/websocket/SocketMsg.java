package com.wmj.design.travelsocial.websocket;

import lombok.Data;

@Data
public class SocketMsg {
    private int type;
    private String fromUser;
    private String toUser;
    private String sendTime;
    private  String msg;
}
