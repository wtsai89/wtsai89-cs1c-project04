package queues;

import cs1c.SongEntry;
import java.util.Scanner;
import java.io.*;
import java.io.FileNotFoundException;

/**
 * Manages three playlists in the Queue format
 */
public class Jukebox {
    private Queue<SongEntry> favoritePL, roadTripPL, loungePL;

    /**
     * default constructor
     */
    public Jukebox()
    {
        favoritePL = new Queue<SongEntry>("favorites");
        roadTripPL = new Queue<SongEntry>("road trip");
        loungePL = new Queue<SongEntry>("lounge");
    }

    /**
     * Reads the test file and then adds songs to one of the three queues
     * @param requestFile
     * @param allSongs
     */
    public void fillPlaylists(String requestFile, SongEntry[] allSongs)
    {
        try
        {
            File file = new File(requestFile);
            Scanner input = new Scanner(file);

            while(input.hasNextLine())
            {
                String[] line = ((input.nextLine()).split(","));    //split line into playlist and song name
                SongEntry song = findSong(line[1], allSongs);
                if(song == null)
                {
                    System.out.println(line[1] + " not found");
                }
                else
                {
                    switch(line[0]) {
                        case "favorites": favoritePL.enqueue(song);
                            break;
                        case "road trip": roadTripPL.enqueue(song);
                            break;
                        case "lounge": loungePL.enqueue(song);
                            break;
                        default: System.out.println("Invalid Playlist");
                            break;
                    }
                }
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File not found");
        }
    }

    /**
     * Searches the entire list for the first song that matches the title
     * @param songName
     * @param list
     * @return
     */
    public SongEntry findSong(String songName, SongEntry[] list)
    {
        for(int i = 0; i < list.length; i++)
        {
            if(songName.equals(list[i].getTitle()))
                return list[i];
        }
        return null;
    }

    /**
     * Accessor method for favoritePL
     * @return
     */
    public Queue<SongEntry> getFavoritePL() { return favoritePL; }

    /**
     * Accessor method for roadTripPL
     * @return
     */
    public Queue<SongEntry> getRoadTripPL() { return roadTripPL; }

    /**
     * Accessor method for loungePL
     * @return
     */
    public Queue<SongEntry> getLoungePL() { return loungePL; }

}
