import javax.print.DocFlavor;
import java.io.File;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main {


    public static void main(String[] args) {
        GUI graphicalUserInterface = new GUI();
        String[][] fileRead = FileReader.readFile("src/Materiallista.json");
        for(String[] s : fileRead){
            for(String s2 : s){
                System.out.println(s2);

            }

        }
    }


        /*try {
            File csvFile = new File("Materiallista.csv");
            Scanner scan = new Scanner(csvFile);
            *//*columns rows, rows needs to be on one since we start at zero upon counting*//*
            int[] colAndRowNumbers = {0, 1};
            *//*Prints the column-titles*//*
            boolean isFirstTime = true;
            *//*Runs infinity-loop for some reason*//*
            while (scan.hasNextLine()) {

                *//*We find the number of columns, only need to do it once so we remove the steps from the next block-iteration -> Save RAM*//*
                if (isFirstTime) {
                    *//*The length of this will be the amount of columns, putting the inString here saves space; We only need space for it on if. Ask about call-stacks*//*
                    String sIn = scan.nextLine();
                    String[] sOut = sIn.split(",");
                    colAndRowNumbers[0] = sOut.length;
                    *//*No longer first time*//*
                    isFirstTime = false;

                }
                *//*Called down here since it´´ avoided on iterations without valid check-condition*//*
                if (!isFirstTime) scan.nextLine();




                colAndRowNumbers[1]++;

            }



            *//*Creates a structure for all cells in table*//*
            String[][] itemsAndTitlesInTable = new String[colAndRowNumbers[0]][colAndRowNumbers[1]];
            *//*Keeps check of row currently occupied*//*
            int currentRowNmbr = 0;
            *//*Should restart the indices of the search, hopefully*//*
            scan = new Scanner(csvFile);
            *//*
            new String("Hej")


             *//*
            *//*
            (....11(0/1))[1232]
            (1010100001110....0(1)) = minnesceller -> MC: 1 | 0 storeat -> Adresser
            Referensvariabel: {#Adress #64 MC  }
            int y = 3;
            int x = y;




             *//*



            while (scan.hasNextLine()) {
                String sIn = scan.nextLine();


                *//*If several it splits to String array with the delimiter ', -> Below is ugly, since we have to divide by three '*//*
                String[] itemsOnCurrentLine = sIn.split(",", colAndRowNumbers[0]);


                *//*To print quickly Arrays.deepToString(array), has format [element1,element2....,elementN] *//*
                for (int i = 0; i < colAndRowNumbers[0]; i++) {
                    *//*Makes sure we don´t apply amout per unit * unit = total amount for third cell-row because previous rows are title-rows, yeaaah*//*
                    itemsAndTitlesInTable[i][currentRowNmbr] = itemsOnCurrentLine[i];
                    *//*Makes sure we´re on the third column before multiplying, could count it out on passing thorugh the index-1 column*//*
                    if ((((i == (colAndRowNumbers[0] - 1)) && (currentRowNmbr > 1)))) {
                        *//*We don´t need indexOfStartGarbage since we already start at the first number in the amount/units*//*
                        int indexOfStartGarbage;
                        int indexOfEndGarbage = itemsOnCurrentLine[1].indexOf(" ");
                        *//*Find values as String and convert them*//*
                        String amountPerUnitAsString = itemsOnCurrentLine[1].substring(0, indexOfEndGarbage);
                        int amountPerUnitAsInt = Integer.parseInt(amountPerUnitAsString);
                        *//*Find amount per unit*//*

                        *//*Find total amount, the information is contained within the second cell of the table*//*

                        *//*Find out number of units,find out amount/unit-> strip away (x ** )-thing, defining interval where number is contained *//*
                        indexOfStartGarbage = itemsOnCurrentLine[1].indexOf('x');
                        indexOfEndGarbage = itemsOnCurrentLine[1].indexOf(')');
                        String numberOfUnitsAsString = itemsOnCurrentLine[1].substring(indexOfStartGarbage + 1, indexOfEndGarbage);
                        System.out.println(numberOfUnitsAsString);
                        int numberOfUnitsAsInt = Integer.parseInt(numberOfUnitsAsString);
                        *//*Sets the total amount thing, -1 because colNumber = xsize of array*//*
                        System.out.println(amountPerUnitAsInt * numberOfUnitsAsInt);
                        itemsAndTitlesInTable[(colAndRowNumbers[0] - 1)][currentRowNmbr] = Integer.toString(amountPerUnitAsInt * numberOfUnitsAsInt);


                        System.out.println(itemsAndTitlesInTable[i][currentRowNmbr]);


                    }

                }
                currentRowNmbr++;



                *//*If several it splits to String array with the delimiter ','*//*

                *//*To print quickly Arrays.deepToString(array), has format [element1,element2....,elementN] *//*

            }

            for (int i = 0; i < colAndRowNumbers[1]; i++)
                for (int k = 0; k < colAndRowNumbers[0]; k++) {

                    System.out.println(itemsAndTitlesInTable[i][k]);


                }


        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());


        }


    }*/
}
