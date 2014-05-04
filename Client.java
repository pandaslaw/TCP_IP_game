package Client.src;
import Game.src.Game.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public String recieveMessage(BufferedReader br) throws IOException {
        String str;
        if(br.readLine()!=null) {
            str=br.readLine();
            return str;
        } else return null;
    }

    public static void main(String[] args) throws InterruptedException {
        Socket socket=null;
        Scanner scanner=new Scanner(System.in);

        try {
            socket=new Socket(InetAddress.getLocalHost(), 8031);
            PrintStream ps=new PrintStream(socket.getOutputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str=br.readLine();
            System.out.println(str+"\n");
            System.out.println("___You're player #1___\n");

            Game a=new Game();
            boolean playGame=true;
            int i=0;
            String word;
            String[] wordsPlayer1=new String[20];

            a.drawField();
            while(playGame && i<10) {

                System.out.print("\nEnter position(x, y) and letter or '--exit': \n>");
                str=scanner.nextLine();

                if(str.equals("--exit")) {
                    playGame=false;
                    ps.println("--exit");
                } else {
                    ps.println(str);
                    ps.flush();

                    System.out.print("Type your new word:\n>");
                    word=scanner.nextLine();
                    wordsPlayer1[i]=word;

                    System.out.print("Your step was: ");
                    a.inputLetter(str);
                    a.drawField();
///////////////////////////////////////////////////////////////////////////
                    Client client=new Client();

                    while(str==null) {
                        Thread.sleep(1000);
                        str=client.recieveMessage(br);
                    }

                    str=br.readLine();

                    if(str.equals("--exit"))
                        playGame=false;
                    else {
                        System.out.print("Player's #2 step was: ");
                        a.inputLetter(str);
                        a.drawField();
                    }
                }
                ++i;
            }
            int scorePlayer1=a.countPoints(wordsPlayer1);
            System.out.println("Your score is: "+ scorePlayer1);

            int scorePlayer2= Integer.parseInt(br.readLine());
            String resultMessage=a.compareScore(scorePlayer1,scorePlayer2);
            ps.print(resultMessage);
            System.out.println(resultMessage);

            ps.flush();
            ps.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}