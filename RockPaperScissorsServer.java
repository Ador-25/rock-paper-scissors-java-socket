package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RockPaperScissorsServer {

    private  static int port =8000;
    private static ServerSocket server;
    private static boolean newPlayer=true;

    static {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ;
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;

    public RockPaperScissorsServer() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        // write your code here
        startGame();

    }
    private static void startGame() throws  IOException{
        socket=server.accept();
        in= new DataInputStream(socket.getInputStream());
        out= new DataOutputStream(socket.getOutputStream());
        while (true){
            if(newPlayer){
                DataOutputStream outputStream =
                        new DataOutputStream(socket.getOutputStream());
                String x= in.readUTF();
                System.out.println("New player has joined! Name:"+x);
                outputStream.writeUTF("Welcome! "+ x);
                newPlayer= false;
                continue;
            }
            else{
                try{
                    DataOutputStream outputStream =
                            new DataOutputStream(socket.getOutputStream());
                    in= new DataInputStream(socket.getInputStream());
                    String x= in.readUTF();
                    int cpu=(int) (Math.random() * (4 - 1)) + 1;
                    System.out.println("CLIENT SAID= "+x);
                    System.out.println("CPU SAID= "+cpu);
                    outputStream.writeUTF(getDecision(x,cpu));
                }
                catch (Exception e){
                    newPlayer=true;
                    continue;
                }

            }
        }

    }
    private static String getDecision(String str,int cpu){
        Integer client= Integer.parseInt(str);
        if(client==cpu){
            return "DRAW";
        }
        else if(client==1){
            return cpu==3?"YOU WON!!!":"CPU WON :(";
        }
        else if(client==2){
            return cpu==1?"YOU WON!!!":"CPU WON :(";
        }
        else{
            return cpu==2?"YOU WON!!!":"CPU WON :(";
        }
    }

}
