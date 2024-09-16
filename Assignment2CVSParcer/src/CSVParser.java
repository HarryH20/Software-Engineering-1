import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVParser {

    public static void main(String[] args) throws FileNotFoundException {

//  Alternative solution
//  if (args == null || args.length != 1) {
//   System.out.println("syntax is CSVParser <file path>");
//   System.exit(0);
//  }
//  Scanner scanner = new Scanner(new File(args[0]));

       File myFile = new File("workout.csv");
       Scanner myReader = new Scanner(myFile);
       while(myReader.hasNextLine()){
           String data = myReader.nextLine();
           System.out.println(data);
        }
        myReader.close();





    }
}