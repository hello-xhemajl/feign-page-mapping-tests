package helo.mali.fegnpagemappingtests.builder;

import helo.mali.fegnpagemappingtests.Artist;

public final class ArtistBuilder {
  private String name;

  private ArtistBuilder() {
  }

  public static ArtistBuilder anArtist() {
    return new ArtistBuilder();
  }

  public ArtistBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public Artist build() {
    Artist artist = new Artist();
    artist.setName(name);
    return artist;
  }
}
