import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ContentPanel extends JPanel {

    public ContentPanel(){
        /*Create a FileChooser with a currentDirectory in our src where we have our tablefiles*/
        final JFileChooser fc = new JFileChooser("/Users/ronne2/Desktop/Java-programmering (skola)/ReadTable/src");


        /*1 Here we handle the button-specific-programming 1*/
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Spara");
        JButton sortButton = new JButton("Sortera");
        JButton fileChooserButton = new JButton("Välj fil:");
        ActionListener saveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //-> Read table, add demarcations, insert values in a string that can be input in file

            }
        };
        ActionListener sortListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        ActionListener fileChooserListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(ContentPanel.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    String[][] fileRead = FileReader.readFile(file.getAbsolutePath().toString());
                    //-> This is where we need to post this shit to the GUI, ContentPanel encapsulated in GUI. Getter needed or access modification



                }
            }
        };

        /*1 This concludes the button-specific programming 1*/





    }


}
