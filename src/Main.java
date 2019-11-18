import org.jbibtex.*;

import java.io.*;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int userChoice;

    public static void main(String[] args) {
        startMenu();
    }

    // Brings up the start menu
    public static void startMenu() {
        System.out.println("Welcome to BibTex Manager Prototype");
        System.out.println("Please select what option you will be performing...");
        System.out.println("1. Creating a bib file.");
        System.out.println("2. Reading a bib file");
        System.out.println("3. Updating a bib file.");
        System.out.println("4. Deleting a bib file.");

        userInput();

        // switch-case for user input
        switch (userChoice) {
            case 1:
                createMenu();
                break;

            case 2:
                readMenu();
                break;

//            case 3:
//                updateMenu();
//                break;
//
//            case 4:
//                deleteMenu();
//                break;

            default:
                System.err.println("\n\nPlease enter a number in range!\n");
                startMenu();
                break;
        }
    }

    // Create menu is brought to the screen if the user enters '1'
    public static void createMenu() {
        System.out.println("Please select what your entry type will be...");
        System.out.println("1. Article");
        System.out.println("2. Book");
        System.out.println("3. Booklet");
        System.out.println("4. Conference");
        System.out.println("5. In Book");
        System.out.println("6. In Collection");
        System.out.println("7. In Proceedings");
        System.out.println("8. Manual");
        System.out.println("9. Masters Thesis");
        System.out.println("10. Misc");
        System.out.println("11. PhD Thesis");
        System.out.println("12. Proceedings");
        System.out.println("13. Tech Report");
        System.out.println("14. Unpublished");

        userInput();

        String author, title, journal, year, publisher, bookTitle;

        // The user will enter values and they will be passed to their related entry type function such as article
        switch (userChoice) {
            case 1:
                System.out.print("Author: ");
                author = scanner.nextLine();
                System.out.println();

                System.out.print("Title: ");
                title = scanner.nextLine();
                System.out.println();

                System.out.print("Journal: ");
                journal = scanner.nextLine();
                System.out.println();

                System.out.print("Year: ");
                year = scanner.nextLine();
                System.out.println();

                createArticleBib(author, title, journal, year);
                break;

            case 2:
                System.out.print("Author (Editor): ");
                author = scanner.nextLine();
                System.out.println();

                System.out.print("Title: ");
                title = scanner.nextLine();
                System.out.println();

                System.out.print("Publisher: ");
                publisher = scanner.nextLine();
                System.out.println();

                System.out.print("Year: ");
                year = scanner.nextLine();
                System.out.println();

                createBookBib(author, title, publisher, year);
                break;

            case 3:
                System.out.print("Title: ");
                title = scanner.nextLine();
                System.out.println();

                createBookletBib(title);
                break;

            case 4:
                System.out.print("Author: ");
                author = scanner.nextLine();
                System.out.println();

                System.out.print("Title: ");
                title = scanner.nextLine();
                System.out.println();

                System.out.print("Book Title: ");
                bookTitle = scanner.nextLine();
                System.out.println();


                System.out.print("Year: ");
                year = scanner.nextLine();
                System.out.println();

                createConferenceBib(author, title, bookTitle, year);
                break;

            default:
                System.err.println("Only the first 4 options work for now.");
                createMenu();
                break;
        }
    }

    // Creates an article type entry. The details are explained inside the function
    public static void createArticleBib(String author, String title, String journal, String year) {
        try {
            // A file is created with the exact location of the bib file
            // @@@ You have to change this path in order to be able to save.
            File file = new File("C:\\Users\\oguzs\\IdeaProjects\\SE302_Practice\\src\\tobewritten.bib");
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file, true)
            );

            // A new database is created
            org.jbibtex.BibTeXDatabase database = new BibTeXDatabase();
            // A formatter is created. This formatter will take the contents of the database and write them to a file.
            org.jbibtex.BibTeXFormatter formatter = new org.jbibtex.BibTeXFormatter();

            // Key class must be used to pass values into BibTexEntry constructor
            Key type = BibTeXEntry.TYPE_ARTICLE;

            // A unique key is generated for each entry
            Key key = new Key(author + title + year);

            // A new entry is created with type and key but it has no fields inside
            BibTeXEntry newEntry = new BibTeXEntry(type, key);

            // Fields are being added to the new entry created
            newEntry.addField(BibTeXEntry.KEY_AUTHOR, new StringValue(author, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_TITLE, new StringValue(title, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_JOURNAL, new StringValue(journal, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_YEAR, new StringValue(year, StringValue.Style.BRACED));

            // Each entry is considered an object and they are added to the database
            database.addObject(newEntry);

            // formatter.format functions takes the database and the writer to write the contents of the database to the
            // specified file
            formatter.format(database, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //The details of adding a new entry are explained inside the createArticleBib function
    public static void createBookBib(String author, String title, String publisher, String year) {
        try {
            // @@@ You have to change this path in order to be able to save.
            File file = new File("C:\\Users\\oguzs\\IdeaProjects\\SE302_Practice\\src\\tobewritten.bib");
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file, true)
            );

            org.jbibtex.BibTeXDatabase database = new BibTeXDatabase();
            org.jbibtex.BibTeXFormatter formatter = new org.jbibtex.BibTeXFormatter();

            Key type = BibTeXEntry.TYPE_BOOK;
            Key key = new Key(author + title + publisher);

            BibTeXEntry newEntry = new BibTeXEntry(type, key);

            newEntry.addField(BibTeXEntry.KEY_AUTHOR, new StringValue(author, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_TITLE, new StringValue(title, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_PUBLISHER, new StringValue(publisher, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_YEAR, new StringValue(year, StringValue.Style.BRACED));

            database.addObject(newEntry);

            formatter.format(database, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //The details of adding a new entry are explained inside the createArticleBib function
    public static void createBookletBib(String title) {
        try {
            // @@@ You have to change this path in order to be able to save.
            File file = new File("C:\\Users\\oguzs\\IdeaProjects\\SE302_Practice\\src\\tobewritten.bib");
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file, true)
            );

            org.jbibtex.BibTeXDatabase database = new BibTeXDatabase();
            org.jbibtex.BibTeXFormatter formatter = new org.jbibtex.BibTeXFormatter();

            Key type = BibTeXEntry.TYPE_BOOKLET;
            Key key = new Key(title + (Calendar.getInstance().get(Calendar.YEAR)));

            BibTeXEntry newEntry = new BibTeXEntry(type, key);

            newEntry.addField(BibTeXEntry.KEY_TITLE, new StringValue(title, StringValue.Style.BRACED));

            database.addObject(newEntry);

            formatter.format(database, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //The details of adding a new entry are explained inside the createArticleBib function
    public static void createConferenceBib(String author, String title, String bookTitle, String year) {
        try {
            // @@@ You have to change this path in order to be able to save.
            File file = new File("C:\\Users\\oguzs\\IdeaProjects\\SE302_Practice\\src\\tobewritten.bib");
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file, true)
            );

            org.jbibtex.BibTeXDatabase database = new BibTeXDatabase();
            org.jbibtex.BibTeXFormatter formatter = new org.jbibtex.BibTeXFormatter();

            Key type = BibTeXEntry.TYPE_CONFERENCE;
            Key key = new Key(author + title + bookTitle);

            BibTeXEntry newEntry = new BibTeXEntry(type, key);

            newEntry.addField(BibTeXEntry.KEY_AUTHOR, new StringValue(author, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_TITLE, new StringValue(title, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_BOOKTITLE, new StringValue(bookTitle, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_YEAR, new StringValue(year, StringValue.Style.BRACED));

            database.addObject(newEntry);

            formatter.format(database, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // The details are described inside the function
    public static void readMenu() {
       while (true) {
           System.out.println("Please enter the full path of the bib file you want to read (-1 to exit): ");
           // The path of the file is saved inside bibFilePath
           String bibFilePath = scanner.nextLine();
           System.out.println();

           if (bibFilePath.equals("-1"))
               break;

           try {

               // A file with the exact location of the bib file is created. The location is stored in bibFilePath
               File file = new File(bibFilePath);

               // A reader created to read the contents of the bib file.
               BufferedReader reader = new BufferedReader(
                       // Just passing the file into the reader makes the reader have all the contents of the file
                       new FileReader(file)
               );

               // A BibTex Database is created here. org.jbibtex part can be omitted
               // This database is just a variable that stores all the values of the file
               org.jbibtex.BibTeXDatabase bibTeXDatabase;

               // BibTex Parser created here.
               org.jbibtex.BibTeXParser bibTeXParser = new org.jbibtex.BibTeXParser();
               //Database initialized to the parsed version of the reader/file by using the bibTexParser created above.
               bibTeXDatabase = bibTeXParser.parse(reader);

               // Each BibTex Entry can be mapped to their key with the code below.
               // Map<Key, BibTeXEntry> myMap = bibTeXDatabase.getEntries();

               // A BibTex Entry collection is created and it includes all the entries within that specific file
               Collection<BibTeXEntry> entries = bibTeXDatabase.getEntries().values();

               // This for loop loops through each entry in the bib file
               for (BibTeXEntry entry: entries) {

                   // @@@ IMPORTANT PART @@@
                   // Every field of each entry is mapped as a key, value pair
                   Map<Key, Value> allFields = entry.getFields();
                   // Type of the entry is printed before each entry
                   System.out.println("Entry Type: " + entry.getType());
//                    For each field in an entry we loop through them and get them as key, value pairs and print them.
//                    author (key) = J. R. R. Tolkien (value)
                   allFields.forEach((key, value) -> System.out.println("\t" + key + " = " + value.toUserString()));

                   System.out.println();
               }

               reader.close();
               break;

           } catch (org.jbibtex.ParseException e) {
               System.err.println("The BibTex file format is not correct. Please check your file.");
           } catch (IOException e) {
               System.err.println("There is an error related to the bib file. Please check the location of the bib file");
           }
       }
    }

    // This function is called whenever there will be an integer choice given by the user
    public static void userInput() {
        while(true) {
            try {
                System.out.print("\nYour choice: ");
                userChoice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Your input must be an integer within the range!");
            }
        }
    }
}
