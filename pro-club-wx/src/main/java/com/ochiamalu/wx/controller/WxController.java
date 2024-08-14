package com.ochiamalu.wx.controller;

import com.ochiamalu.wx.handler.WxChatMsgFactory;
import com.ochiamalu.wx.handler.WxChatMsgHandler;
import com.ochiamalu.wx.utils.MessageUtil;
import com.ochiamalu.wx.utils.SHA1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class WxController {

    @Resource
    private WxChatMsgFactory wxChatMsgFactory;

    private static final String TOKEN = "sadkhjhwdwjk";

    @PostMapping(value = "/callback", produces = "application/xml;charset=UTF-8")
    public String callback(
            @RequestBody String requestBody,
            String signature,
            String timestamp,
            String nonce,
            String echostr,
            @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        String shaStr = SHA1.getSHA1(TOKEN, timestamp, nonce, "");
        if (!signature.equals(shaStr)) {
            return "unknown";
        }
        Map<String, String> msgMap = MessageUtil.parseXml(requestBody);
        String msgType = msgMap.get("MsgType");
        String event = msgMap.get("Event") == null ? "" : msgMap.get("Event");
        String msgTypeKey = msgType + "." + event;
        WxChatMsgHandler handler = wxChatMsgFactory.getHandlerByMsgType(msgTypeKey);
        if (handler == null) {
            return "unknown";
        }
        return handler.dealMsg(msgMap);
    }
}
