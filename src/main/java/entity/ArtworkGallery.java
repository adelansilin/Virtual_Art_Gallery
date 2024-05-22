package entity;

public class ArtworkGallery {
	private int artworkID;
    private int galleryID;

    // Default constructor
    public ArtworkGallery() {
    }

    // Parameterized constructor
    public ArtworkGallery(int artworkID, int galleryID) {
        this.artworkID = artworkID;
        this.galleryID = galleryID;
    }

    // Getters and setters
    public int getArtworkID() {
        return artworkID;
    }

    public void setArtworkID(int artworkID) {
        this.artworkID = artworkID;
    }

    public int getGalleryID() {
        return galleryID;
    }

    public void setGalleryID(int galleryID) {
        this.galleryID = galleryID;
    }
}
