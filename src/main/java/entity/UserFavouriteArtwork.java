package entity;

public class UserFavouriteArtwork {
	 private int userID;
	    private int artworkID;

	    // Default constructor
	    public UserFavouriteArtwork() {
	    }

	    // Parameterized constructor
	    public UserFavouriteArtwork(int userID, int artworkID) {
	        this.userID = userID;
	        this.artworkID = artworkID;
	    }

	    // Getters and setters
	    public int getUserID() {
	        return userID;
	    }

	    public void setUserID(int userID) {
	        this.userID = userID;
	    }

	    public int getArtworkID() {
	        return artworkID;
	    }

	    public void setArtworkID(int artworkID) {
	        this.artworkID = artworkID;
	    }
}
