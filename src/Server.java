
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Server extends VBox implements Runnable {

    ArrayList<Sprite> sprites = null;
    ArrayList<Client> clients = null;
    ServerSocket serverSocket = null;
    TextArea textAreaClients  = null;

    public void init() {
        sprites = new ArrayList<Sprite>();
        clients = new ArrayList<Client>();

        textAreaClients = new TextArea();
        textAreaClients.setEditable(false);
        textAreaClients.setPrefSize(200, 200);
        this.getChildren().add(textAreaClients);

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.getChildren().add(new Label("Server"));
    }

    public void showConnections() {
        Platform.runLater(() -> {
            textAreaClients.clear();
            for(Client client:clients){
                textAreaClients.appendText(client.toString());
            }
        });
    }

    @Override
    public void run() {
        while(true) {
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Waiting for client to connect");
                Client client = new Client(clientSocket, clients);
                clients.add(client);
                client.start();
                showConnections();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}