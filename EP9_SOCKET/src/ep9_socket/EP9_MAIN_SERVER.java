/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguyendung
 */
public class EP9_MAIN_SERVER implements Runnable {

    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private DataInputStream dIn = null;
    private boolean online = true;

    EP9_MAIN_SERVER(Socket accept) throws IOException {
        System.out.println("Create New Socket Client");
        socket = accept;
        out = new PrintWriter(socket.getOutputStream(), true);
        //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        dIn = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {

            while (online) {
                String inputLine = in.readLine();
                int length = dIn.readInt();
                System.out.println("length "+length);
                if (inputLine != null) {
                    switch (inputLine) {
                        case "GG":
                            this.out.println("COUNT " + EP9_APP_SHARE_DATASOURCE.getInstance().CountClient());
                            break;
                        case "MESSAGE":
                            this.sendMessageToAllUser();
                            break;
                        case "END":
                            online = false;
                            break;
                        default:
                            this.out.println("HIHI " + inputLine);
                            break;
                    }
                } else {
                    if (length > 0) {
                        byte[] message = new byte[length];
                        dIn.readFully(message, 0, message.length);
                        this.out.println("HIHI ");
                    }
                }

            }

            if (!online) {
                try {
                    in.close();
                    out.close();
                    socket.close();
                } catch (Exception e) {

                } finally {
                    System.out.println("End Thread " + +EP9_APP_SHARE_DATASOURCE.getInstance().CountClient());
                }

            }

        } catch (IOException ex) {
            System.out.println("End Thread " + +EP9_APP_SHARE_DATASOURCE.getInstance().CountClient());
        }
    }

    public void sendMessage(String message) {
        this.out.println(message);
    }

    public void stop() {

    }

    public void sendMessageToAllUser() {

    }
}
