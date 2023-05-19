package waseemhaider.song.Model.songmodel;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
public class Song {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;
        private String artist;
        private int duration;
        private String genre;
        private int year;
        private String album;
        private String lyrics;

        @Lob
        private byte[] image;


        // Constructors, getters, and setters

        public Song() {
        }

        public Song(String title, String artist, int duration, String genre, int year, String album, String lyrics) {
            this.title = title;
            this.artist = artist;
            this.duration = duration;
            this.genre = genre;
            this.year = year;
            this.album = album;
            this.lyrics = lyrics;
        }


        // Getters and setters


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        public String getLyrics() {
            return lyrics;
        }

        public void setLyrics(String lyrics) {
            this.lyrics = lyrics;
        }


}
