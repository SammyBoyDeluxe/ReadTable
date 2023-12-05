import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;

public abstract  class FileReader {


    /**
     * Takes a pathname and generates a stringArray.
     *
     *
     * @param pathName
     * @return valuesinFile ; valuesInFile[objectindex][propertynameIndex][value]
     */
    public static String[][] readFile(String pathName) {
        try {
            File inFile = new File(pathName);
            /*This is an array which*/
            String[] nameComponents = pathName.split("\\/");
            /*0Users0 1 ronne2 1 2Desktop2 3Java-programmering (skola)3 4ReadTable4 5Materiallista.csv5*/
            String[] garbagePlusExtension = nameComponents[nameComponents.length - 1].split("\\.");

            switch (garbagePlusExtension[1]) {

                case "csv":
                    return csvRead(inFile);


                case "json":
                    return jsonTableRead(inFile);


                case "xml":
                    xmlRead(inFile);
                    break;

            }

        } catch (Exception e) {
            ImageIcon errorIcon = new ImageIcon("error.png");
            JOptionPane.showConfirmDialog(null, "Error 404 file not found", "ErrorMessage:", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, errorIcon);
        }
        String[][] returnString = new String[2][2];
        return returnString;
    }

    /*Is called by readFile-method, already validated file, will always work - FileNotFound not necessary*/
    private static String[][] csvRead(File csvFile) throws FileNotFoundException {

        Scanner scan = new Scanner(csvFile);
        /*columns rows, rows needs to be on one since we start at zero upon counting*/
        int[] numberofColsAndRows = {0, 1};
        /*Prints the column-titles*/
        boolean isFirstTime = true;
        /*Runs infinity-loop for some reason*/
        while (scan.hasNextLine()) {

            /*We find the number of columns, only need to do it once so we remove the steps from the next block-iteration -> Save RAM*/
            if (isFirstTime) {
                /*The length of this will be the amount of columns, putting the inString here saves space; We only need space for it on if. Ask about call-stacks*/
                String sIn = scan.nextLine();
                String[] sOut = sIn.split(",");
                numberofColsAndRows[0] = sOut.length;
                /*No longer first time*/
                isFirstTime = false;

            }
            /*Called down here since it´´ avoided on iterations without valid check-condition*/


            numberofColsAndRows[1]++;

        }



        /*Creates a structure for all cells in table*/
        String[][] itemsAndTitlesInTable = new String[numberofColsAndRows[0]][numberofColsAndRows[1]];
        /*Keeps check of row currently occupied*/
        int currentRowNmbr = 0;
        /*Should restart the indices of the search, hopefully*/
        scan = new Scanner(csvFile);


        while (scan.hasNextLine()) {
            String sIn = scan.nextLine();


            /*If several it splits to String array with the delimiter ', -> Below is ugly, since we have to divide by three '*/
            String[] separatedItemsOnCurrentLine = sIn.split(",", numberofColsAndRows[0]);


            /*To print quickly Arrays.deepToString(array), has format [element1,element2....,elementN] */
            for (int i = 0; i < numberofColsAndRows[0]; i++) {
                /*Makes sure we don´t apply amout per unit * unit = total amount for third cell-row because previous rows are title-rows, yeaaah*/
                itemsAndTitlesInTable[i][currentRowNmbr] = separatedItemsOnCurrentLine[i];
                /* *//*Makes sure we´re on the third column before multiplying, could count it out on passing thorugh the index-1 column*//*
                if ((((i == (numberofColsAndRows[0] - 1)) && (currentRowNmbr > 1)))) {
                    *//*We don´t need indexOfStartGarbage since we already start at the first number in the amount/units*//*
                    int indexOfStartGarbage;
                    int indexOfEndGarbage = separatedItemsOnCurrentLine[1].indexOf(" ");
                    *//*Find values as String and convert them*//*
                    String amountPerUnitAsString = separatedItemsOnCurrentLine[1].substring(0, indexOfEndGarbage);
                    int amountPerUnitAsInt = Integer.parseInt(amountPerUnitAsString);
                    *//*Find amount per unit*//*

                 *//*Find total amount, the information is contained within the second cell of the table*//*

                 *//*Find out number of units,find out amount/unit-> strip away (x ** )-thing, defining interval where number is contained *//*
                    indexOfStartGarbage = separatedItemsOnCurrentLine[1].indexOf('x');
                    indexOfEndGarbage = separatedItemsOnCurrentLine[1].indexOf(')');
                    String numberOfUnitsAsString = separatedItemsOnCurrentLine[1].substring(indexOfStartGarbage + 1, indexOfEndGarbage);
                    System.out.println(numberOfUnitsAsString);
                    int numberOfUnitsAsInt = Integer.parseInt(numberOfUnitsAsString);
                    *//*Sets the total amount thing, -1 because colNumber = xsize of array*//*
                    System.out.println(amountPerUnitAsInt * numberOfUnitsAsInt);
                    itemsAndTitlesInTable[(numberofColsAndRows[0] - 1)][currentRowNmbr] = Integer.toString(amountPerUnitAsInt * numberOfUnitsAsInt);

*/
                System.out.println(Arrays.deepToString(itemsAndTitlesInTable));


            }
            currentRowNmbr++;
        }
        //return itemsAndTitlesInTable;



        /*If several it splits to String array with the delimiter ','*/

        /*To print quickly Arrays.deepToString(array), has format [element1,element2....,elementN] */

        return itemsAndTitlesInTable;
    }


