import java.io.*;
import java.net.*;

public class Server {
    private static final String servicePort = "1234";

    public static void main(String[] args) {
        new Server().start();
    }

    public void start() {
        System.out.println("Server se otvara...");
        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(servicePort))) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                    System.out.println("Prihvacen klijent s adresom: " + clientSocket.getInetAddress());

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        String[] numbers = inputLine.split(" ");
                        int num1 = Integer.parseInt(numbers[0]);
                        int num2 = Integer.parseInt(numbers[1]);
                        int sum = num1 + num2;
                        System.out.println("Poslato od klijenta: " + inputLine);
                        out.write("suma " + sum);
                        out.newLine();
                        out.flush();
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught when trying to listen on port " + servicePort + " or listening for a connection");
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + servicePort);
            System.out.println(e.getMessage());
        }
    }
}
