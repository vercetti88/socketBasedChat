import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class ConferenceMessageDistributor {
    ArrayList<OutputStream> clients = new ArrayList<>();
    public void addOutputStream(OutputStream outputStream) {
        clients.add(outputStream);
    }

    public void sendMessages(String string){
        for(OutputStream outputStream : clients){
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write(string);
            printWriter.flush();
        }
    }
}
