import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConferenceClient {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 4444);

        PrintWriter printWriter = new PrintWriter((clientSocket.getOutputStream()),true);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String line = "Abdulaziz: "+stdIn.readLine();
            printWriter.write(line +"\n");
            printWriter.flush();
            String reply = bufferedReader.readLine();
            System.out.println("reply" +  " "+reply);
            String serversMessage = bufferedReader.readLine();
            if(!serversMessage.isEmpty()){
                System.out.println(serversMessage);
            }
            if(line.equals("exit")){
                break;
            }
        }
        clientSocket.close();
        bufferedReader.close();
        stdIn.close();
    }
}

