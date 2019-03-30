package helo.mali.fegnpagemappingtests;

import helo.mali.fegnpagemappingtests.builder.ArtistBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ArtistController {

  @GetMapping("/artists")
  public Page<Artist> getArtists(Pageable pageable){
    List<Artist> artists = Arrays.asList(
        ArtistBuilder.anArtist().withName("Dua Lipa").build(),
        ArtistBuilder.anArtist().withName("Rita Ora").build(),
        ArtistBuilder.anArtist().withName("Bebe Rexha").build()
    );

    return new PageImpl<>(artists, pageable, artists.size());
  }
}
