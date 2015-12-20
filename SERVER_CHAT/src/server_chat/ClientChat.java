
package server_chat;

import java.io.*;
import java.net.*;

public class ClientChat {
    public static void main(String[] args) throws Exception{
        try (Socket socket = new Socket("localhost", 6789)) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeBytes("2\n");
            dataOutputStream.writeBytes("3\n");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String readLineTotal = bufferedReader.readLine();
            System.out.println(readLineTotal);
            dataOutputStream.close();
            bufferedReader.close();
        }
    }
}
