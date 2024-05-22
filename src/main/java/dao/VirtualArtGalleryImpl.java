package dao;

import entity.Artwork;
import exception.ArtworkNotFoundException;
import exception.UserNotFoundException;
import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VirtualArtGalleryImpl implements IVirtualArtGallery {
    private Connection conn;

    public VirtualArtGalleryImpl() {
        conn = DBConnUtil.getConnection();
        if (conn == null) {
            System.err.println("Failed to establish database connection.");
        }
    }

    @Override
    public boolean addArtwork(Artwork artwork) {
        String INSERT_ARTWORK_SQL = "INSERT INTO Artwork (Title, Description, CreationDate, Medium, ImageURL, ArtistID) VALUES (?, ?, CURRENT_DATE, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_ARTWORK_SQL)) {
            preparedStatement.setString(1, artwork.getTitle());
            preparedStatement.setString(2, artwork.getDescription());
            preparedStatement.setString(3, artwork.getMedium());
            preparedStatement.setString(4, artwork.getImageURL());
            preparedStatement.setInt(5, artwork.getArtistID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding artwork: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateArtwork(Artwork artwork) {
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE Artwork SET Title = ?, Description = ?, Medium = ?, ImageURL = ?, ArtistID = ? WHERE ArtworkID = ?")) {
            stmt.setString(1, artwork.getTitle());
            stmt.setString(2, artwork.getDescription());
            stmt.setString(3, artwork.getMedium());
            stmt.setString(4, artwork.getImageURL());
            stmt.setInt(5, artwork.getArtistID());
            stmt.setInt(6, artwork.getArtworkID());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




    @Override
    public boolean removeArtwork(int artworkID) {
        try {
            // First, delete associated records from user_favorite_artwork
            String deleteFavoritesSQL = "DELETE FROM user_favorite_artwork WHERE ArtworkID=?";
            try (PreparedStatement deleteFavoritesStmt = conn.prepareStatement(deleteFavoritesSQL)) {
                deleteFavoritesStmt.setInt(1, artworkID);
                deleteFavoritesStmt.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException("Error removing associated records from user_favorite_artwork: " + e.getMessage());
            }

            // Then, delete associated records from artwork_gallery
            String deleteArtworkGallerySQL = "DELETE FROM artwork_gallery WHERE ArtworkID=?";
            try (PreparedStatement deleteArtworkGalleryStmt = conn.prepareStatement(deleteArtworkGallerySQL)) {
                deleteArtworkGalleryStmt.setInt(1, artworkID);
                deleteArtworkGalleryStmt.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException("Error removing associated records from artwork_gallery: " + e.getMessage());
            }

            // Finally, delete the artwork from Artwork table
            String deleteArtworkSQL = "DELETE FROM Artwork WHERE ArtworkID=?";
            try (PreparedStatement deleteArtworkStmt = conn.prepareStatement(deleteArtworkSQL)) {
                deleteArtworkStmt.setInt(1, artworkID);
                int rowsAffected = deleteArtworkStmt.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                throw new SQLException("Error removing artwork: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


    @Override
    public Artwork getArtworkById(int artworkID) throws ArtworkNotFoundException {
        String sql = "SELECT * FROM Artwork WHERE ArtworkID = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, artworkID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Artwork artwork = new Artwork();
                    artwork.setArtworkID(resultSet.getInt("ArtworkID"));
                    artwork.setTitle(resultSet.getString("Title"));
                    artwork.setDescription(resultSet.getString("Description"));
                    artwork.setCreationDate(resultSet.getDate("CreationDate"));
                    artwork.setMedium(resultSet.getString("Medium"));
                    artwork.setImageURL(resultSet.getString("ImageURL"));
                    artwork.setArtistID(resultSet.getInt("ArtistID"));
                    return artwork;
                } else {
                    throw new ArtworkNotFoundException("Artwork with ID " + artworkID + " not found");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving artwork: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Artwork> searchArtworks(String keyword) {
        List<Artwork> matchingArtworks = new ArrayList<>();
        String sql = "SELECT * FROM Artwork WHERE Title LIKE ? OR Description LIKE ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Artwork artwork = new Artwork();
                    artwork.setArtworkID(resultSet.getInt("ArtworkID"));
                    artwork.setTitle(resultSet.getString("Title"));
                    artwork.setDescription(resultSet.getString("Description"));
                    artwork.setCreationDate(resultSet.getDate("CreationDate"));
                    artwork.setMedium(resultSet.getString("Medium"));
                    artwork.setImageURL(resultSet.getString("ImageURL"));
                    artwork.setArtistID(resultSet.getInt("ArtistID"));
                    matchingArtworks.add(artwork);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching artworks: " + e.getMessage());
        }
        return matchingArtworks;
    }

    @Override
    public boolean addArtworkToFavorite(int userID, int artworkID) {
        String INSERT_FAVORITE_SQL = "INSERT INTO User_Favorite_Artwork (UserID, ArtworkID) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_FAVORITE_SQL)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, artworkID);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding artwork to favorites: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeArtworkFromFavorite(int userID, int artworkID) {
        String DELETE_FAVORITE_SQL = "DELETE FROM User_Favorite_Artwork WHERE UserID = ? AND ArtworkID = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_FAVORITE_SQL)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, artworkID);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error removing artwork from favorites: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Artwork> getUserFavoriteArtworks(int userID) throws UserNotFoundException {
        List<Artwork> favoriteArtworks = new ArrayList<>();
        String sql = "SELECT A.* FROM Artwork A " +
                     "JOIN User_Favorite_Artwork UFA ON A.ArtworkID = UFA.ArtworkID " +
                     "WHERE UFA.UserID = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Artwork artwork = new Artwork();
                    artwork.setArtworkID(resultSet.getInt("ArtworkID"));
                    artwork.setTitle(resultSet.getString("Title"));
                    artwork.setDescription(resultSet.getString("Description"));
                    artwork.setCreationDate(resultSet.getDate("CreationDate"));
                    artwork.setMedium(resultSet.getString("Medium"));
                    artwork.setImageURL(resultSet.getString("ImageURL"));
                    artwork.setArtistID(resultSet.getInt("ArtistID"));
                    favoriteArtworks.add(artwork);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving favorite artworks: " + e.getMessage());
        }
        return favoriteArtworks;
    }
}
