import java.util.*;
public class Player 
{
    private String name;
    private int position;
    Player(String s,int pos)
    {
        name = s;
        position = pos;
    }
    int rollDice()
    {
        Random ob = new Random();
		return(ob.nextInt(6)+1);
    }
    String getName()
    {
        return name;
    }
    int getPosition()
    {
        return position;
    }
    void setPosition(int pos)
    {
        this.position=pos;
    }
    
}
