package Lab10;

import Lab10.Game.Room;
import Lab10.Game.VirtualEscapePuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Main
{
    private static final Scanner IN = new Scanner(System.in);
    private static final int HELP_BOX_WIDTH = 30;
    private static final int GAME_BOX_WIDTH = 60;

    public static void main(String[] args)
    {
        // --no-modern for old consoles
        if (Arrays.stream(args).anyMatch(arg -> arg.equalsIgnoreCase("--no-modern") || arg.equalsIgnoreCase("-nm")))
            ConsoleUtils.setModernMode(false);

        VirtualEscapePuzzle escapePuzzle = new VirtualEscapePuzzle();

        // populate with 1 room
        escapePuzzle.addRoom(new Room("The Tower", "What has hands but cannot hold a thing?", "Clock"));

        printMenu();

        while (true) { if (!runGameLoopIteration(escapePuzzle)) break; }
    }

    /// @return true if the game loop should continue, false if it should end
    private static boolean runGameLoopIteration(VirtualEscapePuzzle escapePuzzle)
    {
        MenuOption option = getMenuOption();
        ConsoleUtils.oneLineSpace();

        switch (option)
        {
            case HELP -> printMenu();
            case CREATE_ROOM -> createRoom(escapePuzzle);
            case SOLVE_ROOM -> solveRoom(escapePuzzle);
            case VIEW_UNSOLVED_ROOMS -> viewRoom(escapePuzzle.getUnsolvedRooms(), "Unsolved Rooms");
            case VIEW_SOLVED_ROOMS -> viewRoom(escapePuzzle.getSolvedRooms(), "Solved Rooms");
            case VIEW_ALL_ROOMS -> viewRoom(escapePuzzle.getAllRooms(), "Rooms");
            case EXIT ->
            {
                ConsoleUtils.printBox(
                        "Goodbye!",
                        new String[]{"+ Thanks for playing the Virtual Escape Puzzle!"},
                        GAME_BOX_WIDTH
                );
                return false;
            }
            default -> System.out.printf(
                    "> Invalid input. Please enter a number between %d and %d.%n",
                    MenuOption.firstIndex(),
                    MenuOption.lastIndex()
            );
        }

        ConsoleUtils.oneLineSpace();

        return true;
    }

    /* MENU OPTIONS */

    private static void printMenu()
    {
        final String[] options = MenuOption.getAllString();
        ConsoleUtils.printBox("Game Menu", options, HELP_BOX_WIDTH);
        ConsoleUtils.oneLineSpace();
    }

    private static void createRoom(VirtualEscapePuzzle escapePuzzle)
    {
        ConsoleUtils.printBox(
                "Room Creation",
                new String[]{"+ Answer the following questions to add a room"},
                GAME_BOX_WIDTH
        );
        ConsoleUtils.oneLineSpace();
        Room room = escapePuzzle.createRoom(
                getInputFromUser("Enter Room Name"),
                getInputFromUser("Enter Room Puzzle"),
                getInputFromUser("Enter Room Answer")
        );
        escapePuzzle.addRoom(room);
    }

    private static void solveRoom(VirtualEscapePuzzle escapePuzzle)
    {
        final ArrayList<String> options = new ArrayList<>();
        options.add("+ Enter the room name and your answer to solve a room");
        // populate options with unsolved room names
        options.addAll(escapePuzzle.getUnsolvedRooms()
                .stream()
                .map(Room::getRoomName)
                .map(name -> "  - " + name)
                .toList());

        ConsoleUtils.printBox("Solve Room", options.toArray(new String[0]), GAME_BOX_WIDTH);

        ConsoleUtils.oneLineSpace();
        String roomName = getInputFromUser("Enter Room Name");
        Optional<Room> room = escapePuzzle.findRoomByName(roomName);
        if (room.isPresent())
        {
            System.out.println(room.get().getRoomPuzzle());
        }
        else
        {
            System.out.println("Room not found. Please check the room name and try again.");
            return;
        }

        String roomAnswer = getInputFromUser("Enter Room Answer");
        switch (escapePuzzle.solveRoom(roomName, roomAnswer))
        {
            case WRONG_ANSWER -> System.out.println("Wrong answer. Try again.");
            case ROOM_NOT_FOUND -> System.out.println("Room not found.");
            case NO_ERROR -> System.out.println("Congratulations! You've solved the room.");
        }
    }

    private static void viewRoom(ArrayList<Room> rooms, String title)
    {
        if (rooms.isEmpty())
        {
            System.out.printf("No %s found.%n%n", title);
            return;
        }
        ConsoleUtils.printBox(
                title,
                rooms.stream().map(room -> "+ " + room.getRoomName()).toArray(String[]::new),
                GAME_BOX_WIDTH
        );
        ConsoleUtils.oneLineSpace();
    }

    /* HELPERS */

    private static String getInputFromUser(String prompt)
    {
        System.out.print(prompt + ": ");
        return IN.nextLine();
    }

    /* MENU SELECTION */

    private static MenuOption getMenuOption()
    {
        final int option;
        System.out.printf(
                "Select an option between %d and %d (%d for help): ",
                MenuOption.firstIndex(),
                MenuOption.lastIndex(),
                MenuOption.HELP.getIndex()
        );
        try { option = Integer.parseInt(IN.nextLine()); }
        catch (Exception e) { return MenuOption.INVALID; }
        return MenuOption.fromIndex(option);
    }

    enum MenuOption
    {
        HELP(0),
        CREATE_ROOM(1),
        SOLVE_ROOM(2),
        VIEW_UNSOLVED_ROOMS(3),
        VIEW_SOLVED_ROOMS(4),
        VIEW_ALL_ROOMS(5),
        EXIT(6),
        INVALID(-1);
        private final int index;

        MenuOption(int index) { this.index = index; }

        /* FACTORY */

        public static MenuOption fromIndex(int index)
        {
            for (MenuOption option : values())
                if (option.index == index) return option;
            return MenuOption.INVALID;
        }

        /* GETTERS & STATICS */

        public int getIndex() { return index; }

        public static int firstIndex() { return HELP.getIndex(); }

        public static int lastIndex() { return EXIT.getIndex(); }

        /* GET ALL */

        public static MenuOption[] getAll()
        {
            return Arrays.stream(values()).filter(option -> option != INVALID).toArray(MenuOption[]::new);
        }

        public static String[] getAllString()
        {
            // 1. getAll
            // 2. make all title format (only first capital)
            // 3. return
            return Arrays.stream(getAll()).map(MenuOption::getAllFormat).toArray(String[]::new);
        }

        private static String getAllFormat(MenuOption option)
        {
            return option.getIndex() + ". " + option.name().charAt(0) + option.name()
                    .substring(1)
                    .toLowerCase()
                    .replace("_", " ");
        }
    }
}
