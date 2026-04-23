package Lab10.Game;

import java.util.ArrayList;
import java.util.Optional;

public class VirtualEscapePuzzle
{
    private final ArrayList<Room> rooms = new ArrayList<>();

    public Room createRoom(String roomName, String roomPuzzle, String answer)
    {
        return new Room(roomName, roomPuzzle, answer);
    }

    public void addRoom(Room room) { rooms.add(room); }

    public Optional<Room> findRoomByName(String name)
    {
        return rooms.stream().filter(room -> room.getRoomName().equalsIgnoreCase(name)).findFirst();
    }

    public RoomSolvedError solveRoom(String roomName, String answer)
    {
        return findRoomByName(roomName).map(room -> {
            boolean solved = room.validSolution(answer);
            if (solved) room.setSolved();
            return solved ? RoomSolvedError.NO_ERROR : RoomSolvedError.WRONG_ANSWER;
        }).orElse(RoomSolvedError.ROOM_NOT_FOUND);
    }

    public ArrayList<Room> getUnsolvedRooms()
    {
        return rooms.stream()
                .filter(room -> !room.isSolved())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public ArrayList<Room> getSolvedRooms()
    {
        return rooms.stream().filter(Room::isSolved).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public ArrayList<Room> getAllRooms() { return rooms; }

    /* UTILITIES */

    public enum RoomSolvedError
    {
        WRONG_ANSWER,
        ROOM_NOT_FOUND,
        NO_ERROR;
    }
}
