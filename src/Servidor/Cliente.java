package Servidor;

import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) {
        String server = "10.130.129.103";
        int porta = 12345;
        String mensagem = "MTk5OQ==";
        
        Socket socket = null;
        PrintWriter writer = null;
        BufferedReader reader = null;

        try {
            socket = new Socket(server, porta);

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            writer.println(mensagem);

            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            String resposta = reader.readLine();

            try (PrintWriter out = new PrintWriter(new FileWriter("resposta.txt"))) {
                out.println(resposta);
                System.out.println(resposta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
                if (socket != null) socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}