package com.socket.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    private Socket clientSocket;
    private BufferedReader socketIn = null;
    private PrintWriter socketOut = null;

    public TCPClient(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            this.socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String message) {
        try {
            socketOut.println(message);

            return socketIn.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            socketIn.close();
            socketOut.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
