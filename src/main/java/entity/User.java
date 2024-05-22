package entity;
import java.util.List;
import java.util.Date;
public class User {
	private int userID;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String profilePicture;
    private List<Artwork> favoriteArtworks;  //  user can have multiple favorite artwork

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(int userID, String username, String password, String email, String firstName, String lastName, Date dateOfBirth, String profilePicture, List<Artwork> favoriteArtworks) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.profilePicture = profilePicture;
        this.favoriteArtworks = favoriteArtworks;
    }

    // Getters and setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Artwork> getFavoriteArtworks() {
        return favoriteArtworks;
    }

    public void setFavoriteArtworks(List<Artwork> favoriteArtworks) {
        this.favoriteArtworks = favoriteArtworks;
    }
}
