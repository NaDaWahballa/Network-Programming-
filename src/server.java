import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
    private static ServerSocket serverSocket;
    private static final int port = 1234;

    public static void main(String[] args) {
        System.out.println("Openning port........");

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Port Openning Successfuly");
        } catch (IOException var2) {
            System.out.println("Cannot Open Port");
        }

        while(true) {
            handelconnect();
        }
    }

    private static void handelconnect() {
        Socket socket = null;

        try {
            socket = serverSocket.accept();
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String username = in.nextLine();
            String pass = in.nextLine();
            while(!username.equals("close") && !pass.equals("close")) {
                System.out.println("User Name : " + username);
                System.out.println("Password : " + pass);
                if (checkuser(username, pass)) {
                    out.println("SUCCESSFUL LOGIN");
                    System.out.println("SUCCESSFUL LOGIN");
                    int num_msg = 0;

                    for(String msg = in.nextLine(); !msg.equals("***STOP***"); msg = in.nextLine()) {
                        System.out.println("Message received");
                        ++num_msg;
                        System.out.println("Message " + num_msg + " : " + msg);
                    }
                } else {
                    System.out.println("OPPS :( >>INVALID USERNAME OR PASSWORDS");
                    out.println("OPPS :( >>INVALID USERNAME OR PASSWORDS");
                    System.out.println("\n:) Try Again\n");
                    username = in.nextLine();
                    pass = in.nextLine();
                }
            }
        } catch (IOException var16) {
            var16.printStackTrace();
        } finally {
            try {
                System.out.println("Conecction  is closing......");
                socket.close();
                System.exit(1);
            } catch (IOException var15) {
                System.out.println("Cannot Close Connection");
                System.exit(1);
            }

        }

    }

    private static boolean checkuser(String username, String pass) {

            if (username.equals("nada")&&pass.equals("123")) {
                return true;
            }
            else return false;
    }
}
