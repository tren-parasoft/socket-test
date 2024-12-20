package com.socket.client;

import org.junit.jupiter.api.Test;


class TCPClientUtilTest {

    @Test
    public void clientSendMessage() {
        TCPClient tcpClient = TCPClientUtil.CreateTCPClient(true);
        String message = "Hello, World!";
        String response = tcpClient.sendMessage(message);

        System.out.printf(response);

        tcpClient.close();
    }
}