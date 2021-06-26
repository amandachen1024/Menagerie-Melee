package AutoBattler;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class AutoBattler extends JFrame implements ActionListener
{
    private static boolean startPressed = false;
    private static boolean choiceMade = false;
    private static boolean battleStarted = false;

    private static String choice = "";

    private static JLabel introHeader;
    private static JLabel instructions;
    private static JButton startButton;
    private static JTextField textField1;
    private static JTextField textField2;
    private static JLabel startLabel;
    private static JButton choose1;
    private static JButton choose2;
    private static JButton choose3;
    private static JLabel recruitLabel;
    private static JLabel recapLabel;
    private static JLabel p1recap;
    private static JLabel p2recap;
    private static JButton startBattle;
    private static JLabel battleLabel;
    private static JLabel endLabel;
    private static JLabel resultLabel;

    private static ImagePanel startPanel;
    private static ImagePanel recruitPanel;
    private static ImagePanel recapPanel;
    public static ImagePanel fightPanel;
    private static ImagePanel endPanel;
    private static GridBagConstraints constraints;

    private static Warband currentWarband;
    private static HashMap<String, Fighter> fighterMap;
    private static HashMap<Warband, Integer> warbandMap;

    private static CardLayout card;
    private static Container c;

    private static Object lock;

    private static Warband w1;
    private static Warband w2;

    public AutoBattler()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        startPanel = new ImagePanel(new ImageIcon("src/AutoBattler/AutoBattlerImages/startBackground.png").getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        startPanel.setLayout(new GridBagLayout());
        recruitPanel = new ImagePanel(new ImageIcon("src/AutoBattler/AutoBattlerImages/recruitBackground.png").getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        recruitPanel.setLayout(new GridBagLayout());
        recapPanel = new ImagePanel(new ImageIcon("src/AutoBattler/AutoBattlerImages/recapBackground.png").getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        recapPanel.setLayout(new GridBagLayout());
        fightPanel = new ImagePanel(new ImageIcon("src/AutoBattler/AutoBattlerImages/battleBackground.png").getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        fightPanel.setLayout(new GridBagLayout());
        endPanel = new ImagePanel(new ImageIcon("src/AutoBattler/AutoBattlerImages/endBackground.png").getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        endPanel.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        introHeader = new JLabel("Welcome to Autobattler!");
        introHeader.setFont(new Font("Verdana", Font.BOLD, 20));
        instructions = new JLabel();
        instructions.setFont(new Font("Verdana", Font.PLAIN, 14));
        instructions.setText("<html><div style=\"width: 500px;\"> This is a turn-based autobattler for you to test your RNG against your friend using different fighters to duke it out. This game will consists of 2 phases: recruiting fighters and a battle. <p><p>" +
                "<b>RECRUITING: </b><p><p>" +
                "After entering your names, each of you will choose 5 fighters to add to your warband. For each, you will be able to choose 1 out of 3 different options (if you are especially unlucky, you may be offered 3 of the same fighter) <p><p>" +
                "<b>BATTLE: </b><p><p>" +
                "After you each choose your fighters, they will fight to determine a winner. Player 1's first minion will randomly attack one of Player 2's. Then, Player 2's first minion will attack one of Player 1's. The next living fighter on each warband will attack randomly until one player has no more fighters. <p><p>" +
                "Then, a winner will be declared.</div></html>");
        startButton = new JButton("Press to start");
        textField1 = new JTextField("Player 1");
        textField2 = new JTextField("Player 2");
        startLabel = new JLabel("Enter your names:");
        startLabel.setFont(new Font("Verdana", Font.PLAIN, 16));

        recruitLabel = new JLabel();
        choose1 = new JButton("Choose 1st option");
        choose2 = new JButton("Choose 2nd option");
        choose3 = new JButton("Choose 3rd option");

        recapLabel = new JLabel("Recap - here's what you chose:");
        recapLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        p1recap = new JLabel();
        p1recap.setFont(new Font("Verdana", Font.PLAIN, 16));
        p2recap = new JLabel();
        p2recap.setFont(new Font("Verdana", Font.PLAIN, 16));
        startBattle = new JButton("Start battle");

        battleLabel = new JLabel("The battle begins...");
        battleLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        battleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        endLabel = new JLabel("GAME OVER");
        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.ipady = 25;
        startPanel.add(introHeader, constraints);

        constraints.gridy = 1;
        constraints.ipady = 50;
        //constraints.weightx = 2;
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        startPanel.add(instructions, constraints);

        constraints.fill = 0;
        //constraints.weightx = 0.25;
        constraints.ipady = 15;
        constraints.gridy = 2;
        startPanel.add(startLabel, constraints);

        constraints.gridy = 3;
        constraints.ipady = 5;
        constraints.ipadx = 100;
        startPanel.add(textField1, constraints);

        constraints.gridy = 4;
        startPanel.add(textField2, constraints);

        constraints.gridy = 5;
        constraints.ipady = 15;
        constraints.ipadx = 0;
        startPanel.add(startButton, constraints);



        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0;
        //constraints.fill = GridBagConstraints.VERTICAL;
        constraints.ipadx = 0;
        constraints.ipady = 100;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        fightPanel.add(battleLabel, constraints);
        constraints.fill = 0;
        constraints.gridwidth = 1;

        constraints.ipadx = 0;
        constraints.ipady = 75;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(getHeight()/2-150, 0, 0, 0);
        constraints.anchor = GridBagConstraints.CENTER;
        endPanel.add(resultLabel, constraints);
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridwidth = 1;
        constraints.weightx = 0;

        /*
        Fighter fighter1 = new Fighter(1);
        constraints.ipadx = 45;
        constraints.gridx = 0;
        constraints.gridy = 2;
        fightPanel.add(fighter1.getPanel(), constraints);
        Fighter fighter2 = new Fighter(3);
        constraints.gridx = 1;
        constraints.gridy = 2;
        fightPanel.add(fighter2.getPanel(), constraints);
        Fighter fighter3 = new Fighter(4);
        constraints.gridx = 2;
        constraints.gridy = 2;
        fightPanel.add(fighter3.getPanel(), constraints);
        Fighter fighter4 = new Fighter(0);
        constraints.gridx = 3;
        constraints.gridy = 2;
        fightPanel.add(fighter4.getPanel(), constraints);
        Fighter fighter5 = new Fighter(2);
        constraints.gridx = 4;
        constraints.gridy = 2;
        fightPanel.add(fighter5.getPanel(), constraints);
         */

        c = getContentPane();
        card = new CardLayout();
        c.setLayout(card);
        startButton.addActionListener(this);
        choose1.addActionListener(new OptionClicked("choose1"));
        choose2.addActionListener(new OptionClicked("choose2"));
        choose3.addActionListener(new OptionClicked("choose3"));
        startBattle.addActionListener(this);
        c.add("startPanel", startPanel);
        c.add("recruitPanel", recruitPanel);
        c.add("recapPanel", recapPanel);
        c.add("fightPanel", fightPanel);
        c.add("endPanel", endPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setSize(screenSize.width, screenSize.height);

    }
    public static void recruit(Warband w) throws IOException {
        currentWarband = w;
        recruitLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        recruitLabel.setText("Recruit for " + currentWarband.getPlayer());
        recruitPanel.removeAll();
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.ipady = 100;
        constraints.ipadx = 0;
        recruitPanel.add(recruitLabel, constraints);
        choiceMade = false;
        Fighter option1 = new Fighter((int) (Math.random() * Fighter.getNumPossible()), w);
        Fighter option2 = new Fighter((int) (Math.random() * Fighter.getNumPossible()), w);
        Fighter option3 = new Fighter((int) (Math.random() * Fighter.getNumPossible()), w);
        fighterMap = new HashMap<String, Fighter>();
        fighterMap.put("choose1", option1);
        fighterMap.put("choose2", option2);
        fighterMap.put("choose3", option3);
        //constraints.fill = GridBagConstraints.BOTH;
        constraints.ipadx = 45;
        constraints.ipady = 10;
        constraints.gridx = 0;
        constraints.gridy = 1;
        recruitPanel.add(option1.getPanel(), constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        recruitPanel.add(option2.getPanel(), constraints);
        constraints.gridx = 2;
        constraints.gridy = 1;
        recruitPanel.add(option3.getPanel(), constraints);

        constraints.ipady = 0;
        constraints.insets = new Insets(15, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 3;
        recruitPanel.add(choose1, constraints);

        constraints.gridx = 1;
        recruitPanel.add(choose2, constraints);

        constraints.gridx = 2;
        recruitPanel.add(choose3, constraints);
        constraints.insets = new Insets(0, 0, 0, 0);

        recruitPanel.revalidate();
        recruitPanel.repaint();

    }

    public static void showWarband(Warband w, JPanel panel, int row)
    {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipady = 0;
        constraints.ipadx = 15;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        constraints.insets = new Insets(0, 10, 0, 10);
        for (int i = 0; i < w.getLength(); i++)
        {
            constraints.gridx = i;
            constraints.gridy = row;
            panel.add(w.getFighter(i).getPanel(), constraints);
            System.out.println(w.getFighter(i));
        }
        constraints.insets = new Insets(0, 0, 0, 0);
        panel.revalidate();
        panel.repaint();
    }

    static void paintRecapPanel()
    {
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = 0;
        constraints.gridwidth = 3;
        constraints.ipady = 25;
        constraints.gridy = 0;
        constraints.gridx = 1;
        recapPanel.add(recapLabel, constraints);

        constraints.gridy = 1;
        constraints.gridx = 1;
        recapPanel.add(p1recap, constraints);

        showWarband(w1, recapPanel, 2);

        constraints.gridwidth = 3;
        constraints.gridy = 3;
        constraints.gridx = 1;
        recapPanel.add(p2recap, constraints);

        showWarband(w2, recapPanel, 4);

        constraints.gridy = 5;
        constraints.gridx = 1;
        constraints.ipadx = 5;
        constraints.ipady = 5;
        constraints.insets = new Insets(25, 0, 0, 0);
        recapPanel.add(startBattle, constraints);
        constraints.insets = new Insets(0, 0, 0, 0);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        card.next(c);
        synchronized (lock)
        {
            lock.notify();
        }
    }


    private class OptionClicked implements ActionListener
    {
        private String name;
        public OptionClicked(String n)
        {
            name = n;
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (currentWarband.getLength() < 5) {
                choiceMade = true;
                choice = name;
                currentWarband.addFighter((fighterMap.get(choice)));
                System.out.println(currentWarband);
                if (currentWarband.getLength() < 5) {

                    try {
                        recruit(currentWarband);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
                else
                {
                    if (currentWarband.equals(w2)) {
                        card.next(c);
                    }
                    synchronized (lock) {
                        lock.notify();
                    }
                }
            }

            return;
            //System.out.println(choice);
        }

    }

    public static void main(String[] args) throws InterruptedException, IOException {
        new AutoBattler();
        lock = new Object();
        synchronized (lock)
        {
            lock.wait();
        }

        synchronized (lock)
        {
            w1 = new Warband(textField1.getText());
            p1recap.setText(w1.getPlayer() + " chose these fighters:");
            recruit(w1);
            lock.wait();
        }

        synchronized (lock)
        {
            w2 = new Warband(textField2.getText());
            p2recap.setText(w2.getPlayer() + " chose these fighters:");
            recruit(w2);
            lock.wait();
        }
        paintRecapPanel();
        synchronized (lock) {
            lock.wait();
            showWarband(w1, fightPanel, 0);
            showWarband(w2, fightPanel, 2);
            lock.wait(2000);
        }
        int counter1 = 0;
        int counter2 = 0;
        while (!w1.hasLost() && !w2.hasLost()) {
            /*
            if (counter1 > w1.getNumAlive()-1)
            {
                counter1 = 0;
            }

             */
            while (!w1.getFighter(counter1 % 5).isAlive())
            {
                counter1++;
            }
            battleLabel.setText(Fighter.attack(w1.getFighter(counter1 % 5), w2.getAlive((int) (Math.random()*w2.getNumAlive()))));
            fightPanel.repaint();
            fightPanel.revalidate();
            counter1++;
            synchronized (lock)
            {
                lock.wait(1000);
            }
            if (w1.hasLost() || w2.hasLost())
            {
                break;
            }
            /*
            if (counter2 > w2.getNumAlive()-1)
            {
                counter2 = 0;
            }

             */
            while (!w2.getFighter(counter2 % 5).isAlive())
            {
                counter2++;
            }
            battleLabel.setText(Fighter.attack(w2.getFighter(counter2 % 5), w1.getAlive((int) (Math.random()*w1.getNumAlive()))));
            //fightPanel.repaint();
            //fightPanel.revalidate();
            counter2++;
            synchronized (lock)
            {
                lock.wait(1500);
            }
        }
        if (w1.hasLost() && w2.hasLost())
        {
            resultLabel.setText("It's a tie!");
        }
        else if (w1.hasLost())
        {
            resultLabel.setText(w2.getPlayer() + " wins!");
            for (int i = 0; i < w2.getLength(); i++)
            {
                w2.getFighter(i).revive();
            }
            showWarband(w2, endPanel, 2);
        }
        else
        {
            resultLabel.setText(w1.getPlayer() + " wins!");
            for (int i = 0; i < w1.getLength(); i++)
            {
                w1.getFighter(i).revive();
            }
            showWarband(w1, endPanel, 2);
        }
        endPanel.repaint();
        endPanel.revalidate();
        card.next(c);
        System.out.println("done");

    }
}
