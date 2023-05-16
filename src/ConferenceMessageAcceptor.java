import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ConferenceMessageAcceptor extends Thread {
    Socket socket;
    ConferenceMessageDistributor conferenceMessageDistributor;
    FileOutputStream fileOutputStream;
    public ConferenceMessageAcceptor(Socket socket,
                                     ConferenceMessageDistributor conferenceMessageDistributor,
                                     FileOutputStream fileOutputStream) throws IOException {
        this.socket = socket;
        this.conferenceMessageDistributor = conferenceMessageDistributor;
        this.fileOutputStream = fileOutputStream;
    }
    public void run(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        while(true){

            String line = bufferedReader.readLine();
            System.out.println(Thread.currentThread().getId()+": Received command "+ line);
            conferenceMessageDistributor.sendMessages(line+"\n");
            String serversReply = stdIn.readLine();
            String dataForFile = line + "\n" + serversReply;
            fileOutputStream.write(dataForFile.getBytes());
            if(!serversReply.isEmpty()){
                conferenceMessageDistributor.sendMessages("User number " + Thread.currentThread().getId() + ":" + serversReply+"\n");
                System.out.println("User number " + Thread.currentThread().getId() + ":" + serversReply);
            }

            if(line.equals("exit")){
                break;
            }
        }
        bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Smth went wrong");
        }
    }
}
