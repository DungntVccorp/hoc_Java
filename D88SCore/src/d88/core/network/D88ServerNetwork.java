/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d88.core.network;

import java.util.concurrent.ExecutorService;

/**
 *
 * @author dung.nt
 */
public class D88ServerNetwork implements D88ServerConnectionDelegate, D88ClientConnectionDelegate {

    public interface D88NetworkDelegate {

        public void onD88NetworkError(String message);

        public void ServerConnectionDidStart();

        public void ServerConnectionStartFailure(String message);

        public void ServerConnectionDidEnd();

        public void ServerConnectionDidEndFailure(String message);

        public void ServerConnectionClientDidConnect(D88ClientConnection client);

        public void ServerConnectionClientConnectFailure(String message);

        public void clientdidReceiveMessage(byte[] message, D88ClientConnection formClient);

        public void clientConnectionLost(D88ClientConnection client);
        
        public void clientConnectionTimeout(D88ClientConnection client);

        public void clientdidDisconnect(D88ClientConnection client);

        public void clientDidSendMessage(byte[] message, D88ClientConnection client);

        public void clientdidSendMessageFailer(D88ClientConnection client, String message);

    }

    private D88ServerConnection ServerConnection = null;
    private ExecutorService exxecutors = null;
    private D88NetworkDelegate delegate = null;

    public D88ServerNetwork(int SERVER_PORT, ExecutorService exxecutors, D88NetworkDelegate delegate) {
        this.exxecutors = exxecutors;
        this.delegate = delegate;
        this.ServerConnection = new D88ServerConnection(this, SERVER_PORT);
        if (this.exxecutors != null) {
            this.exxecutors.execute(this.ServerConnection);
        } else {
            if (this.delegate != null) {
                this.delegate.onD88NetworkError("ExecutorService not null");
            }
        }
    }

    // D88ServerConnectionDelegate
    @Override
    public void ServerConnectionDidStart() {
        if (this.delegate != null) {
            this.delegate.ServerConnectionDidStart();
        }
    }

    @Override
    public void ServerConnectionStartFailure(String message) {
        if (this.delegate != null) {
            this.delegate.ServerConnectionStartFailure(message);
        }
    }

    @Override
    public void ServerConnectionDidEnd() {
        if (this.delegate != null) {
            this.delegate.ServerConnectionDidEnd();
        }
    }

    @Override
    public void ServerConnectionDidEndFailure(String message) {
        if (this.delegate != null) {
            this.delegate.ServerConnectionDidEndFailure(message);
        }
    }

    @Override
    public void ServerConnectionClientDidConnect(D88ClientConnection client) {
        if (this.delegate != null) {
            this.delegate.ServerConnectionClientDidConnect(client);
        }
        if (this.exxecutors != null) {
            client.setDelegate(this);
            this.exxecutors.execute(client);
        }
    }

    @Override
    public void ServerConnectionClientConnectFailure(String message) {
        if (this.delegate != null) {
            this.delegate.ServerConnectionClientConnectFailure(message);
        }
    }

    //D88ClientConnectionDelegate
    @Override
    public void clientdidReceiveMessage(byte[] message, D88ClientConnection formClient) {
        if (this.delegate != null) {
            this.delegate.clientdidReceiveMessage(message, formClient);
        }
    }

    @Override
    public void clientConnectionLost(D88ClientConnection client) {
        if (this.delegate != null) {
            this.delegate.clientConnectionLost(client);
        }
    }

    @Override
    public void clientConnectionTimeout(D88ClientConnection client) {
        if (this.delegate != null) {
            this.delegate.clientConnectionTimeout(client);
        }
    }
    
    @Override
    public void clientdidDisconnect(D88ClientConnection client) {
        if (this.delegate != null) {
            this.delegate.clientdidDisconnect(client);
        }
    }

    @Override
    public void clientDidSendMessage(byte[] message, D88ClientConnection client) {
        if (this.delegate != null) {
            this.delegate.clientDidSendMessage(message, client);
        }
    }

    @Override
    public void clientdidSendMessageFailer(D88ClientConnection client, String message) {
        if (this.delegate != null) {
            this.delegate.clientdidSendMessageFailer(client, message);
        }
    }

}
