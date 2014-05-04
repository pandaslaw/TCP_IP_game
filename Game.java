package Game.src.Game;

public class Game {
    public char field[][];

    public Game() {
        this.field =new char [5][5];
        for(int i=0; i<5; ++i) {
            for(int j=0; j<5; ++j) {
                this.field[i][j]='*';
            }
        }
    }

    public void setStartWord() {
        field[2][0]='T';
        field[2][1]='A';
        field[2][2]='B';
        field[2][3]='L';
        field[2][4]='E';
    }

    public void changeField(int y, int x, char letter) {
        if(x==2 && (y==0 || y==1 || y==2 || y==3 || y==4))
            System.out.println("miss. you hit into third cell. lol :)");
        field[x][y]=letter;
    }

    public void drawField() {
        setStartWord();
        int n=5;

        for(int i=0; i<n; ++i) {
            for(int j=0; j<n; ++j) {
                System.out.print(field[i][j] + " ");
            }
            System.out.print("\n");
        }

    }
    public void inputLetter(String str) {
        String []strArray  = str.split(" ");

        char[] xAr  = strArray[0].toCharArray();
        char[] yAr  = strArray[1].toCharArray();
        char[] charArray  = strArray[2].toCharArray();


        char x1 = xAr[0];
        char y1 = yAr[0];
        char symbol=charArray[0];
        int x = Character.getNumericValue(x1);
        int y = Character.getNumericValue(y1);

        System.out.print("x="+ x+ " y=" +y + " symbol=" +symbol + "\n" );

        changeField(x-1, y-1, symbol);

    }

    public int countPoints(String[] array) {
        int sum=0;
        int i=0;
        while(array[i]!=null) {
            char[] charAr  = array[i].toCharArray();
            for(int j=0; j<charAr.length; ++j)
                ++sum;
            ++i;
        }
        return sum;

    }

    public String compareScore(int a, int b) {
        String str;
        if(a>b)
            str="PLAYER #1 WON";
        else if(a<b)
            str="PLAYER #2 WON";
        else
            str="FRIENDSHIP WON";
        return str;

    }
}