import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ContentPanel extends JPanel {

    private JTable table;
    private JPanel buttonPanel;
    private Object[][] data;
    private Object[] headers;
    private JScrollPane tableScrollPane = new JScrollPane();
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
                try {
                    File save = new File("src");
                    save.setWritable(true);
                    FileWriter writer = new FileWriter(save);


                /*
                Demarcation-notes: Start and end of file ([,]) respectively
                TableRow: {
                        -|1|-,
                        -|2|-,
                          .
                          .
                          .
                          -|x|-},
                          -> Lägg till {, lägg till varje kolumn åtskild med , och radbrytning
                          tills sista, då lägger vi till },\\n istället
                Column: <"property name"> : <"property-value">,


                 */


                }
                catch (Exception e1){


                }
            }
        };

        ActionListener sortListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<Object>> dataAsAL = new ArrayList<>();
                /*We only want to sort based on the alphabetical order of the first column*/

                for (int row = 0 ; row < data.length ; row++) {
                    ArrayList<Object> temp = new ArrayList<Object>();
                    for (int col = 0; col < data[0].length; col ++) {

                        temp.add(data[row][col]);


                    }
                    /*When we complete an arrayList, one row, we add it to the parentArrayList*/
                    dataAsAL.add(row, temp);
                }

                dataAsAL.sort(new Comparator<ArrayList<Object>>() {
                    @Override
                    public int compare(ArrayList<Object> o1, ArrayList<Object> o2) {
                        /*First we generate charsequences of the name-cells of the two rows*/
                      String o1NameAsString =  ((String) o1.get(0));
                        String o2NameAsString =  ((String) o2.get(0));
                       return String.CASE_INSENSITIVE_ORDER.compare(o1NameAsString, o2NameAsString);

                    }

                });



                for(int i = 0 ; i < data.length ; i++){
                    /*We need to make sure the row finds its spot within its turn otherwise it gets skipped*/
                  for(int k = 0 ; k < data[0].length ; k++){

                      data[i][k] = dataAsAL.get(i).get(k);

                  }
                  /*We reset the index so that we don´t get indexoutofbounds*/



                }
                JTable tempTable = new JTable(data,headers);
                JScrollPane tableScrollPaneTemp = new JScrollPane(tempTable);
                ContentPanel.this.remove(tableScrollPane);
                table = tempTable;
                tableScrollPane = tableScrollPaneTemp;
                tableScrollPane.add(table);
                ContentPanel.this.add(tableScrollPane);

                ContentPanel.this.validate();
                ContentPanel.this.repaint();
                ContentPanel.this.updateUI();
                ContentPanel.this.getParent().validate();
                ContentPanel.this.getParent().repaint();


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
                     headers = new Object[fileRead.length];
                    for(int col = 0 ; col < fileRead.length ; col++ ){
                        /**/
                        headers[col] = fileRead[col][0];

                    }
                    /*Headers kept on top row, data one row less*/
                    data = new Object[fileRead[0].length-1][fileRead.length];
                    for(int i = 1 ; i < fileRead[0].length ; i++ )for(int col = 0 ; col < fileRead.length; col++){
                        data[i-1][col] = fileRead[col][i];

                    }


                    table = new JTable(data,headers);
                    tableScrollPane = new JScrollPane(table);

                    /*Two scrollpanes currently I think, should check for block*/

                    ContentPanel.this.add(tableScrollPane);
                    ContentPanel.this.repaint();
                    ContentPanel.this.validate();
                    ContentPanel.this.updateUI();



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

        /*1 This concludes the button-specific programming 1*/

        /*2 This starts the JTable´s container and initiate the JTable 2*/



        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(buttonPanel,this.getLayout());
        this.repaint();
        this.validate();


        /*2 This concludes -||- 2*/





    }

    /**This method sets the table´s data to match the incoming data-value-string
     *
     * @param tableInData
     */
    public void setTable(String[][] tableInData){



    }


}
