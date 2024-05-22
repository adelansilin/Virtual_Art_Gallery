package entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Artist {
	private  int artistID;
    private String name;
    private String biography;
    private String birthDate;
    private String nationality;
    private String website;
    private String contactInformation;

    // Default constructor
    public Artist() {
    }

    // Parameterized constructor
    public Artist(int artistID, String name, String biography, String birthDate, String nationality, String website, String contactInformation) {
        this.artistID = artistID;
        this.name = name;
        this.biography = biography;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.website = website;
        this.contactInformation = contactInformation;
    }

    // Getters and setters
    public  int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
    public void addArtist(Connection connection) {
    	 try {
             
             String INSERT_ARTIST_SQL = "INSERT INTO Artist (Name, Biography, BirthDate, Nationality, Website, ContactInformation) VALUES (?, ?, ?, ?, ?, ?)";

             
             try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARTIST_SQL)) {
                 preparedStatement.setString(1, name);
                 preparedStatement.setString(2, biography);
                 preparedStatement.setString(3, birthDate);
                 preparedStatement.setString(4, nationality);
                 preparedStatement.setString(5, website);
                 preparedStatement.setString(6, contactInformation);

                 
                 int rowsAffected = preparedStatement.executeUpdate();

                 if (rowsAffected > 0) {
                     System.out.println("Artist added successfully!");
                 } else {
                     System.out.println("Failed to add artist.");
                 }
             }
         } catch (SQLException e) {
             // Handle exceptions, log or print the error
             e.printStackTrace();
         }
     }
    
}
