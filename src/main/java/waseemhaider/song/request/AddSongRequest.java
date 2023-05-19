package waseemhaider.song.request;


import waseemhaider.song.Model.songmodel.Song;

public class AddSongRequest {
   private Song song;

    // Getter and Setter


    public AddSongRequest() {
    }

    public AddSongRequest(Song song) {
        this.song = song;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
