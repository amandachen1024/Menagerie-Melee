package AutoBattler;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;

public class Fighter extends JFrame
{
    private String name;
    private String imageFile;
    private int attack;
    private int initHealth;
    private int currentHealth;
    private Warband player;

    private JPanel panel;
    private JLabel imageLabel;
    private JLabel attackLabel;
    private JLabel healthLabel;
    private ImageIcon fighterImage;
    private ImageIcon scaledImage;
    private ImageIcon hitImage;
    private ImageIcon deadImage;
    private ImageIcon scaledDeadImage;

    private static final String[] possibleNames = {"caterpalien", "swirly", "jellyjam", "f'stein", "bunny"};
    private static final String[] possibleImages = {"fighterImage00.png", "fighterImage01.png", "fighterImage02.png", "fighterImage03.png", "fighterImage04.png"};
    private static final int[] possibleAttacks = {2, 4, 7, 12, 5};
    private static final int[] possibleHealths = {20, 13, 4, 2, 15};

    public Fighter(int n, Warband p)
    {
        name = possibleNames[n];
        imageFile = possibleImages[n];
        attack = possibleAttacks[n];
        initHealth = possibleHealths[n];
        currentHealth = initHealth;

        player = p;
        //p.addFighter(this);


        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //panel.setSize(300, 300);

        imageLabel = new JLabel();
        fighterImage = new ImageIcon(getClass().getResource("AutoBattlerImages/" + imageFile));
        scaledImage = new ImageIcon(fighterImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        deadImage = new ImageIcon(getClass().getResource("AutoBattlerImages/image02.png")); // STAND-IN DEADIMAGE FROM GOOGLE IMAGES
        scaledDeadImage = new ImageIcon(deadImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));


        imageLabel.setIcon(scaledImage);

        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 1;
        panel.add(imageLabel, c);

        attackLabel = new JLabel(String.valueOf(attack));
        attackLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(attackLabel, c);

        healthLabel = new JLabel(String.valueOf(currentHealth));
        healthLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        panel.add(healthLabel, c);

        /*

        imageLabel.add(attackLabel);
        imageLabel.add(healthLabel);

        attackLabel.setBounds(50, 300,25, 25);
        panel.add(attackLabel);

        imageLabel.setBounds(75, 100, 200, 200);
        panel.add(imageLabel);

        healthLabel.setBounds(300, 300,25, 25);
        panel.add(healthLabel);

         */


    }

    public static String attack(Fighter attacker, Fighter defender)
    {
        System.out.println(attacker.getPlayer() + "'s " + attacker.getName() + " attacks " + defender.getPlayer() + "'s " +defender.getName());
        attacker.beHit(defender);
        defender.beHit(attacker);
        System.out.println("Attacker: " + attacker);
        System.out.println("Defender: " + defender);
        return (attacker.getPlayer() + "'s " + attacker.getName() + " attacks " + defender.getPlayer() + "'s " +defender.getName());
    }

    public void beHit(Fighter opponent)
    {
        currentHealth -= opponent.getAttack();
        healthLabel.setText(String.valueOf(currentHealth));
        if (currentHealth <= 0)
        {
            this.die();
        }
    }

    public String die()
    {
        System.out.println(name + " has died");
        // code to make it disappear and remove it from list
        player.removeFighter(this);
        imageLabel.setIcon(scaledDeadImage);
        return (name + " has died");
    }

    public String getName() {
        return name;
    }

    public String getPlayer() {return player.getPlayer();}

    public int getAttack() {
        return attack;
    }

    public int getInitHealth() {
        return initHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public JPanel getPanel() {
        return panel;
    }

    public String toString()
    {
        return ("\nname = " + name + "\nattack = " + attack + "\nhealth = " + currentHealth);
    }

    /*
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        Fighter f = new Fighter(1,);
        frame.setSize(200, 200);
        frame.add(f.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

     */

}