    private static String[][] jsonTableRead(File jsonFile) throws IOException {
    /*
    JSON-fileformat: Vi excludear ([,]) -> RegEx: ["^\\[\\ (om inte de ligger innanför citationstecken)] "] -> Då har vi alla objekten
    Excludea alla {} så har vi alla objekt med objektnamn först på egen rad sedan följd av propertyname/value par
    demarkerade med komma och radbrytning
    splitta mot komman så går den till kommat, hoppar över och genererar två strängar som är demarkerade av dessa. Måste vara
    med regEx så att vi skippar
    Då har vi alla propertyNames på samma rad som sin propertyValue demarkerade med : , även
    *1 Ta reda på om JTable, finns det någonting inom citationstecken före det första "["

    Make single line->matcher.replaceAll("\\s","");


    Tanke: Hashmap-implementering så att vi mappar rätt kolonntitel till rätt rad och rätt värde för raden?


     */
        /*Generates filetext as whole string with \n */
        String fileRead = Files.readString(jsonFile.getAbsoluteFile().toPath());
        /*This generates a string with no lines.*/
        fileRead.replaceAll("[\\n|\\r]","");
        /*This generates the separated rows*/
        String[] separatedRows = fileRead.split("(\\}\\,)|(\\}\\])");
        /*This gives the amount of rows in the table, +1 where we will have our headers, -10 so it will generate errors if unchanged*/
        int[] numbersOfcolsAndRows = {-10, (separatedRows.length+1)};
        /*This takes away the garbage (},{,[) since we split  before and got rid of the last ], that is that in the row which is not name/value-pairs*/
        String separatedColumnsWithoutGarbage = separatedRows[0].replaceAll("(\\}|\\{|\\[)","");
        String[] propertyNameValuePairs = separatedColumnsWithoutGarbage.split(",");
        /*Sets the number of columns to the number of property-valuue/Property-name pairs*/
        numbersOfcolsAndRows[0] = propertyNameValuePairs.length;
        /*Generate a string-array with appropriate space for our file-read*/
        String[][] headersAndItemsInTable = new String[numbersOfcolsAndRows[0]][numbersOfcolsAndRows[1]];
        /*For each propertyNameValue pair we need two spaces, one for name, one for value so 2*length should suffice*/
        String[] separatedPropertyNameValuePairs = new String[2*propertyNameValuePairs.length];
        String[] result;
        int counter = 0;
        /*This for loop separates propertyNameValuePairs into its constituent name & value and adds it to the separated-||- array*/
        for(int i = 0 ; i < propertyNameValuePairs.length ; i++){
            result = propertyNameValuePairs[i].split(":",numbersOfcolsAndRows[0]*2);
            separatedPropertyNameValuePairs[counter] = result[0];
            separatedPropertyNameValuePairs[counter+1] = result[1];
            counter += 2;


        }
        for(int i = 0 ; i < separatedPropertyNameValuePairs.length ; i++){

            if((i+2) % 2 == 0){
                headersAndItemsInTable[(i/2)][0] = separatedPropertyNameValuePairs[i];


            }
            else{
                headersAndItemsInTable[(i+2)%3][1] = separatedPropertyNameValuePairs[i];


            }

        }


        /*In the first row we want to place our headers, then in the second their corresponding values building our table-structure*/


        /**/
        /*Since we handled one row we don´t need to handle that in the for-loop*/
        for(int row = 2 ; row < numbersOfcolsAndRows[1] ; row++){
            separatedColumnsWithoutGarbage = separatedRows[row-1].replaceAll("(\\}|\\{|\\[)","");
            propertyNameValuePairs = separatedColumnsWithoutGarbage.split(",");
            /*Sets the number of columns to the number of property-valuue/Property-name pairs*/


            /*For each propertyNameValue pair we need two spaces, one for name, one for value so 2*length should suffice*/
            // separatedPropertyNameValuePairs;

             counter = 0;
            /*This for loop separates propertyNameValuePairs into its constituent name & value and adds it to the separated-||- array*/
            for(int i = 0 ; i < propertyNameValuePairs.length ; i++){
                result = propertyNameValuePairs[i].split(":",numbersOfcolsAndRows[0]*2);
                separatedPropertyNameValuePairs[counter] = result[0];
                separatedPropertyNameValuePairs[counter+1] = result[1];
                counter += 2;


            }

            counter = 0;
            for(int i = 1 ; i < separatedPropertyNameValuePairs.length ; i += 2){


                    headersAndItemsInTable[(counter)][row] = separatedPropertyNameValuePairs[i];
                    counter++;



            }



        }

        /*Generates an array of probable */

        return headersAndItemsInTable;











































    }

    private static void xmlRead(File xmlFile) {


    }
}




