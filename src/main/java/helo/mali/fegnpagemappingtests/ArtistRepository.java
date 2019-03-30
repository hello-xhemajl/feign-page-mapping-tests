package helo.mali.fegnpagemappingtests;

import helo.mali.fegnpagemappingtests.builder.ArtistBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ArtistRepository {
  private List<Artist> artists;

  public ArtistRepository(){
    artists = Arrays.asList(
        ArtistBuilder.anArtist().withName("Dua Lipa").build(),
        ArtistBuilder.anArtist().withName("Rita Ora").build(),
        ArtistBuilder.anArtist().withName("Bebe Rexha").build()
    );
  }

  public Page<Artist> find(Pageable pageable){
    int start = (int) pageable.getOffset();
    int end = (int) (pageable.getOffset() + pageable.getPageSize());

    List<Artist> content = artists.subList(start, end);

    return new PageImpl<>(content, pageable, artists.size());
  }

}
