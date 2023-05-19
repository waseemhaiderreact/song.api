package waseemhaider.song.request;

import waseemhaider.song.Model.songmodel.Song;

public class updateSongRequest {
    private Song song;

    public updateSongRequest() {
    }

    public updateSongRequest(Song song) {
        this.song = song;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
