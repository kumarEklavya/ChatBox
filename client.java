import java.net.*;
import java.io.*;


public class client {

    Socket socket;

    BufferedReader br;
    PrintWriter out;

     
    public client() 
    {
        try{

            System.out.println("Sending request to server");
            socket = new Socket("127.0.0.1",7777);
            System.out.println("Connection done");



            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();







        }catch(Exception e){

            e.printStackTrace();
        }

      


    }

    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("reader started...");

            try{

            while (true)
              
                    {
                        String msg = br.readLine();

                        if (msg.equals("Exit")) {
                            System.out.println("Server terminated the chat");
                            socket.close();
                            break;
                        }
                        System.out.println("Server : " + msg);
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
              

        };
        new Thread(r1).start();

    }

    public void startWriting() {
        Runnable r2 = () -> {

            try{
            while( true)
            {
             

                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();

                    if(content.equals("exit")){
                        socket.close();
                        break;
                    }

               
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        };
        new Thread(r2).start();


    }

    public static void main(String[] args)
    {
        System.out.println("This is client");
        new client(); 
    }
}
