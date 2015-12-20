package server_chat_simple;

import java.io.*;
import java.net.*;

public class SERVER_CHAT_SIMPLE {

    public static void main(String[] args) throws IOException {
        System.out.println(ChatServer.getInstance().getNameServer());
//        ServerSocket serverSocket = new ServerSocket(6789);
//        Socket accept = serverSocket.accept();
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
//        DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());
//        dataOutputStream.writeBytes("Nhập vào tên của bạn\n");
//        while (true) {
//            if (accept.isConnected()) {
//                String lineread = bufferedReader.readLine();
//                if (lineread != null) {
//                    dataOutputStream.writeBytes("Xin Chào " + lineread + "\n");
//                }
//            } else {
//                dataOutputStream.close();
//                bufferedReader.close();
//                accept.close();
//            }
//        }
    }

}
