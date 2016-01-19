/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d88.core.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author dung.nt
 */
interface D88ServerConnectionDelegate {

    public void ServerConnectionDidStart();

    public void ServerConnectionStartFailure(String message);

    public void ServerConnectionDidEnd();

    public void ServerConnectionDidEndFailure(String message);

    public void ServerConnectionClientDidConnect(D88ClientConnection client);

    public void ServerConnectionClientConnectFailure(String message);

}

public final class D88ServerConnection implements Runnable {

    private D88ServerConnectionDelegate delegate = null;
    private ServerSocket server = null;
    private boolean isOnline = true;
    private int clientTimeout = 0;

    public D88ServerConnection(D88ServerConnectionDelegate _delegate, int SERVER_PORT) {
        this.delegate = _delegate;
        if (server == null) {
            try {
                server = new ServerSocket(SERVER_PORT);
                if (this.delegate != null) {
                    this.delegate.ServerConnectionDidStart();
                }
            } catch (IOException ex) {
                if (this.delegate != null) {
                    this.delegate.ServerConnectionStartFailure(ex.getMessage());
                }
            }
        }
    }

    public D88ServerConnection(D88ServerConnectionDelegate _delegate, int SERVER_PORT, int timeout) {
        this.clientTimeout = timeout;
        this.delegate = _delegate;
        if (server == null) {
            try {
                server = new ServerSocket(SERVER_PORT);
                if (this.delegate != null) {
                    this.delegate.ServerConnectionDidStart();
                }
            } catch (IOException ex) {
                if (this.delegate != null) {
                    this.delegate.ServerConnectionStartFailure(ex.getMessage());
                }
            }
        }
    }

    @Override
    public void run() {
        while (this.isOnline && !this.server.isClosed()) {
            try {
                Socket accept = this.server.accept();

                D88ClientConnection d88ClientConnection;
                if (this.clientTimeout == 0) {
                    d88ClientConnection = new D88ClientConnection(accept);
                } else {
                    d88ClientConnection = new D88ClientConnection(accept, this.clientTimeout);
                }
                if (delegate != null) {
                    this.delegate.ServerConnectionClientDidConnect(d88ClientConnection);
                }
            } catch (IOException ex) {
                if (this.delegate != null) {
                    this.delegate.ServerConnectionClientConnectFailure(ex.getMessage());
                }
            }
        }
        if (!isOnline || this.server.isClosed()) {
            try {
                this.server.close();
                if (delegate != null) {
                    this.delegate.ServerConnectionDidEnd();
                }
            } catch (IOException ex) {
                if (delegate != null) {
                    this.delegate.ServerConnectionDidEndFailure(ex.getMessage());
                }
            }
        }
    }

}
