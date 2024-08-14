package com.ochiamalu.wx.handler;

import com.ochiamalu.wx.enums.WxChatMsgTypeEnum;

import java.util.Map;

public interface WxChatMsgHandler {
    WxChatMsgTypeEnum getMsgType();
    String dealMsg(Map<String, String> messageMap);
}
