/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep6_networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class ChatServer {

   
    private static final int PORT = 9001;

    private static HashSet<String> names = new HashSet<String>();

   
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

   
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }
    
    public static int currentConnection = 0;

    private static class Handler extends Thread {
        private String name;
        private final Socket socket;
        private BufferedReader in;
        private PrintWriter out;

       
        public Handler(Socket socket) {
            this.socket = socket;
        }

        
        @Override
        public void run() {
            try {
                currentConnection += 1;
                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!names.contains(name)) {
                            names.add(name);
                            break;
                        }
                    }
                }
                out.println("CONNECT" + currentConnection);
                if(in.readLine().startsWith("DUNGNT")){
                    System.out.println("Done");
                }
                out.println("NAMEACCEPTED");
                writers.add(out);


                while (true) {
                    String input = in.readLine();
                    if(input.startsWith("DUNGNT")){
                        System.out.println("Done");
                    }
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " + name + ": " + input);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                // This client is going down!  Remove its name and its print
                // writer from the sets, and close its socket.
                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
