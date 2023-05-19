package waseemhaider.song.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import waseemhaider.song.Model.songmodel.Song;
import waseemhaider.song.request.AddSongRequest;
import waseemhaider.song.request.updateSongRequest;
import waseemhaider.song.response.DefaultResponse;


@RestController
@Slf4j
@RequestMapping("/song")
public class SongController {


    @Autowired
 SongService songService;

    @PostMapping("/add")
    public ResponseEntity<DefaultResponse> saveSong(@RequestBody AddSongRequest addSongRequest) throws Exception {
        ResponseEntity<DefaultResponse> responseEntity = null;
        try {
            responseEntity = new ResponseEntity(songService.addSong(addSongRequest), HttpStatus.OK);
        }  catch (Exception e) {
            responseEntity = new ResponseEntity(new DefaultResponse("FAILURE"+e.getMessage(), "ERROR IN CONTACT SAVING AGAINST MULTIPLE LOCATIONS", "F001"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/update")
    public ResponseEntity<DefaultResponse> updateSong(@RequestBody updateSongRequest request){
        ResponseEntity<DefaultResponse> responseEntity=null;
        try {
            responseEntity=new ResponseEntity(songService.updateSong(request),HttpStatus.OK);

        }catch (Exception e){
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
           log.error(e.getMessage());
        }
        return responseEntity;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DefaultResponse> DeleteSong(@RequestParam Long id){
        ResponseEntity<DefaultResponse> responseEntity=null;
        try {
            responseEntity=new ResponseEntity(songService.deleteSong(id),HttpStatus.OK);
        }catch (Exception e){
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/search")
    public Page<Song> searchSong(@RequestParam("query") String query,@RequestParam("page") int page,@RequestParam("size") int size) throws Exception {
       Page<Song> songs=null;
        try {
            songs=songService.searchSong(query,page,size);
        }catch (Exception e){
             throw new Exception(e.getMessage());
        }
        return songs;
    }

    @GetMapping("/advance/search")
    public Page<Song> AdvanceSearchSong(@RequestParam("query") String query,@RequestParam("page") int page,@RequestParam("size") int size) throws Exception {
        Page<Song> songs=null;
        try {
            songs=songService.AdvanceSearch(query,page,size);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return songs;
    }


}
