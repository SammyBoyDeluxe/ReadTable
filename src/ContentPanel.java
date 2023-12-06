import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ContentPanel extends JPanel {

    private JTable table;

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
                    String[] headers = new String[fileRead.length];
                    for(int col = 0 ; col < fileRead.length ; col++ ){
                        /**/
                        headers[col] = fileRead[col][0];

                    }
                    /*Headers kept on top row, data one row less*/
                    String[][] data = new String[fileRead[0].length-1][fileRead.length];
                    for(int i = 1 ; i < fileRead[0].length ; i++ )for(int col = 0 ; col < fileRead.length; col++){
                        data[i-1][col] = fileRead[col][i];

                    }
                    ContentPanel.this.remove(table);
                    table = new JTable(data,headers);
                    table.setSize(420,420);
                    ContentPanel.this.add(table);


                }



            }
        };
        saveButton.addActionListener(saveListener);
        sortButton.addActionListener(sortListener);
        fileChooserButton.addActionListener(fileChooserListener);
        buttonPanel.add(saveButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(fileChooserButton);
        /*This makes sure that they are presented in a line*/
        buttonPanel.setLayout( new FlowLayout());
        this.add(buttonPanel);
        /*1 This concludes the button-specific programming 1*/

        /*2 This starts the JTable´s container and initiate the JTable 2*/
        JScrollPane tableScrollPane = new JScrollPane();
        String[][] emptyData = new String[3][3];
        for(int i = 0; i < 3 ; i++) for(int k = 0 ; k < 3 ; k++){
            emptyData[k][i] = " ";

        }
        String[] emptyHeaders = {" "," "," "};

        table = new JTable(4,4);
        table.setBorder( BorderFactory.createLineBorder(Color.BLACK,200));
        table.setSize(420,420);
        tableScrollPane.add(table);
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.setVisible(true);


        /*2 This concludes -||- 2*/





    }

    /**This method sets the table´s data to match the incoming data-value-string
     *
     * @param tableInData
     */
    public void setTable(String[][] tableInData){



    }


}
