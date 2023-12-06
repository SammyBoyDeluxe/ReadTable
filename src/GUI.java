import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private ContentPanel contentPanel;

    public GUI(){
        /*Gets the screen-dimensions*/
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);
        this.setTitle("Tabell-läsar/skrivarprogram");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPanel = new ContentPanel();
        this.add(contentPanel);
        this.setVisible(true);



    }

}
