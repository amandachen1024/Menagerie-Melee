package AutoBattler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class AutoBattler extends JFrame implements ActionListener
{
    private static boolean startPressed = false;
    private static boolean choiceMade = false;
    private static boolean battleStarted = false;

    private static String choice = "";

    private static JButton startButton;
    private static JButton choose1;
    private static JButton choose2;
    private static JButton choose3;
    private static JButton startBattle;

    private static JPanel startPanel;
    private static JPanel recruitPanel;
    private static JPanel battlePanel;
    private static JPanel fightPanel;
    private static GridBagConstraints constraints;

    private static Warband currentWarband;
    private static HashMap<String, Fighter> map;

    private static CardLayout card;
    private static Container c;

    public AutoBattler()
    {
        setSize(1750, 1000);

        startPanel = new JPanel();
        recruitPanel = new JPanel(new GridBagLayout());
        battlePanel = new JPanel();
        fightPanel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        startButton = new JButton("Press to start");
        choose1 = new JButton("Choose 1st option");
        choose2 = new JButton("Choose 2nd option");
        choose3 = new JButton("Choose 3rd option");

        startBattle = new JButton("Start battle");


        startPanel.add(startButton);



        battlePanel.add(startBattle);

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
        card = new CardLayout(40, 30);
        c.setLayout(card);
        startButton.addActionListener(this);
        choose1.addActionListener(new OptionClicked("choose1"));
        choose2.addActionListener(new OptionClicked("choose2"));
        choose3.addActionListener(new OptionClicked("choose3"));
        startBattle.addActionListener(this);
        c.add("startPanel", startPanel);
        c.add("recruitPanel", recruitPanel);
        c.add("battlePanel", battlePanel);
        c.add("fightPanel", fightPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //pack();
        setVisible(true);

    }
    public static void recruit(Warband w) {
        currentWarband = w;
        recruitPanel.removeAll();
            choiceMade = false;
            Fighter option1 = new Fighter((int) (Math.random() * 5));
            Fighter option2 = new Fighter((int) (Math.random() * 5));
            Fighter option3 = new Fighter((int) (Math.random() * 5));
            map = new HashMap<String, Fighter>();
            map.put("choose1", option1);
            map.put("choose2", option2);
            map.put("choose3", option3);
            //constraints.fill = GridBagConstraints.VERTICAL;
            constraints.ipadx = 45;
            constraints.gridx = 0;
            constraints.gridy = 0;
            recruitPanel.add(option1.getPanel(), constraints);
            constraints.gridx = 1;
            constraints.gridy = 0;
            recruitPanel.add(option2.getPanel(), constraints);
            constraints.gridx = 2;
            constraints.gridy = 0;
            recruitPanel.add(option3.getPanel(), constraints);
            constraints.gridx = 0;
            constraints.gridy = 2;
            recruitPanel.add(choose1, constraints);

            constraints.gridx = 1;
            constraints.gridy = 2;
            recruitPanel.add(choose2, constraints);

            constraints.gridx = 2;
            constraints.gridy = 2;
            recruitPanel.add(choose3, constraints);

            recruitPanel.revalidate();
            recruitPanel.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        card.next(c);
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
                currentWarband.addFighter((map.get(choice)));
                System.out.println(currentWarband);
                if (currentWarband.getLength() < 5) {
                    recruit(currentWarband);
                }
            }
            return;
            //System.out.println(choice);
        }

    }



    public static void main(String[] args)
    {
        new AutoBattler();
        Warband w = new Warband("player");
        recruit(w);
        System.out.println("done");

    }
}
