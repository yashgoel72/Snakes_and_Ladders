
import java.util.*;

public class Board {

    int[][] gameBoard;
	int[][] snakes;
	int[][] ladders;
	public Board()
    {

		//Initialize the board
		gameBoard = new int[10][10];
		for (int row = 0 ; row < 10 ; row++){
			for (int col = 0; col < 10; col++){
				gameBoard[row][col] = row*10 + col + 1;
			}
		}
		snakes = new int[8][2];
		snakes[0][0] = 17;
		snakes[0][1] = 7;
		snakes[1][0] = 54;
		snakes[1][1] = 34;
		snakes[2][0] = 62;
		snakes[2][1] = 19;
		snakes[3][0] = 64;
		snakes[3][1] = 60;
		snakes[4][0] = 87;
		snakes[4][1] = 24;
		snakes[5][0] = 93;
		snakes[5][1] = 73;
		snakes[6][0] = 95;
		snakes[6][1] = 75;
		snakes[7][0] = 99;
		snakes[7][1] = 78;


		ladders = new int[8][2];

		ladders[0][0] = 4;
		ladders[0][1] = 14;
		ladders[1][0] = 9;
		ladders[1][1] = 31;
		ladders[2][0] = 20;
		ladders[2][1] = 38;
		ladders[3][0] = 28;
		ladders[3][1] = 84;
		ladders[4][0] = 40;
		ladders[4][1] = 59;
		ladders[5][0] = 51;
		ladders[5][1] = 67;
		ladders[6][0] = 63;
		ladders[6][1] = 81;
		ladders[7][0] = 71;
		ladders[7][1] = 91;



	}
    public static void main(String[] args){
        //Print welcome message.
        System.out.println("#### Welcome to Snakes & Ladders Game ####");
        System.out.println("Created by Yash Goel");
    
        //Initialize scanner.
        Scanner sc = new Scanner (System.in);
        
        //Input number of players playing
        int numPlayers;
       System.out.print("Please enter the number of player : " );
        numPlayers = sc.nextInt();
        
        //Initialize the players.
        List<Player> players = new ArrayList<Player>();
        for (int i = 1; i <= numPlayers; i++)
        {
            Player p = new Player("P"+i,0);
            players.add(p);
        }
        
        //Initialize the board
        Board board = new Board();
        
        //Loop until a player reaches the final spot.
        //Players take turns to roll the die and move on the board
        boolean done = false;
        int index = 0;
        while (!done)
        {
            //Player takes turn
            Player currPlayer = players.get(index);
            
            int roll = currPlayer.rollDice();
            System.out.println(currPlayer.getName() + " Rolled a dice and got "+ roll);
            
            //Update the board
           currPlayer = board.modify(roll , currPlayer);
           players.set(index , currPlayer);
           //Print the board
           board.printboard(players);
            System.out.println("-----------------------\n");

            if(currPlayer.getPosition()==100)
           {
               done=true;
               System.out.println(currPlayer.getName()+ " WINS ");
           }
            
            
            //Set up for next player
            index++;
            if (index == numPlayers){
                index = 0;
            }
            
        }
    }











	public Player modify(int r, Player p){

		//Compute the new position
		int position = p.getPosition();
		position += r;


		if (position >= 100){
			//If the new position is 100 (or above), the player wins!
			position=100;
            p.setPosition(position);
            return p;
		} else {
			//If the new position is less than 100

			//Check if the new position is the starting point for a snake
			for (int i = 0; i < 8; i++){
				if (snakes[i][0] == position){
					//If the new position is the starting point for a snake
					//Move the player to the end position for the snake
					position = snakes[i][1];
					
					System.out.println("Uh oh. " + p.getName() + " takes snake from " + snakes[i][0] + " to " + snakes[i][1]+"\n");

					
				}
			}

			//Check if the new position is the starting point for a ladder
			for (int i = 0; i < 8; i++){
				if (ladders[i][0] == position){
					//If the new position is the starting point for a ladder
					//Move the player to the end position for the ladder
					position = ladders[i][1];

					System.out.println("Yay! " + p.getName() + " takes ladder from " + ladders[i][0] + " to " + ladders[i][1]+"\n");
					
					
				}
			}
		
			p.setPosition(position);
			return p;
		}

	}
	void printboard(List<Player> players)
	{
		boolean flag=true;
		for(int i=9;i>=0;i--)
		{
			StringBuffer st = new StringBuffer("");
			for(int j=0 ; j<10 ; j++)
			{
				boolean occupied = false;
				int num;
				if(flag)
				num = gameBoard[i][9-j];
				else
				num = gameBoard[i][j];
			String p1="";
			for(Player p : players)
			{
				if(p.getPosition()==num)
				{
					p1 = p1 + p.getName();
					occupied = true;
				}
			}
		
			if(occupied)
			st.append(p1 + '\t');
			else
			st.append(Integer.toString(num) + "\t");
		}
		System.out.println(st);
		flag = !flag;
	}
}

	

}