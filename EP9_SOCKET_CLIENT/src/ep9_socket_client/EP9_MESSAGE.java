/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket_client;
import java.io.Serializable;
/**
 *
 * @author dungnt
 */
public class EP9_MESSAGE implements Serializable{
    private static final long serialVersionUID = 1L;
    public String type, sender, content, recipient;

    public EP9_MESSAGE(String type, String sender, String content, String recipient) {
        this.type = type;
        this.sender = sender;
        this.content = content;
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "{type='" + type + "', sender='" + sender + "', content='" + content + "', recipient='" + recipient + "'}";
    }
}
