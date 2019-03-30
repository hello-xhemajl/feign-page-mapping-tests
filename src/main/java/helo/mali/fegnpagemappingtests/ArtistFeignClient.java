package helo.mali.fegnpagemappingtests;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("local")
public interface ArtistFeignClient {
  @GetMapping("/artists")
  Page<Artist> getArtists(Pageable pageable);
}