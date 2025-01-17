import java.io.*;
import java.util.ArrayList;
public class chessGame{

    //Setting up the chess board
    static String ChessBoard[][]=new String[8][8];
    static BufferedReader globalBR = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<String> toShowBlack = new ArrayList<>();
    static ArrayList<String> toShowWhite = new ArrayList<>();
    // for(int i=0;i<8;i++){
    //     for(int j=0;j<8;j++){
    //         ChessBoard[i][j]=i+""+j;
    //     }
    // }
    chessGame(){
        for(int i=0;i<8;i++){
            toShowWhite.add("WP"+(i+1));
            toShowBlack.add("BP"+(i+1));
        }
        for(int i=1;i<=2;i++){
            toShowWhite.add("WC"+(i));
            toShowWhite.add("WH"+(i));
            toShowWhite.add("WE"+(i));
            toShowBlack.add("BC"+(i));
            toShowBlack.add("BH"+(i));
            toShowBlack.add("BE"+(i));
        }
        toShowWhite.add("WK");
        toShowWhite.add("WQ");
        toShowBlack.add("BK");
        toShowBlack.add("BQ");
    }

    //BLACK PAWN
    static class BlackPawn{
        // int isEliminated = 0;
        int currentPositionX,currentPositionY,number;
        BlackPawn(int positionX,int positionY,int n){
            currentPositionX = positionX;
            currentPositionY = positionY;
            number = n;
            ChessBoard[currentPositionX][currentPositionY]="BP"+number;
        }
        // void up(){
        //     if(currentPositionX==0)
        //     {
        //         System.out.println("Can't move up");
        //     }
        //     else{
        //         ChessBoard[currentPositionX][currentPositionY]=(ChessBoard[currentPositionX][currentPositionY]=="-")?"-":"";
        //         currentPositionX = currentPositionX-1;
        //         ChessBoard[currentPositionX][currentPositionY]="BP"+number;
        //     }
        // }
        void down(){
            if(currentPositionX==7)
            {
                System.out.println("Can't move down");
            }
            else{
                ChessBoard[currentPositionX][currentPositionY]=(ChessBoard[currentPositionX][currentPositionY]=="-")?"-":"";
                currentPositionX = currentPositionX+1;
                ChessBoard[currentPositionX][currentPositionY]="BP"+number;
            }
        }
        //To show all the valid moves
        void showValidMoves(ArrayList<String> players,ArrayList<String> oppositionTeam,
        ArrayList<Object>oppositionPlayers){
            if(currentPositionX!=0 || currentPositionY!=7){
                if(!players.contains(ChessBoard[currentPositionX+1][currentPositionY])
                    ||!oppositionTeam.contains(ChessBoard[currentPositionX+1][currentPositionY])
                ){
                    System.out.println("1 : DOWN");
                }
                // else if(!players.contains(ChessBoard[currentPositionX-1][currentPositionY])){
                //     System.out.println("2 : UP");
                // }
                if(currentPositionY!=7 || currentPositionY!=0){
                    if(oppositionTeam.contains(ChessBoard[currentPositionX+1][currentPositionY-1])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX+1][currentPositionY+1])
                    ){
                        System.out.println("3 : ATTACK");
                    }
                }
            }
            try{
                System.out.print("Your move : ");
                int move = Integer.parseInt(globalBR.readLine());
                if(move==1){
                    this.down();
                    
                }
                // else if(move==2){
                //     this.up();
                // }
                else if(move==3){
                    this.attack(oppositionTeam,oppositionPlayers);
                }
                else{
                    System.out.println("Sorry there is no such move available.");
                }
                display();
            }catch(IOException e){}
        }
        void attack(ArrayList<String> oTeam, ArrayList<Object> oPlayers){
            if(oTeam.contains(ChessBoard[currentPositionX+1][currentPositionY-1]))
            {
                System.out.println("1 : "+ChessBoard[currentPositionX+1][currentPositionY-1]);
            }
            if(oTeam.contains(ChessBoard[currentPositionX+1][currentPositionY+1]))
            {
                System.out.println("2 : "+ChessBoard[currentPositionX+1][currentPositionY+1]);
            }
            try{
                System.out.print("Select which player to KILL/ATTACK/ELIMINATE : ");
                int select = Integer.parseInt(globalBR.readLine());
                if(select == 1){
                    System.out.println("White sides "+ChessBoard[currentPositionX+1][currentPositionY-1]+" is eliminated!!");
                    toShowWhite.remove(ChessBoard[currentPositionX+1][currentPositionY-1]);
                    // oPlayers.add(oTeam.indexOf(ChessBoard[currentPositionX][currentPositionY])-1, null);
                    ChessBoard[currentPositionX][currentPositionY]=(ChessBoard[currentPositionX][currentPositionY]=="-")?"-":"";
                    // oTeam.remove(ChessBoard[currentPositionX][currentPositionY]);
                    // oPlayers.remove(oPlayers.get(number));
                    // oPlayers.remove(oTeam.indexOf(ChessBoard[currentPositionX][currentPositionY])-1);
                    currentPositionX = currentPositionX+1;
                    currentPositionY = currentPositionY-1;
                    ChessBoard[currentPositionX][currentPositionY]="BP"+number;
                }
                else if(select == 2){
                    System.out.println("White sides "+ChessBoard[currentPositionX+1][currentPositionY+1]+" is eliminated!!");
                    toShowWhite.remove(ChessBoard[currentPositionX+1][currentPositionY+1]);
                    // oPlayers.add(oTeam.indexOf(ChessBoard[currentPositionX][currentPositionY])-1, null);
                    ChessBoard[currentPositionX][currentPositionY]=(ChessBoard[currentPositionX][currentPositionY]=="-")?"-":"";
                    // oTeam.remove(ChessBoard[currentPositionX][currentPositionY]);
                    // oPlayers.remove(oTeam.indexOf(ChessBoard[currentPositionX][currentPositionY])-1);
                    currentPositionX = currentPositionX+1;
                    currentPositionY = currentPositionY+1;
                    ChessBoard[currentPositionX][currentPositionY]="BP"+number;
                }
            }catch(IOException e){}
        }
    }
    //WHITE PAWN
    static class WhitePawn{
        // int isEliminated = 0;
        int currentPositionX,currentPositionY,number;
        WhitePawn(int positionX,int positionY,int n){
            currentPositionX = positionX;
            currentPositionY = positionY;
            number = n;
            ChessBoard[currentPositionX][currentPositionY]="WP"+number;
        }
        void up(){
            if(currentPositionX==0)
            {
                System.out.println("Can't move up");
            }
            else{
                // currentPositionY = currentPositionY+1;
                //Making the original place as it is
                ChessBoard[currentPositionX][currentPositionY]=(ChessBoard[currentPositionX][currentPositionY]=="-")?"-":"";
                //Now making the changes to other places.
                currentPositionX = currentPositionX-1;
                ChessBoard[currentPositionX][currentPositionY]="WP"+number;
            }
        }
        // void down(){
        //     if(currentPositionX==7)
        //     {
        //         System.out.println("Can't move down");
        //     }
        //     else{
        //         ChessBoard[currentPositionX][currentPositionY]=(ChessBoard[currentPositionX][currentPositionY]=="-")?"-":"";
        //         currentPositionX = currentPositionX+1;
        //         ChessBoard[currentPositionX][currentPositionY]="WP"+number;
        //     }
        // }
        void showValidMoves(ArrayList<String> wPlayers,ArrayList<String> oppositionTeam,
        ArrayList<Object>oppositionPlayers){
            if(currentPositionX!=0 || currentPositionY!=7){
                if(!wPlayers.contains(ChessBoard[currentPositionX-1][currentPositionY])
                ||!oppositionTeam.contains(ChessBoard[currentPositionX-1][currentPositionY])){
                    System.out.println("2 : UP");
                }
                if(oppositionTeam.contains(ChessBoard[currentPositionX-1][currentPositionY-1])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX-1][currentPositionY+1])
                ){
                    System.out.println("3 : ATTACK");
                }
            }
            try{
                System.out.print("Your move : ");
                int move = Integer.parseInt(globalBR.readLine());
                // if(move==1){
                //     this.down();
                    
                // }
                if(move==2){
                    this.up();
                }
                else if(move==3){
                    this.attack(oppositionTeam,oppositionPlayers);
                }
                else{
                    System.out.println("Sorry there is no such move available.");
                }
                display();
            }catch(IOException e){}
        }
        void attack(ArrayList<String> oTeam, ArrayList<Object> oPlayers){
            if(oTeam.contains(ChessBoard[currentPositionX-1][currentPositionY-1]))
            {
                System.out.println("1 : "+ChessBoard[currentPositionX-1][currentPositionY-1]);
            }
            else if(oTeam.contains(ChessBoard[currentPositionX-1][currentPositionY+1]))
            {
                System.out.println("2 : "+ChessBoard[currentPositionX-1][currentPositionY+1]);
            }
            try{
                System.out.print("Select which player to KILL/ATTACK/ELIMINATE : ");
                int select = Integer.parseInt(globalBR.readLine());
                if(select == 1){
                    System.out.println("Black sides "+ChessBoard[currentPositionX-1][currentPositionY-1]+" is eliminated!!");
                    toShowBlack.remove(ChessBoard[currentPositionX-1][currentPositionY-1]);
                    // oPlayers.add(oTeam.indexOf(ChessBoard[currentPositionX][currentPositionY])-1, null);
                    ChessBoard[currentPositionX][currentPositionY]=(ChessBoard[currentPositionX][currentPositionY]=="-")?"-":"";
                    // oTeam.remove(ChessBoard[currentPositionX][currentPositionY]);
                    // oPlayers.remove(oTeam.indexOf(ChessBoard[currentPositionX][currentPositionY])-1);
                    // oPlayers.add(oTeam.indexOf(ChessBoard[currentPositionX][currentPositionY])-1, null);
                    currentPositionX = currentPositionX-1;
                    currentPositionY = currentPositionY-1;
                    ChessBoard[currentPositionX][currentPositionY]="WP"+number;
                }
                else if(select == 2){
                    System.out.println("White sides "+ChessBoard[currentPositionX-1][currentPositionY+1]+" is eliminated!!");
                    toShowBlack.remove(ChessBoard[currentPositionX-1][currentPositionY+1]);
                    // oPlayers.add(oTeam.indexOf(ChessBoard[currentPositionX][currentPositionY])-1, null);
                    ChessBoard[currentPositionX][currentPositionY]=(ChessBoard[currentPositionX][currentPositionY]=="-")?"-":"";
                    // oPlayers.update(oTeam.indexOf(ChessBoard[currentPositionX][currentPositionY])-1);
                    // oPlayers.add(oTeam.indexOf(ChessBoard[currentPositionX][currentPositionY])-1, null);
                    currentPositionX = currentPositionX-1;
                    currentPositionY = currentPositionY+1;
                    ChessBoard[currentPositionX][currentPositionY]="WP"+number;
                }
            }catch(IOException e){}
        }
    }

    //BLACK KING
    static class BlackKing{
        int currentPositionX,currentPositionY;
        BlackKing(int positionX,int positionY){
            currentPositionX = positionX;
            currentPositionY = positionY;
            ChessBoard[currentPositionX][currentPositionY]="BK";
        }
        void left(){}
        void right(){}
        void up(){}
        void down(){}
        void topLeft(){}
        void topRight(){}
        void bottomLeft(){}
        void bottomRight(){}
        void showValidMoves(){}
    }

    //WHITE KING
    static class WhiteKing{
        int currentPositionX,currentPositionY;
        WhiteKing(int positionX,int positionY){
            currentPositionX = positionX;
            currentPositionY = positionY;
            ChessBoard[currentPositionX][currentPositionY]="WK";
        }
        void left(){}
        void right(){}
        void up(){}
        void down(){}
        void topLeft(){}
        void topRight(){}
        void bottomLeft(){}
        void bottomRight(){}
        void showValidMoves(){
            if(currentPositionX!=7){
                //Check if no player : - i)either of opposition team 
                //                       ii) or my own team is blocking my way.
                if(!toShowWhite.contains(ChessBoard[currentPositionX+1][currentPositionY]) 
                && !toShowBlack.contains(ChessBoard[currentPositionX+1][currentPositionY])){
                    System.out.println("1:Down by 1 step");
                }
                if(currentPositionY!=7){
                    if(!toShowWhite.contains(ChessBoard[currentPositionX][currentPositionY+1]) 
                    && !toShowBlack.contains(ChessBoard[currentPositionX][currentPositionY+1])){
                        System.out.println("3:Right by 1 step");
                    }
                    if(!toShowWhite.contains(ChessBoard[currentPositionX+1][currentPositionY+1]) 
                    && !toShowBlack.contains(ChessBoard[currentPositionX+1][currentPositionY+1])){
                        System.out.println("5:Bottom Right by 1 step");
                    }
                }
                if(currentPositionY!=0){
                    if(!toShowWhite.contains(ChessBoard[currentPositionX][currentPositionY-1]) 
                    && !toShowBlack.contains(ChessBoard[currentPositionX][currentPositionY-1])){
                        System.out.println("4:Left by 1 step");
                    }
                    if(!toShowWhite.contains(ChessBoard[currentPositionX-1][currentPositionY-1]) 
                    && !toShowBlack.contains(ChessBoard[currentPositionX-1][currentPositionY-1])){
                        System.out.println("6:Bottom Left by 1 step");
                    }
                }
                //bottomLeft
                //bottomRight
            }
            if(currentPositionX!=0){
                if(!toShowWhite.contains(ChessBoard[currentPositionX-1][currentPositionY]) 
                && !toShowBlack.contains(ChessBoard[currentPositionX-1][currentPositionY])){
                    System.out.println("2:Up by 1 step");
                }
                if(currentPositionY!=7){
                    if(!toShowWhite.contains(ChessBoard[currentPositionX][currentPositionY+1]) 
                    && !toShowBlack.contains(ChessBoard[currentPositionX][currentPositionY+1])){
                        System.out.println("3:Right by 1 step");
                    }
                    if(!toShowWhite.contains(ChessBoard[currentPositionX-1][currentPositionY+1]) 
                    && !toShowBlack.contains(ChessBoard[currentPositionX-1][currentPositionY+1])){
                        System.out.println("6:Top Right by 1 step");
                    }
                }
                if(currentPositionY!=0){
                    if(!toShowWhite.contains(ChessBoard[currentPositionX][currentPositionY-1]) 
                    && !toShowBlack.contains(ChessBoard[currentPositionX][currentPositionY-1])){
                        System.out.println("4:Left by 1 step");
                    }
                    if(!toShowWhite.contains(ChessBoard[currentPositionX-1][currentPositionY-1]) 
                    && !toShowBlack.contains(ChessBoard[currentPositionX-1][currentPositionY-1])){
                        System.out.println("7:Top Left by 1 step");
                    }
                    
                }
                //topLeft
                //topRight
            }

        }
    }

    //BLACK QUEEN
    static class BlackQueen{
        int currentPositionX,currentPositionY;
        BlackQueen(int positionX,int positionY){
            currentPositionX = positionX;
            currentPositionY = positionY;
            ChessBoard[currentPositionX][currentPositionY]="BQ";
        }
        void left(){}
        void right(){}
        void up(){}
        void down(){}
        void topLeft(){}
        void topRight(){}
        void bottomLeft(){}
        void bottomRight(){}
        void showValidMoves(){}
    }
    //WHITE QUEEN
    static class WhiteQueen{
        int currentPositionX,currentPositionY;
        WhiteQueen(int positionX,int positionY){
            currentPositionX = positionX;
            currentPositionY = positionY;
            ChessBoard[currentPositionX][currentPositionY]="WQ";
        }
        void left(){}
        void right(){}
        void up(){}
        void down(){}
        void topLeft(){}
        void topRight(){}
        void bottomLeft(){}
        void bottomRight(){}
        void showValidMoves(){}
    }
    //BLACK HORSE
    static class BlackHorse{
        int currentPositionX,currentPositionY,number;
        BlackHorse(int positionX,int positionY,int n){
            currentPositionX = positionX;
            currentPositionY = positionY;
            number = n;
            ChessBoard[currentPositionX][currentPositionY]="BH"+number;
        }
        void up_up_left()
        {
            if((currentPositionX==0 || currentPositionX == 1) || currentPositionY == 0)
            {
                System.out.println("Can't move");
            }
            else
            {
                currentPositionX = currentPositionX + 2;
                currentPositionY = currentPositionY - 1;
                ChessBoard[currentPositionX][currentPositionY]="BH"+number;
            }
            
        }
        void up_up_right()
        {
            if((currentPositionX==0 || currentPositionX == 1) || currentPositionY == 7)
            {
                System.out.println("Can't move");
            }
            else
            {
                currentPositionX = currentPositionX + 2;
                currentPositionY = currentPositionY + 1;
                ChessBoard[currentPositionX][currentPositionY]="BH"+number;
            }
        }
        void down_down_left()
        {
            if((currentPositionX==6 || currentPositionX == 7) || currentPositionY == 0)
            {
                System.out.println("Can't move");
            }
            else
            {
                currentPositionX = currentPositionX - 2;
                currentPositionY = currentPositionY - 1;
                ChessBoard[currentPositionX][currentPositionY]="BH"+number;
            }
        }
        void down_down_right()
        {
            if((currentPositionX==6 || currentPositionX == 7) || currentPositionY == 7)
            {
                System.out.println("Can't move");
            }
            else
            {
                currentPositionX = currentPositionX - 2;
                currentPositionY = currentPositionY + 1;
                ChessBoard[currentPositionX][currentPositionY]="BH"+number;
            }
        }
        void showValidMoves(ArrayList<String> players,ArrayList<String> oppositionTeam,
        ArrayList<Object>oppositionPlayers)
        {
            if(currentPositionX!=0 || currentPositionY!=7){
                if(!players.contains(ChessBoard[currentPositionX+1][currentPositionY])
                    ||!oppositionTeam.contains(ChessBoard[currentPositionX+1][currentPositionY])
                ){
                    System.out.println("1 : down_down_right");
                    System.out.println("2 : down_down_left");
                    System.out.println("3 : up_up_right");
                    System.out.println("4 : up_up_left");
                }
                // else if(!players.contains(ChessBoard[currentPositionX-1][currentPositionY])){
                //     System.out.println("2 : UP");
                // }
                if(currentPositionY!=7 || currentPositionY!=0){
                    if(oppositionTeam.contains(ChessBoard[currentPositionX+2][currentPositionY+1])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX+2][currentPositionY-1])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX+1][currentPositionY+2])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX+1][currentPositionY-2])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX-1][currentPositionY+2])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX-1][currentPositionY-2])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX-2][currentPositionY-1])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX-2][currentPositionY+1])
                    ){
                        System.out.println("5 : ATTACK");
                    }
                }
            }
            try{
                System.out.print("Your move : ");
                int move = Integer.parseInt(globalBR.readLine());
                if(move==1){
                    this.down_down_right();
                    
                }
                else if(move==2){
                    this.down_down_left();
                }
                else if(move==3){
                    this.up_up_right();
                }
                else if(move==4){
                    this.up_up_left();
                }
                else if(move==5) {
                    // fill here for attack move
                }
                else{
                    System.out.println("Sorry there is no such move available.");
                }
                display();
            }catch(IOException e){}
        }

    }
    //WHITE HORSE
    static class WhiteHorse{
        int currentPositionX,currentPositionY,number;
        WhiteHorse(int positionX,int positionY,int n){
            currentPositionX = positionX;
            currentPositionY = positionY;
            number = n;
            ChessBoard[currentPositionX][currentPositionY]="WH"+number;
        }
        void up_up_left()
        {
            if((currentPositionX==0 || currentPositionX == 1) || currentPositionY == 0)
            {
                System.out.println("Can't move");
            }
            else
            {
                currentPositionX = currentPositionX + 2;
                currentPositionY = currentPositionY - 1;
                ChessBoard[currentPositionX][currentPositionY]="WH"+number;
            }
            
        }
        void up_up_right()
        {
            if((currentPositionX==0 || currentPositionX == 1) || currentPositionY == 7)
            {
                System.out.println("Can't move");
            }
            else
            {
                currentPositionX = currentPositionX + 2;
                currentPositionY = currentPositionY + 1;
                ChessBoard[currentPositionX][currentPositionY]="wH"+number;
            }
        }
        void down_down_left()
        {
            if((currentPositionX==6 || currentPositionX == 7) || currentPositionY == 0)
            {
                System.out.println("Can't move");
            }
            else
            {
                currentPositionX = currentPositionX - 2;
                currentPositionY = currentPositionY - 1;
                ChessBoard[currentPositionX][currentPositionY]="wH"+number;
            }
        }
        void down_down_right()
        {
            if((currentPositionX==6 || currentPositionX == 7) || currentPositionY == 7)
            {
                System.out.println("Can't move");
            }
            else
            {
                currentPositionX = currentPositionX - 2;
                currentPositionY = currentPositionY + 1;
                ChessBoard[currentPositionX][currentPositionY]="wH"+number;
            }
        }
        void showValidMoves(ArrayList<String> players,ArrayList<String> oppositionTeam,
        ArrayList<Object>oppositionPlayers)
        {
            if(currentPositionX!=0 || currentPositionY!=7){
                if(!players.contains(ChessBoard[currentPositionX+1][currentPositionY])
                    ||!oppositionTeam.contains(ChessBoard[currentPositionX+1][currentPositionY])
                ){
                    System.out.println("1 : down_down_right");
                    System.out.println("2 : down_down_left");
                    System.out.println("3 : up_up_right");
                    System.out.println("4 : up_up_left");
                }
                // else if(!players.contains(ChessBoard[currentPositionX-1][currentPositionY])){
                //     System.out.println("2 : UP");
                // }
                if(currentPositionY!=7 || currentPositionY!=0){
                    if(oppositionTeam.contains(ChessBoard[currentPositionX+2][currentPositionY+1])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX+2][currentPositionY-1])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX+1][currentPositionY+2])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX+1][currentPositionY-2])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX-1][currentPositionY+2])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX-1][currentPositionY-2])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX-2][currentPositionY-1])
                    ||oppositionTeam.contains(ChessBoard[currentPositionX-2][currentPositionY+1])
                    ){
                        System.out.println("5 : ATTACK");
                    }
                }
            }
            try{
                System.out.print("Your move : ");
                int move = Integer.parseInt(globalBR.readLine());
                if(move==1){
                    this.down_down_right();
                    
                }
                else if(move==2){
                    this.down_down_left();
                }
                else if(move==3){
                    this.up_up_right();
                }
                else if(move==4){
                    this.up_up_left();
                }
                else if(move==5) {
                    // fill here for attack move
                }
                else{
                    System.out.println("Sorry there is no such move available.");
                }
                display();
            }catch(IOException e){}
        }
    }
    //BLACK ELEPHANT
    static class BlackElephant{
        int currentPositionX,currentPositionY,number;
        BlackElephant(int positionX,int positionY,int n){
            currentPositionX = positionX;
            currentPositionY = positionY;
            number = n;
            ChessBoard[currentPositionX][currentPositionY]="BE"+number;
        }
        void up(int numberOfMoves)
        {
            if((currentPositionX==0))
            {
                System.out.println("Can't move");
            }
            else
            {
                currentPositionX = currentPositionX - numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="BE"+number;
            }
        }
        void down(int numberOfMoves)
        {
            if((currentPositionX==7))
            {
                System.out.println("Can't move");
            }
            else
            {
                currentPositionX = currentPositionX + numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="BE"+number;
            }
        }
        void left(int numberOfMoves)
        {
            if((currentPositionY==0))
            {
                System.out.println("Can't move");
            }
            else
            {
                currentPositionY = currentPositionY - numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="BE"+number;
            }
        }
        void right(int numberOfMoves)
        {
            if(currentPositionY==7)
            {
                System.out.println("Can't move");
            }
            else{
                currentPositionY = currentPositionY - numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="BE"+number;
            }
        }
        void showValidMoves(ArrayList<String> players,ArrayList<String> oppositionTeam,
        ArrayList<Object>oppositionPlayers)
        {
            if(currentPositionX!=0 || currentPositionY!=7){
                if(!players.contains(ChessBoard[currentPositionX+1][currentPositionY])
                    ||!oppositionTeam.contains(ChessBoard[currentPositionX+1][currentPositionY])
                ){
                    System.out.println("1 : right");
                    System.out.println("2 : left");
                    System.out.println("3 : down");
                    System.out.println("4 : up");
                }
                // else if(!players.contains(ChessBoard[currentPositionX-1][currentPositionY])){
                //     System.out.println("2 : UP");
                // }
                if(currentPositionY!=7 || currentPositionY!=0){
                    for (int i = 0; i < ChessBoard.length; i++)
                    {
                        if(oppositionTeam.contains(ChessBoard[currentPositionX - i][currentPositionY])
                        ||oppositionTeam.contains(ChessBoard[currentPositionX + i][currentPositionY])
                        ||oppositionTeam.contains(ChessBoard[currentPositionX][currentPositionY - i])
                        ||oppositionTeam.contains(ChessBoard[currentPositionX][currentPositionY + i])
                        ){
                            System.out.println("5 : ATTACK");
                            break;
                        }
                    }
                }
            }
            try{
                System.out.print("Your move along with the number of moves seperated by a space : ");
                int move = Integer.parseInt(globalBR.readLine());
                int numberOfMoves = Integer.parseInt(globalBR.readLine());
                if(move==1){
                    this.right(numberOfMoves);
                    
                }
                else if(move==2){
                    this.left(numberOfMoves);
                }
                else if(move==3){
                    this.down(numberOfMoves);
                }
                else if(move==4){
                    this.up(numberOfMoves);
                }
                else if(move==5) {
                    // fill here for attack move
                }
                else{
                    System.out.println("Sorry there is no such move available.");
                }
                display();
            }catch(IOException e){}
        }
    }
    //WHITE ELEPHANT
    static class WhiteElephant{
        int currentPositionX,currentPositionY,number;
        WhiteElephant(int positionX,int positionY,int n){
            currentPositionX = positionX;
            currentPositionY = positionY;
            number = n;
            ChessBoard[currentPositionX][currentPositionY]="WE"+number;
        }
        void up(int numberOfMoves)
        {
            if((currentPositionX==0))
            {
                System.out.println("Can't move");
            }
            else
            {
                currentPositionX = currentPositionX - numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="WE"+number;
            }
        }
        void down(int numberOfMoves)
        {
            if((currentPositionX==7))
            {
                System.out.println("Can't move");
            }
            else
            {
                currentPositionX = currentPositionX + numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="WE"+number;
            }
        }
        void left(int numberOfMoves)
        {
            if((currentPositionY==0))
            {
                System.out.println("Can't move");
            }
            else
            {
                currentPositionY = currentPositionY - numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="WE"+number;
            }
        }
        void right(int numberOfMoves)
        {
            if(currentPositionY==7)
            {
                System.out.println("Can't move");
            }
            else{
                currentPositionY = currentPositionY - numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="WE"+number;
            }
        }
        void showValidMoves(ArrayList<String> players,ArrayList<String> oppositionTeam,
        ArrayList<Object>oppositionPlayers)
        {
            if(currentPositionX!=0 || currentPositionY!=7){
                if(!players.contains(ChessBoard[currentPositionX+1][currentPositionY])
                    ||!oppositionTeam.contains(ChessBoard[currentPositionX+1][currentPositionY])
                ){
                    System.out.println("1 : right");
                    System.out.println("2 : left");
                    System.out.println("3 : down");
                    System.out.println("4 : up");
                }
                // else if(!players.contains(ChessBoard[currentPositionX-1][currentPositionY])){
                //     System.out.println("2 : UP");
                // }
                if(currentPositionY!=7 || currentPositionY!=0){
                    for (int i = 0; i < ChessBoard.length; i++)
                    {
                        if(oppositionTeam.contains(ChessBoard[currentPositionX - i][currentPositionY])
                        ||oppositionTeam.contains(ChessBoard[currentPositionX + i][currentPositionY])
                        ||oppositionTeam.contains(ChessBoard[currentPositionX][currentPositionY - i])
                        ||oppositionTeam.contains(ChessBoard[currentPositionX][currentPositionY + i])
                        ){
                            System.out.println("5 : ATTACK");
                            break;
                        }
                    }
                }
            }
            try{
                System.out.print("Your move along with the number of moves seperated by a space : ");
                int move = Integer.parseInt(globalBR.readLine());
                int numberOfMoves = Integer.parseInt(globalBR.readLine());
                if(move==1){
                    this.right(numberOfMoves);
                    
                }
                else if(move==2){
                    this.left(numberOfMoves);
                }
                else if(move==3){
                    this.down(numberOfMoves);
                }
                else if(move==4){
                    this.up(numberOfMoves);
                }
                else if(move==5) {
                    // fill here for attack move
                }
                else{
                    System.out.println("Sorry there is no such move available.");
                }
                display();
            }catch(IOException e){}
        }
    }
    //BLACK CAMEL
    static class BlackCamel{
        int currentPositionX,currentPositionY,number;
        BlackCamel(int positionX,int positionY,int n){
            currentPositionX = positionX;
            currentPositionY = positionY;
            number = n;
            ChessBoard[currentPositionX][currentPositionY]="BC"+number;
        }
        void topLeft(int numberOfMoves)
        {
            if(currentPositionY==0 && currentPositionX==0)
            {
                System.out.println("Can't move");
            }
            else{
                currentPositionX = currentPositionX - numberOfMoves;
                currentPositionY = currentPositionY - numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="BC"+number;
            }
        }
        void topRight(int numberOfMoves)
        {
            if(currentPositionY==7 && currentPositionX==0)
            {
                System.out.println("Can't move");
            }
            else{
                currentPositionX = currentPositionX - numberOfMoves;
                currentPositionY = currentPositionY + numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="BC"+number;
            }
        }
        void bottomLeft(int numberOfMoves)
        {
            if(currentPositionY==0 && currentPositionX==7)
            {
                System.out.println("Can't move");
            }
            else{
                currentPositionX = currentPositionX + numberOfMoves;
                currentPositionY = currentPositionY - numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="BC"+number;
            }
        }
        void bottomRight(int numberOfMoves)
        {
            if(currentPositionY==7 && currentPositionX==7)
            {
                System.out.println("Can't move");
            }
            else{
                currentPositionX = currentPositionX + numberOfMoves;
                currentPositionY = currentPositionY + numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="BC"+number;
            }
        }
        // showing error...check it at line 1251
        void showValidMoves(ArrayList<String> players,ArrayList<String> oppositionTeam,
        ArrayList<Object>oppositionPlayers)
        {
            if(currentPositionX!=0 || currentPositionY!=7){
                if(!players.contains(ChessBoard[currentPositionX+1][currentPositionY])
                    ||!oppositionTeam.contains(ChessBoard[currentPositionX+1][currentPositionY])
                ){
                    System.out.println("1 : topLeft");
                    System.out.println("2 : topRight");
                    System.out.println("3 : bottomLeft");
                    System.out.println("4 : bottomRight");
                }
                // else if(!players.contains(ChessBoard[currentPositionX-1][currentPositionY])){
                //     System.out.println("2 : UP");
                // }
                if(currentPositionY!=7 || currentPositionY!=0){
                    for (int i = 0; i < ChessBoard.length; i++)
                    {
                        if(oppositionTeam.contains(ChessBoard[currentPositionX - i][currentPositionY + i])
                        ||oppositionTeam.contains(ChessBoard[currentPositionX + i][currentPositionY + i])
                        ||oppositionTeam.contains(ChessBoard[currentPositionX - i][currentPositionY - i])
                        ||oppositionTeam.contains(ChessBoard[currentPositionX + i][currentPositionY + i])
                        ){
                            System.out.println("5 : ATTACK");
                            break;
                        }
                    }
                }
            }
            try{
                System.out.print("Your move along with the number of moves seperated by a space : ");
                int move = Integer.parseInt(globalBR.readLine());
                int numberOfMoves = Integer.parseInt(globalBR.readLine());
                if(move==1){
                    this.topLeft(numberOfMoves);
                    
                }
                else if(move==2){
                    this.topRight(numberOfMoves);
                }
                else if(move==3){
                    this.bottomLeft(numberOfMoves);
                }
                else if(move==4){
                    this.bottomRight(numberOfMoves);
                }
                else if(move==5) {
                    // fill here for attack move
                }
                else{
                    System.out.println("Sorry there is no such move available.");
                }
                display();
            }catch(IOException e){}
        }
    }
    //WHITE CAMEL
    static class WhiteCamel{
        int currentPositionX,currentPositionY,number;
        WhiteCamel(int positionX,int positionY,int n){
            currentPositionX = positionX;
            currentPositionY = positionY;
            number = n;
            ChessBoard[currentPositionX][currentPositionY]="WC"+number;
        }
        void topLeft(int numberOfMoves)
        {
            if(currentPositionY==0 && currentPositionX==0)
            {
                System.out.println("Can't move");
            }
            else{
                currentPositionX = currentPositionX - numberOfMoves;
                currentPositionY = currentPositionY - numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="WC"+number;
            }
        }
        void topRight(int numberOfMoves)
        {
            if(currentPositionY==7 && currentPositionX==0)
            {
                System.out.println("Can't move");
            }
            else{
                currentPositionX = currentPositionX - numberOfMoves;
                currentPositionY = currentPositionY + numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="WC"+number;
            }
        }
        void bottomLeft(int numberOfMoves)
        {
            if(currentPositionY==0 && currentPositionX==7)
            {
                System.out.println("Can't move");
            }
            else{
                currentPositionX = currentPositionX + numberOfMoves;
                currentPositionY = currentPositionY - numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="WC"+number;
            }
        }
        void bottomRight(int numberOfMoves)
        {
            if(currentPositionY==7 && currentPositionX==7)
            {
                System.out.println("Can't move");
            }
            else{
                currentPositionX = currentPositionX + numberOfMoves;
                currentPositionY = currentPositionY + numberOfMoves;
                ChessBoard[currentPositionX][currentPositionY]="WC"+number;
            }
        }
        // showing error at line 1314 related to some function parameter..check
        void showValidMoves(ArrayList<String> players,ArrayList<String> oppositionTeam,
        ArrayList<Object>oppositionPlayers)
        {
            if(currentPositionX!=0 || currentPositionY!=7){
                if(!players.contains(ChessBoard[currentPositionX+1][currentPositionY])
                    ||!oppositionTeam.contains(ChessBoard[currentPositionX+1][currentPositionY])
                ){
                    System.out.println("1 : topLeft");
                    System.out.println("2 : topRight");
                    System.out.println("3 : bottomLeft");
                    System.out.println("4 : bottomRight");
                }
                // else if(!players.contains(ChessBoard[currentPositionX-1][currentPositionY])){
                //     System.out.println("2 : UP");
                // }
                if(currentPositionY!=7 || currentPositionY!=0){
                    for (int i = 0; i < ChessBoard.length; i++)
                    {
                        if(oppositionTeam.contains(ChessBoard[currentPositionX - i][currentPositionY + i])
                        ||oppositionTeam.contains(ChessBoard[currentPositionX + i][currentPositionY + i])
                        ||oppositionTeam.contains(ChessBoard[currentPositionX - i][currentPositionY - i])
                        ||oppositionTeam.contains(ChessBoard[currentPositionX + i][currentPositionY + i])
                        ){
                            System.out.println("5 : ATTACK");
                            break;
                        }
                    }
                }
            }
            try{
                System.out.print("Your move along with the number of moves seperated by a space : ");
                int move = Integer.parseInt(globalBR.readLine());
                int numberOfMoves = Integer.parseInt(globalBR.readLine());
                if(move==1){
                    this.topLeft(numberOfMoves);
                    
                }
                else if(move==2){
                    this.topRight(numberOfMoves);
                }
                else if(move==3){
                    this.bottomLeft(numberOfMoves);
                }
                else if(move==4){
                    this.bottomRight(numberOfMoves);
                }
                else if(move==5) {
                    // fill here for attack move
                }
                else{
                    System.out.println("Sorry there is no such move available.");
                }
                display();
            }catch(IOException e){}
        }
    }

    static void display(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.print(ChessBoard[i][j]+"\t");
            }
            System.out.println();
        }
    }

    static void callAsPerRequired(int n,int side){
        
    }
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //Setting up the chessboard
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(j%2==0){
                    ChessBoard[i][j]="-";
                }
                else{
                    ChessBoard[i][j]="";
                }
            }
        }

        chessGame obj = new chessGame();

        //Getting the players
        //Getting all the black pawns
        BlackPawn BP1 = new BlackPawn(1, 0, 1);
        BlackPawn BP2 = new BlackPawn(1, 1, 2);
        BlackPawn BP3 = new BlackPawn(1, 2, 3);
        BlackPawn BP4 = new BlackPawn(1, 3, 4);
        BlackPawn BP5 = new BlackPawn(1, 4, 5);
        BlackPawn BP6 = new BlackPawn(1, 5, 6);
        BlackPawn BP7 = new BlackPawn(1, 6, 7);
        BlackPawn BP8 = new BlackPawn(1, 7, 8);
        //Getting all the white pawns
        WhitePawn WP1 = new WhitePawn(6, 0, 1);
        WhitePawn WP2 = new WhitePawn(6, 1, 2);
        WhitePawn WP3 = new WhitePawn(6, 2, 3);
        WhitePawn WP4 = new WhitePawn(6, 3, 4);
        WhitePawn WP5 = new WhitePawn(6, 4, 5);
        WhitePawn WP6 = new WhitePawn(6, 5, 6);
        WhitePawn WP7 = new WhitePawn(6, 6, 7);
        WhitePawn WP8 = new WhitePawn(6, 7, 8);

        //Getting the black main players side
        BlackKing BK = new BlackKing(0, 4);
        BlackQueen BQ = new BlackQueen(0, 3);
        BlackCamel BC1 = new BlackCamel(0, 2, 1);
        BlackHorse BH1 = new BlackHorse(0, 1, 1);
        BlackElephant BE1 = new BlackElephant(0, 0, 1);
        BlackCamel BC2 = new BlackCamel(0, 5, 2);
        BlackHorse BH2 = new BlackHorse(0, 6, 2);
        BlackElephant BE2 = new BlackElephant(0, 7, 2);

        //Getting the white main player side
        WhiteKing WK = new WhiteKing(7,3);
        WhiteQueen WQ = new WhiteQueen(7, 4);
        WhiteCamel WC1 = new WhiteCamel(7, 2, 1);
        WhiteHorse WH1 = new WhiteHorse(7, 1, 1);
        WhiteElephant WE1 = new WhiteElephant(7, 0, 1);
        WhiteCamel WC2 = new WhiteCamel(7, 5, 2);
        WhiteHorse WH2 = new WhiteHorse(7, 6, 2);
        WhiteElephant WE2 = new WhiteElephant(7, 7, 2);

        System.out.println("My chess board : ");
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.print(ChessBoard[i][j]+"\t");
            }
            System.out.println();
        }


        //Now the play begins
        //To know which all players are available we use a list
        ArrayList<Object> blackPlayers = new ArrayList<>();
        ArrayList<Object> whitePlayers = new ArrayList<>();
        // ArrayList<String> toShowBlack = new ArrayList<>();
        // ArrayList<String> toShowWhite = new ArrayList<>();
        
        blackPlayers.add(BP1);
        blackPlayers.add(BP2);
        blackPlayers.add(BP3);
        blackPlayers.add(BP4);
        blackPlayers.add(BP5);
        blackPlayers.add(BP6);
        blackPlayers.add(BP7);
        blackPlayers.add(BP8);
        blackPlayers.add(BC1);
        blackPlayers.add(BH1);
        blackPlayers.add(BE1);
        blackPlayers.add(BC2);
        blackPlayers.add(BH2);
        blackPlayers.add(BE2);
        blackPlayers.add(BK);
        blackPlayers.add(BQ);

        whitePlayers.add(WP1);
        whitePlayers.add(WP2);
        whitePlayers.add(WP3);
        whitePlayers.add(WP4);
        whitePlayers.add(WP5);
        whitePlayers.add(WP6);
        whitePlayers.add(WP7);
        whitePlayers.add(WP8);
        whitePlayers.add(WC1);
        whitePlayers.add(WH1);
        whitePlayers.add(WE1);
        whitePlayers.add(WC2);
        whitePlayers.add(WH2);
        whitePlayers.add(WE2);
        whitePlayers.add(WK);
        whitePlayers.add(WQ);

        // for(int i=0;i<8;i++){
        //     toShowWhite.add("WP"+(i+1));
        //     toShowBlack.add("BP"+(i+1));
        // }
        // for(int i=1;i<=2;i++){
        //     toShowWhite.add("WC"+(i));
        //     toShowWhite.add("WH"+(i));
        //     toShowWhite.add("WE"+(i));
        //     toShowBlack.add("BC"+(i));
        //     toShowBlack.add("BH"+(i));
        //     toShowBlack.add("BE"+(i));
        // }

        // toShowWhite.add("WK");
        // toShowWhite.add("WQ");

        // toShowBlack.add("BK");
        // toShowBlack.add("BQ");

        // To check whether any of the player is checked mate or not
        int white_player_check_mate=0, black_player_check_mate = 0;

        while(white_player_check_mate!=1 || black_player_check_mate!=1){
            System.out.println("Black Side : ");
            System.out.println("-------------");
            System.out.println("Available players : ");
            for(int i=0;i<toShowBlack.size();i++){
                System.out.println((i+1)+":"+toShowBlack.get(i));
            }
            System.out.print("Chose your player : ");
            int playerChosen = Integer.parseInt(br.readLine());

            System.out.println("You chose -"+toShowBlack.get(playerChosen-1)+" : ");
            
            if((toShowBlack.get(playerChosen-1).equals("BP1"))){
                BP1.showValidMoves(toShowBlack,toShowWhite,whitePlayers);
                // System.out.print("Your move : ");
                // display();
            }
            else if((toShowBlack.get(playerChosen-1).equals("BP2"))){
                BP2.showValidMoves(toShowBlack,toShowWhite,whitePlayers);
            }
            else if((toShowBlack.get(playerChosen-1).equals("BP3"))){
                BP3.showValidMoves(toShowBlack,toShowWhite,whitePlayers);
            }
            else if((toShowBlack.get(playerChosen-1).equals("BP4"))){
                BP4.showValidMoves(toShowBlack,toShowWhite,whitePlayers);
            }
            else if((toShowBlack.get(playerChosen-1).equals("BP5"))){
                BP5.showValidMoves(toShowBlack,toShowWhite,whitePlayers);
            }
            else if((toShowBlack.get(playerChosen-1).equals("BP6"))){
                BP6.showValidMoves(toShowBlack,toShowWhite,whitePlayers);
            }
            else if((toShowBlack.get(playerChosen-1).equals("BP7"))){
                BP7.showValidMoves(toShowBlack,toShowWhite,whitePlayers);
            }
            else if((toShowBlack.get(playerChosen-1).equals("BP8"))){
                BP8.showValidMoves(toShowBlack,toShowWhite,whitePlayers);
            }
            else if((playerChosen-1)==8){
                BC1.showValidMoves(toShowBlack,toShowWhite,whitePlayers);
            }
            else if((playerChosen-1)==9){
                BH1.showValidMoves(toShowBlack,toShowWhite,whitePlayers);
            }
            else if((playerChosen-1)==10){
                BE1.showValidMoves(toShowBlack,toShowWhite,whitePlayers);
            }
            else if((playerChosen-1)==11){
                BC2.showValidMoves(toShowBlack,toShowWhite,whitePlayers);
            }
            else if((playerChosen-1)==12){
                BH2.showValidMoves(toShowBlack,toShowWhite,whitePlayers);
            }
            else if((playerChosen-1)==13){
                BE2.showValidMoves(toShowBlack,toShowWhite,whitePlayers);
            }
            else if((playerChosen-1)==14){
                BK.showValidMoves();
            }
            else if((playerChosen-1)==15){
                BQ.showValidMoves();
            }

            //Now the turn of white side
            System.out.println("White Side : ");
            System.out.println("-------------");
            System.out.println("Available players : ");
            for(int i=0;i<toShowWhite.size();i++){
                System.out.println((i+1)+":"+toShowWhite.get(i));
            }
            System.out.print("Chose your player : ");
            int playerChosenW = Integer.parseInt(br.readLine());

            System.out.println("You chose -"+toShowWhite.get(playerChosenW-1)+" : ");
            
            if((toShowWhite.get(playerChosenW-1).equals("WP1"))){
                WP1.showValidMoves(toShowWhite,toShowBlack,blackPlayers);
                // System.out.print("Your move : ");
                // display();
            }
            else if((toShowWhite.get(playerChosenW-1).equals("WP2"))){
                WP2.showValidMoves(toShowWhite,toShowBlack,blackPlayers);
            }
            else if((toShowWhite.get(playerChosenW-1).equals("WP3"))){
                WP3.showValidMoves(toShowWhite,toShowBlack,blackPlayers);
            }
            else if((toShowWhite.get(playerChosenW-1).equals("WP4"))){
                WP4.showValidMoves(toShowWhite,toShowBlack,blackPlayers);
            }
            else if((toShowWhite.get(playerChosenW-1).equals("WP5"))){
                WP5.showValidMoves(toShowWhite,toShowBlack,blackPlayers);
            }
            else if((toShowWhite.get(playerChosenW-1).equals("WP6"))){
                WP6.showValidMoves(toShowWhite,toShowBlack,blackPlayers);
            }
            else if((toShowWhite.get(playerChosenW-1).equals("WP7"))){
                WP7.showValidMoves(toShowWhite,toShowBlack,blackPlayers);
            }
            else if((toShowWhite.get(playerChosenW-1).equals("WP8"))){
                WP8.showValidMoves(toShowWhite,toShowBlack,blackPlayers);
            }
            else if((playerChosenW-1)==8){
                WC1.showValidMoves(toShowWhite,toShowBlack,blackPlayers);
            }
            else if((playerChosenW-1)==9){
                WH1.showValidMoves(toShowWhite,toShowBlack,blackPlayers);
            }
            else if((playerChosenW-1)==10){
                WE1.showValidMoves(toShowWhite,toShowBlack,blackPlayers);
            }
            else if((playerChosenW-1)==11){
                WC2.showValidMoves(toShowWhite,toShowBlack,blackPlayers);
            }
            else if((playerChosenW-1)==12){
                WH2.showValidMoves(toShowWhite,toShowBlack,blackPlayers);
            }
            else if((playerChosenW-1)==13){
                WE2.showValidMoves(toShowWhite,toShowBlack,blackPlayers);
            }
            else if((toShowWhite.get(playerChosenW-1).equals("WK"))){
                WK.showValidMoves();
            }
            else if((playerChosenW-1)==15){
                WQ.showValidMoves();
            }
            
        }


    }

}
