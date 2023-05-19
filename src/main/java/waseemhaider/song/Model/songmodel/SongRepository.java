package waseemhaider.song.Model.songmodel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,Long> {

    Song findSongById(@Param("id") Long id);


    Page<Song> findSongByAlbum(String query, Pageable pageable);

}
