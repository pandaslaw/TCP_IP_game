package Server.src.Server;
import Game.src.Game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {
    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        Socket socket=null;
        try {
            ServerSocket server = new ServerSocket(8031);
            socket=server.accept();
            PrintStream ps=new PrintStream(socket.getOutputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String str="Let's start the game!";
            System.out.println(str);
            System.out.println("\n___You're player #2___");
            ps.println(str);
            ps.flush();

            Game a=new Game();
            boolean playGame=true;
            int i=0;
            String word;
            String[] wordsPlayer2=new String[20];

            a.drawField();
            while(playGame && i<10) {
                str=br.readLine();
                ps.flush();

                if(str.equals("--exit"))
                    playGame=false;
                else {
                    System.out.print("Player's #1 step was: ");
                    a.inputLetter(str);
/////////////////////////////////////////////////////////////
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
                        wordsPlayer2[i]=word;

                        System.out.print("Your step was: ");
                        a.inputLetter(str);
                        a.drawField();
                    }
                }
                ++i;
            }
            int scorePlayer2=a.countPoints(wordsPlayer2);
            System.out.println("Your score is: "+ scorePlayer2);

            ps.println(scorePlayer2);

            System.out.println(br.readLine());

            ps.flush();
            ps.close();
        } catch (IOException e) {
            System.out.println("error"+e);

        }
        finally {
            if(socket!=null)
                socket.close();
        }
    }
}