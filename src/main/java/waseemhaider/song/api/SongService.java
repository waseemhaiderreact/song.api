package waseemhaider.song.api;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.apache.tomcat.websocket.WsPongMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import waseemhaider.song.Model.songmodel.Song;
import waseemhaider.song.Model.songmodel.SongRepository;
import waseemhaider.song.request.AddSongRequest;
import waseemhaider.song.request.updateSongRequest;
import waseemhaider.song.response.DefaultResponse;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

@Service
@Slf4j
public class SongService {

    @Autowired
    SongRepository songRepository;



    @Autowired
    EntityManager entityManager;

    public DefaultResponse addSong(AddSongRequest request){
        DefaultResponse response=null;
        try {
            songRepository.save(request.getSong());
            response=new DefaultResponse("Successful","Song has been added ","200");

        }catch (Exception e){
            response=new DefaultResponse("Failed"+e.getMessage(),"Song has been added not ","500");
        }
        return response;
    }

    public DefaultResponse updateSong(updateSongRequest updateSongRequest){
        DefaultResponse response=null;
        Song song;
        try {

            song=songRepository.findSongById(updateSongRequest.getSong().getId());

            // setting value in song

            song.setAlbum(updateSongRequest.getSong().getAlbum());
            song.setArtist(updateSongRequest.getSong().getArtist());
            song.setDuration(updateSongRequest.getSong().getDuration());
            song.setGenre(updateSongRequest.getSong().getGenre());
            song.setTitle(updateSongRequest.getSong().getTitle());
            song.setYear(updateSongRequest.getSong().getYear());
            song.setLyrics(updateSongRequest.getSong().getLyrics());

            // saving in database
            songRepository.save(song);
            response=new DefaultResponse("Successful","song has been Update ","200");

        }catch (Exception e){
            response=new DefaultResponse("Error"+e.getMessage(),"an error while update song","500");
        }
        return response;
    }

    public DefaultResponse deleteSong(Long id){
        DefaultResponse response=null;
        try {
            songRepository.deleteById(id);
            response=new DefaultResponse("Successful","song has been deleted","200");
        }catch (Exception e){
            response=new DefaultResponse("Successful","song has been not deleted","500");
        }
        return response;
    }

    public Page<Song> searchSong(String query,int page,int size) throws Exception {

        Page<Song> songs=null;
        Pageable pageable;
        try {
            pageable= PageRequest.of(page,size, Sort.unsorted());

            songs= songRepository.findSongByAlbum(query,pageable);

        }catch (Exception e)
        {
            log.error(e.getMessage());
            throw new Exception("no Record Found ");
        }

        return songs;
    }


    public Page<Song> AdvanceSearch(String query,int page,int size)
    {
        Pageable pageable;
        pageable= PageRequest.of(page,size,Sort.unsorted());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Song> criteriaQuery = criteriaBuilder.createQuery(Song.class);
        List<Song> songList;
        Root<Song> root = criteriaQuery.from(Song.class);
        List<Predicate> classes=new ArrayList<>();
        classes.add(criteriaBuilder.equal(root.get("album"),query));

        criteriaQuery.where(criteriaBuilder.and(classes.toArray(new Predicate[0])));

        criteriaQuery.select(criteriaBuilder.construct(Song.class,root.get("title"),root.get("artist"),root.get("duration"),root.get("genre"),root.get("year"),root.get("album"),root.get("lyrics")));

        songList = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Song> rootCount = countQuery.from(Song.class);
        countQuery.select(criteriaBuilder.count(rootCount)).where(criteriaBuilder.and(classes.toArray(new Predicate[0])));
        Long count = entityManager.createQuery(countQuery).getSingleResult();
        //get data for invoice's shipment
        return new PageImpl<>(songList, pageable, count);

    }

}
