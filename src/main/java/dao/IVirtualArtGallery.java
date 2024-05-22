package dao;

import entity.Artwork;
import exception.ArtworkNotFoundException;
import exception.UserNotFoundException;

import java.util.List;

public interface IVirtualArtGallery {
    // Artwork Management Methods
    boolean addArtwork(Artwork artwork);
    
    boolean updateArtwork(Artwork artwork);

    boolean removeArtwork(int artworkID) throws ArtworkNotFoundException;

    Artwork getArtworkById(int artworkID) throws ArtworkNotFoundException;

    List<Artwork> searchArtworks(String keyword);
   
    // User-Favorite Methods
    boolean addArtworkToFavorite(int userID, int artworkID);
    
    boolean removeArtworkFromFavorite(int userID, int artworkID);
    
    List<Artwork> getUserFavoriteArtworks(int userId) throws UserNotFoundException;
}
