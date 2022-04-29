import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Thread {
    private Socket socket;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;
    private ArrayList<Client> allCLients;
    
    public Client(Socket clientSocket, ArrayList<Client> clients) {
        this.socket = clientSocket;
        try {
            this.oos = new ObjectOutputStream(this.socket.getOutputStream());
            this.ois = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        this.allCLients = clients;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("Client running");
        }
    }

    @Override
    public String toString() {
        return "Client: " + this.socket.getInetAddress().getHostAddress() + ":" + this.socket.getPort() + "\n";
    }
}