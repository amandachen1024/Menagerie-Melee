package AutoBattler;
import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel
{
    private Image image;
    public ImagePanel(String img)
    {
        this(new ImageIcon(img).getImage());
    }
    public ImagePanel(Image img)
    {
        this.image = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(400, 400);

        ImagePanel panel = new ImagePanel(new ImageIcon("src/AutoBattler/AutoBattlerImages/startBackground.png").getImage());
        panel.setLayout(new GridBagLayout());
        JLabel label = new JLabel("hi");
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        c.ipadx = 10;
        panel.add(label, c);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
