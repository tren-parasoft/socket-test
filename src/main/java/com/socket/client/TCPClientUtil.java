package com.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.socket.SocketConstant.SERVER_IP;
import static com.socket.SocketConstant.TCP_PORT;

public class TCPClientUtil {

    public static TCPClient CreateTCPClient(boolean reuseAddress) {
        TCPClient tcpClient = null;
        try {
            Socket clientSocket = new Socket(SERVER_IP, TCP_PORT);
            // Set option
            if (reuseAddress) {
                clientSocket.setReuseAddress(true);
            }

            clientSocket.setTcpNoDelay(true);

            tcpClient = new TCPClient(clientSocket);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return tcpClient;
    }

    public void CreateTCPClient() {
        Socket clientSocket = null;
        System.out.println("Creating TCP Client");

        try {
            clientSocket = new Socket(SERVER_IP, TCP_PORT);
            clientSocket.setReuseAddress(true);
            System.out.println("connect to server");

            // 获取输入输出流
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

                String userInput;
                while(true)
                {
                    System.out.print("输入消息（输入 bye 退出）：");
                    userInput = consoleInput.readLine();
                    out.println(userInput);

                    // 读取服务端返回的消息
                    String serverResponse = in.readLine();
                    System.out.println("服务端回复：" + serverResponse);

                    // 如果输入 "bye"，退出
                    if ("bye".equalsIgnoreCase(userInput)) {
                        System.out.println("已断开连接");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

