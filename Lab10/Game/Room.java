package Lab10.Game;

public class Room
{
    private final String roomName;
    private final String roomPuzzle;
    private final String answer;

    /// <b>{@code INTERNAL}</b>
    boolean solved = false;

    public Room(String roomName, String roomPuzzle, String answer)
    {
        this.roomName = roomName;
        this.roomPuzzle = roomPuzzle;
        this.answer = answer;
    }

    public boolean validSolution(String answer) { return this.answer.equalsIgnoreCase(answer); }

    /* GETTERS */

    public String getRoomName() { return roomName; }

    public String getRoomPuzzle() { return roomPuzzle; }

    public String getAnswer() { return answer; }

    /// <b>{@code INTERNAL}</b>
    void setSolved() { this.solved = true; }

    /// <b>{@code INTERNAL}</b>
    boolean isSolved() { return this.solved; }
}
