package AutoBattler;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    private ImageIcon scaledHitImage;
    private ImageIcon deadImage;
    private ImageIcon scaledDeadImage;

    private ImageChanger imageChanger = new ImageChanger();
    private static Object lock = new Object();

    private static final String[] possibleNames = {"caterpalien", "swirly", "jellyjam", "f'stein", "bunny"};
    private static final String[] possibleImages = {"AutoBattlerImages/fighterImage00.png", "AutoBattlerImages/fighterImage01.png", "AutoBattlerImages/fighterImage02.png", "AutoBattlerImages/fighterImage03.png", "AutoBattlerImages/fighterImage04.png"};
    private static final int[] possibleAttacks = {2, 4, 7, 12, 5};
    private static final int[] possibleHealths = {20, 13, 4, 2, 15};

    public Fighter(int n, Warband p) throws IOException {
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
        fighterImage = new ImageIcon(getClass().getResource(imageFile));
        scaledImage = new ImageIcon(fighterImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        deadImage = new ImageIcon(imageChanger.invertColours(imageFile));
        scaledDeadImage = new ImageIcon(deadImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        hitImage = new ImageIcon(imageChanger.makeRed(imageFile));
        scaledHitImage = new ImageIcon(hitImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));

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

    public static String attack(Fighter attacker, Fighter defender) throws InterruptedException {
        System.out.println(attacker.getPlayer() + "'s " + attacker.getName() + " attacks " + defender.getPlayer() + "'s " +defender.getName());

        attacker.imageLabel.setIcon(attacker.scaledHitImage);
        //defender.panel.repaint();
        //defender.panel.revalidate();
        defender.imageLabel.setIcon(defender.scaledHitImage);
        //attacker.panel.repaint();
        //attacker.panel.revalidate();
        AutoBattler.fightPanel.repaint();
        AutoBattler.fightPanel.revalidate();
        attacker.beHit(defender);
        defender.beHit(attacker);
        System.out.println("Attacker: " + attacker);
        System.out.println("Defender: " + defender);
        return (attacker.getPlayer() + "'s " + attacker.getName() + " attacks " + defender.getPlayer() + "'s " +defender.getName());
    }

    public void beHit(Fighter opponent) throws InterruptedException {
        currentHealth -= opponent.getAttack();
        healthLabel.setText(String.valueOf(currentHealth));
        synchronized (lock)
        {
            lock.wait(500);
        }
        if (currentHealth <= 0)
        {
            this.die();
        }
        else{

            imageLabel.setIcon(scaledImage);
            AutoBattler.fightPanel.repaint();
            AutoBattler.fightPanel.revalidate();
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

    public boolean isAlive()
    {
        return currentHealth > 0;
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
