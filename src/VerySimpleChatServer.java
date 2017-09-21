import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.util.ArrayList;
import java.net.*;
import java.util.Iterator;

/**
 * Created by cherginets-sv on 20.09.2017.
 */
public class VerySimpleChatServer {

    ArrayList clientOutputStreams;

    public class ClientHandler implements Runnable {
        BufferedReader reader;
        Socket sock;

        public ClientHandler(Socket clientSocket) {
            try {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            } catch (Exception e) {e.printStackTrace();}
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("Server read " + message);
                    tellEveryone(message);
                }
            } catch (IOException e) {e.printStackTrace();}

        }
    }

    public static void main(String[] args) {
        VerySimpleChatServer server = new VerySimpleChatServer();
        server.go();
    }

    public void go() {
        clientOutputStreams = new ArrayList();
        try {
            ServerSocket serverSock = new ServerSocket(5000);

            while (true) {
                Socket clientSocket = serverSock.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("got a connection");
            }
        } catch (Exception e) {e.printStackTrace();}

    }

    public void tellEveryone(String message) { //ok

        Iterator it = clientOutputStreams.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                System.out.println("sent " + message + "to" + writer);
                writer.flush();
            } catch (Exception e) {e.printStackTrace();}
        }
    }
}
