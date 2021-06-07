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
    private JLabel infoLabel;
    private JLabel nameLabel;
    private JLabel imageLabel;
    private JLabel attackLabel;
    private JLabel healthLabel;
    private ImageIcon fighterImage;
    private ImageIcon scaledImage;
    private ImageIcon hitImage;
    private ImageIcon scaledHitImage;
    private ImageIcon deadImage;
    private ImageIcon scaledDeadImage;

    private JLabel attackIconLabel;
    private JLabel healthIconLabel;
    private ImageIcon attackIcon;
    private ImageIcon healthIcon;
    private ImageIcon scaledAttackIcon;
    private ImageIcon scaledHealthIcon;

    private ImageChanger imageChanger = new ImageChanger();
    private static Object lock = new Object();

    private static final String[] possibleNames = {"caterpalien", "swirly", "jellyjam", "f'stein", "bunny", "protractorfish", "delf"};
    private static final String[] possibleImages =
            {"AutoBattlerImages/fighterImage00.png","AutoBattlerImages/fighterImage01.png", "AutoBattlerImages/fighterImage02.png",
                    "AutoBattlerImages/fighterImage03.png", "AutoBattlerImages/fighterImage04.png", "AutoBattlerImages/fighterImage05.png",
                    "AutoBattlerImages/fighterImage06.png"};
    private static final int[] possibleAttacks = {3, 6, 8, 15, 5, 12, 7};
    private static final int[] possibleHealths = {20, 13, 9, 2, 15, 4, 12};
    private static final int numPossible = possibleNames.length;
    private static final String attackIconFile = "AutoBattlerImages/attackIcon.png";
    private static final String healthIconFile = "AutoBattlerImages/healthIcon.png";


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
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(260, 260));
        panel.setBackground(Color.white);
        GridBagConstraints c = new GridBagConstraints();

        nameLabel = new JLabel(name);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        infoLabel = new JLabel(" ");
        infoLabel.setLayout(new GridBagLayout());
        infoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        infoLabel.setBackground(Color.white);
        infoLabel.setOpaque(true);

        imageLabel = new JLabel();
        fighterImage = new ImageIcon(getClass().getResource(imageFile));
        scaledImage = new ImageIcon(fighterImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        deadImage = new ImageIcon(imageChanger.invertColours(imageFile));
        scaledDeadImage = new ImageIcon(deadImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        hitImage = new ImageIcon(imageChanger.makeRed(imageFile));
        scaledHitImage = new ImageIcon(hitImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));

        attackIconLabel = new JLabel();
        healthIconLabel = new JLabel();
        attackIcon = new ImageIcon(getClass().getResource(attackIconFile));
        scaledAttackIcon = new ImageIcon(attackIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        healthIcon = new ImageIcon(getClass().getResource(healthIconFile));
        scaledHealthIcon = new ImageIcon(healthIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));

        imageLabel.setIcon(scaledImage);

        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(imageLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        infoLabel.add(nameLabel, c);

        attackLabel = new JLabel(String.valueOf(attack));
        attackLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        attackIconLabel.setIcon(scaledAttackIcon);
        attackIconLabel.setLayout(new GridBagLayout());
        c.gridx = 1;
        c.gridy = 1;
        attackIconLabel.add(attackLabel, c);

        c.fill = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        infoLabel.add(attackIconLabel, c);



        healthLabel = new JLabel(String.valueOf(currentHealth));
        healthLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        healthIconLabel.setIcon(scaledHealthIcon);
        healthIconLabel.setLayout(new GridBagLayout());
        c.weightx = 0;
        c.gridx = 1;
        c.gridy = 1;
        healthIconLabel.add(healthLabel, c);

        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.EAST;
        infoLabel.add(healthIconLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.ipady = 5;
        c.weightx = 0;
        c.fill = GridBagConstraints.BOTH;
        panel.add(infoLabel, c);

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
        System.out.println(attacker.getPlayer() + "'s " + attacker.getName() + " attacked " + defender.getPlayer() + "'s " +defender.getName());

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
        return (attacker.getPlayer() + "'s " + attacker.getName() + " attacked " + defender.getPlayer() + "'s " +defender.getName() + "!");
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

    public static int getNumPossible() {return numPossible;}

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
