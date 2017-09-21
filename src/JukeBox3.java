import java.io.*;
import java.util.ArrayList;
import java.util.*;

/**
 * Created by cherginets-sv on 21.09.2017.
 */
public class JukeBox3 {

    ArrayList<Song> songList = new ArrayList<Song>();
    public static void main(String[] args) {
        new JukeBox3().go();
    }

    public void go() {
        getSongs();
        System.out.println(songList);
        Collections.sort(songList);
        System.out.println(songList);
    }

    public void getSongs() {
        try {
            File file = new File("C:\\Project\\Songs.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {

            }
        } catch (IOException e) {e.printStackTrace();}


    }

    void  addSongs(String lineToParse) {
        String[] tokens = lineToParse.split("/");
        Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);
        songList.add(nextSong);
    }
}
