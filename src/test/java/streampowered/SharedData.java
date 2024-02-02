package streampowered;

public final class SharedData {
    private static String nameOfGame;
    
    public static String getNameOfGame() {
        return nameOfGame;
    }
    
    public static void setNameOfGame(String nameOfGame) {
        SharedData.nameOfGame = nameOfGame;
    }
}
