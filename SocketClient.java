
import com.company.Main;

import java.io.DataInputStream;
import java.io.*;
import java.io.IOException;
import java.net.*;
import java.net.UnknownHostException;
import java.util.Scanner;
public class SocketClient {

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;
    private static boolean newPlayer=true;

    public SocketClient (String address, int port) throws IOException {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            input = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            if(newPlayer){
                Scanner sc= new Scanner(System.in);
                System.out.println("ENTER YOUR NAME");
                String temp= sc.nextLine();
                out.writeUTF(temp);
                System.out.println(input.readUTF());
            }

        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }
        System.out.println("HERE");
        String line = "";
        int count=0;
        while(!line.equals("Over")){
            System.out.println("Your Turn! Rock =1, Paper=2, Scissors =3");
            Scanner sc= new Scanner(System.in);
            line= sc.nextLine();
            if(!line.equals("1") && !line.equals("2") && !line.equals("3")){
                System.out.println("Invalid Input");
                System.out.println("GAME OVER!!!");
                break;
            }
            out.writeUTF(line);
            System.out.println(input.readUTF());
        }

    }

    public static void main(String[] args) throws IOException {
        SocketClient client = new SocketClient("192.168.0.100", 8000);
    }
}

