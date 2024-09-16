public class CSVParser {

    public static void main(String[] args) throws FileNotFoundException {

//  Alternative solution
//  if (args == null || args.length != 1) {
//   System.out.println("syntax is CSVParser <file path>");
//   System.exit(0);
//  }
//  Scanner scanner = new Scanner(new File(args[0]));

        Scanner scanner = new Scanner(System.in);
        System.out.println("---");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
        System.out.println("---");

        scanner.close();
    }
}