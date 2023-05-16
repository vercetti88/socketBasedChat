import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConferenceServer {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Abdulaziz\\Desktop\\Test.txt",true);

        ServerSocket serverSocket = new ServerSocket(4444);
        ConferenceMessageDistributor conferenceMessageDistributor = new ConferenceMessageDistributor();
        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("CONNECTED //////////");
            ConferenceMessageAcceptor conferenceMessageAcceptor = new ConferenceMessageAcceptor(socket,
                    conferenceMessageDistributor,
                    fileOutputStream);
            conferenceMessageAcceptor.start();
            conferenceMessageDistributor.addOutputStream(socket.getOutputStream());
            System.out.println("Connection accepted"+ socket.toString());
        }
    }
}
