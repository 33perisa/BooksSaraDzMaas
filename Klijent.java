import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Klijent {
    private static final String servicePort = "1234";

    public static void main(String[] args) {
        new Klijent().start();
    }

    public void start() {
        System.out.println("Client pocinje...");
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("unesi prvi broj");
            int num1 = Integer.parseInt(userInput.readLine());
            System.out.println("unesi drugi broj");
            int num2 = Integer.parseInt(userInput.readLine());

            Socket socket = new Socket("localhost", Integer.parseInt(servicePort));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(num1 + " " + num2);

            String odgovor = in.readLine();
            if (odgovor != null) {
                System.out.println("Poslato od servera: " + odgovor);
            } else {
                System.out.println("server zatvoren");
            }
            out.flush();
            out.close();
            in.close();
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection.");
            e.printStackTrace();
        }
    }
}
