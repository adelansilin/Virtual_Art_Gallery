package main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.IVirtualArtGallery;
import dao.VirtualArtGalleryImpl;
import entity.Artwork;
import exception.ArtworkNotFoundException;
import java.util.List;

class ArtworkManagementTest {

    IVirtualArtGallery gallery = new VirtualArtGalleryImpl();

    @Test
    void testUploadNewArtwork() {
        // Create a new artwork
        Artwork newArtwork = new Artwork();
        newArtwork.setTitle("Test Artwork");
        newArtwork.setDescription("Test Description");
        newArtwork.setMedium("Test Medium");
        newArtwork.setImageURL("test_image_url.jpg");
        newArtwork.setArtistID(1); // Assuming artist ID exists

        // Try to upload the artwork
        boolean isUploaded = gallery.addArtwork(newArtwork);

        assertTrue(isUploaded);
    }

    @Test
    void testUpdateArtworkDetails() {
        try {
            // Get an existing artwork (assuming artwork with ID 1 exists)
            Artwork artworkToUpdate = gallery.getArtworkById(11);
            assertNotNull(artworkToUpdate);

            // Update artwork details
            artworkToUpdate.setTitle("Updated Title");

            // Update the artwork
            boolean isUpdated = gallery.updateArtwork(artworkToUpdate);

            assertTrue(isUpdated);
        } catch (ArtworkNotFoundException e) {
            fail("Artwork not found.");
        }
    }

    @Test
    void testRemoveArtwork() {
        try {
            // Remove an existing artwork (assuming artwork with ID 1 exists)
            boolean isRemoved = gallery.removeArtwork(4);

            assertTrue(isRemoved);
        } catch (ArtworkNotFoundException e) {
            fail("Artwork not found.");
        }
    }

    @Test
    void testSearchArtworks() {
        // Search for artworks with keyword "test"
        List<Artwork> searchResults = gallery.searchArtworks("test");

        assertFalse(searchResults.isEmpty());
    }
}
