package helo.mali.fegnpagemappingtests;

import helo.mali.fegnpagemappingtests.builder.ArtistBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ArtistController {

  private final ArtistRepository artistRepository;

  @Autowired
  public ArtistController(ArtistRepository artistRepository) {
    this.artistRepository = artistRepository;
  }

  @GetMapping("/artists")
  public Page<Artist> getArtists(Pageable pageable){
    return artistRepository.find(pageable);
  }
}