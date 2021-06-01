package AutoBattler;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ArrayList;

public class Warband
{
    private ArrayList<Fighter> warband;
    private ArrayList<Fighter> alive;
    private ArrayList<Fighter> dead;
    private String player;

    public Warband(String p)
    {
        warband = new ArrayList<Fighter>();
        alive = new ArrayList<Fighter>();
        dead = new ArrayList<Fighter>();
        player = p;

        /*
        for (int i = 0; i < 5; i++)
        {
            int option = (int) (Math.random() * 5);
            //Fighter fighter1 = new Fighter(option, this);

        }

         */
    }

    public void addFighter(Fighter f)
    {
        warband.add(f);
        alive.add(f);
    }

    public void removeFighter(Fighter f)
    {
        alive.remove(f);
        dead.add(f);
    }

    public Fighter getFighter(int i)
    {
        return warband.get(i);
    }

    public Fighter getAlive(int i)
    {
        return alive.get(i);
    }

    public int getLength()
    {
        return warband.size();
    }

    public int getNumAlive()
    {
        return alive.size();
    }

    public int getNumDead()
    {
        return dead.size();
    }

    public boolean hasLost(){return alive.size() <= 0;}

    public String getPlayer() {return player;}

    public String toString()
    {
        String showWarband = "\n";
        showWarband += (player + "'s warband: \n");
        for (Fighter f : warband)
        {
            showWarband += f.toString();
        }
        return showWarband;

    }
}
