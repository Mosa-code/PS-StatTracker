import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ParseReplay {
    public String Player1;
    public String Player2;
    public ArrayList<String> P1Pokemon = new ArrayList<>();
    public ArrayList<String> P2Pokemon = new ArrayList<>();
    public HashMap<String, Integer> Kills = new HashMap<>();

    public ParseReplay(String fileName) {
        File Replay = new File(fileName);
        try {
            Scanner scanner = new Scanner(Replay);
            String lastMoveUser = "";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("|player|p1")) {
                    this.Player1 = line.split("\\|")[3];
                } else if (line.startsWith("|player|p2")) {
                    this.Player2 = line.split("\\|")[3];
                } else if (line.startsWith("|poke|p1")) {
                    String pokemonName = line.split("\\|")[3].split(",")[0];
                    this.P1Pokemon.add(pokemonName);
                } else if (line.startsWith("|poke|p2")) {
                    String pokemonName = line.split("\\|")[3].split(",")[0];
                    this.P2Pokemon.add(pokemonName);
                } 
                else if (line.startsWith("|move|")) {
                lastMoveUser = line.split("\\|")[2];
                }   
                
                else if (line.startsWith("|faint|")) { //If the pokemon Fainted, track it. 
                
                if (!Kills.containsKey(lastMoveUser)) {
                    Kills.put(lastMoveUser, 1);
                } else {
                    Kills.put(lastMoveUser, Kills.get(lastMoveUser) + 1);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getPlayer1() {
        return this.Player1;
    }

    public String getPlayer2() {
        return this.Player2;
    }

    public ArrayList<String> getP1Pokemon() {
        return this.P1Pokemon;
    }

    public ArrayList<String> getP2Pokemon() {
        return this.P2Pokemon;
    }

    public HashMap<String, Integer> getKills() {
        return this.Kills;
    }
}
