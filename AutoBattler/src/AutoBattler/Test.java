package AutoBattler;
import java.awt.*;
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

        JFrame frame = new JFrame();
        Warband w = new Warband("a");
        Fighter f = new Fighter(8, w);
        System.out.println(f);
        frame.setSize(400, 400);
        frame.add(f.getPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        Warband w = new Warband("a");
        Fighter f = new Fighter(6, w);
        w.addFighter(f);
        AutoBattler.showWarband(w, panel, 1);
        frame.setSize(400, 400);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

         */
        JFrame frame = new JFrame();
        ImagePanel panel = new ImagePanel(new ImageIcon("src/AutoBattler/AutoBattlerImages/endBackground.png").getImage().getScaledInstance(1200, 900, Image.SCALE_DEFAULT));
        panel.setLayout(new GridBagLayout());
        Warband w = new Warband("a");
        Fighter f1 = new Fighter(6, w);
        w.addFighter(f1);
        Fighter f2 = new Fighter(3, w);
        w.addFighter(f2);
        Fighter f3 = new Fighter(1, w);
        w.addFighter(f3);
        Fighter f4 = new Fighter(6, w);
        w.addFighter(f4);
        Fighter f5 = new Fighter(2, w);
        w.addFighter(f5);

        JLabel label = new JLabel("Player 1 wins");
        label.setFont(new Font("Verdana", Font.PLAIN, 20));
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 3;
        constraints.weightx = 5;
        constraints.insets = new Insets(450, 0, 0, 0);
        constraints.anchor = GridBagConstraints.CENTER;

        panel.add(label, constraints);
        constraints.weightx = 1;
        AutoBattler.showWarband(w, panel, 3);

        frame.add(panel);
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }


}
