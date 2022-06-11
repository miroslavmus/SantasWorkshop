import actors.Dwarf;
import actors.MagicBoard;
import actors.Santa;
import actors.Sled;
import magic.Command;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var board = setupBoard();
        setupDwarves(board);

        var scanner = new Scanner(System.in);
        var input = "";

        firstTimeGreet();

        do {

            persistentMessage();

            input = scanner.nextLine();

            // parse/read input
            switch (input) {

                case "stop":
                    toyListing();
                    return;

                case "stash":
                    toyListing();
                    continue;

                case "help":
                    clear();
                    manual();
                    continue;

                //if not command, then try extract command
                default:
                    var command = ForgottenMagicFactory.make(input);

                    if (command != null) {

                        commandExecute(command);
                        continue;
                    }
            }

            error();

        } while (true);
    }

    private static void toyListing() {

        clear();
        for (var toy : Sled.shared().getToys()) {

            System.out.println(toy.description());
        }
        System.out.println("--end of toy list...");
    }

    public static MagicBoard setupBoard() {

        var board = new MagicBoard();
        Santa.shared().addSubscriber(board);

        return board;
    }

    public static void setupDwarves(MagicBoard board) {

        for (int i = 0; i < 5; i++) {

            var dwarf = new Dwarf();
            board.addObserver(dwarf);
            dwarf.addSubscriber(Sled.shared());
        }
    }

    private static void error() {

        System.out.println("""
                    
                    Invalid command or command.
                    Try again!
                    """);
    }

    private static void commandExecute(Command command) {

        clear();
        Santa.shared().utterForgottenMagic(command);
    }

    private static void persistentMessage() {

        System.out.println("""
                Type spells and commands then execute with enter!
                -> "help" to show help screen;
                """);
    }

    private static void firstTimeGreet() {

        clear();
        System.out.println("""
                Welcome!
                This is the Santa Claus shell program...
                """);
        manual();
    }

    private static void manual() {

        System.out.println("""
                Type a command to order toys.
                
                Spells include:
                -> "bike" to get bikes;
                -> "doll" to get dolls;
                
                Commands include:
                -> "stash" to show stored toys;
                -> "stop" to exit the program;
                -> "help" to see this screen;
                
                Enter to execute.
                """);
    }

    private static void clear() {

        //currently we do not support clearing the console for debug reasons
    }

    private static class ForgottenMagicFactory {

        public static Command make(String string) {

            return switch (string) {

                case "bike" -> new Command.NeedBicycle();
                case "doll" -> new Command.NeedDoll();
                default -> null;
            };
        }
    }
}
