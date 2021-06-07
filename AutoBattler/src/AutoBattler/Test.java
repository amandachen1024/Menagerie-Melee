package AutoBattler;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class Test
{
    private static Scanner in = new Scanner(System.in);

    public static void recruit(Warband w) throws IOException {
        for (int i = 0; i < 5; i++) {
            Fighter option1 = new Fighter((int) (Math.random() * 5), w);
            Fighter option2 = new Fighter((int) (Math.random() * 5), w);
            Fighter option3 = new Fighter((int) (Math.random() * 5), w);
            HashMap<String, Fighter> map = new HashMap<String, Fighter>();
            map.put("1", option1);
            map.put("2", option2);
            map.put("3", option3);
            System.out.println("Option 1: " + option1.toString());
            System.out.println("Option 2: " + option2.toString());
            System.out.println("Option 3: " + option3.toString());
            System.out.print("Choose a fighter: ");
            String choice = in.next();
            w.addFighter(map.get(choice));
        }
    }



    public static void main(String[] args) throws InterruptedException, IOException {
        /*
        Warband warband1 = new Warband("player 1");
        recruit(warband1);
        System.out.println(warband1);
        Warband warband2 = new Warband();
        System.out.println(warband2);
        Thread.sleep(1000);
        Fighter.attack(warband1.getFighter(0), warband2.getFighter((int) (warband2.getLength()*Math.random())));


        JFrame frame = new JFrame();

        JLabel label = new JLabel();
        ImageChanger imageChanger = new ImageChanger();
        ImageIcon icon = new ImageIcon(imageChanger.invertColours("AutoBattlerImages/fighterImage00.png"));
        label.setIcon(icon);
        frame.add(label);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

         */

        JFrame frame = new JFrame();
        Warband w = new Warband("a");
        Fighter f = new Fighter(6, w);
        System.out.println(f);
        frame.setSize(400, 400);
        frame.add(f.getPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }


}
