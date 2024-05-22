package main;


import exception.ArtworkNotFoundException;
import exception.UserNotFoundException;


import dao.IVirtualArtGallery;
import dao.VirtualArtGalleryImpl;

import entity.Artwork;

import java.util.Scanner;
import java.util.List;
public class Main {
	
	public static void main(String[] args)  {
		IVirtualArtGallery artworks = new VirtualArtGalleryImpl();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
	    System.out.println();
		System.out.println("Enter 1 for Add Artwork:");
		System.out.println("Enter 2 for Update Artwork:");
		System.out.println("Enter 3 for Remove Artwork:");
		System.out.println("Enter 4 for GetArtworkByID :");
		System.out.println("Enter 5 for SearchArtwork :");
		System.out.println("Enter 6 for AddArtwork to Favourite :");
		System.out.println("Enter 7 for RemoveArtwork from Favourite:");
		System.out.println("Enter 8 for GetArtwork From Favourite:");
		System.out.println("Enter 9 for Exit:");
		
		
		
		
        int choice = sc.nextInt();
        sc.nextLine();
            switch(choice) {
            
            case 1:
            	
            	// Add Artwork
                Artwork newArtwork = new Artwork();
                System.out.println("Enter artwork title:");
                newArtwork.setTitle(sc.nextLine());

                System.out.println("Enter artwork description:");
                newArtwork.setDescription(sc.nextLine());

                System.out.println("Enter artwork medium:");
                newArtwork.setMedium(sc.nextLine());

                System.out.println("Enter artwork image URL:");
                newArtwork.setImageURL(sc.nextLine());

                System.out.println("Enter artist ID:");
                newArtwork.setArtistID(sc.nextInt());

                boolean artworkAdded = artworks.addArtwork(newArtwork);

                if (artworkAdded) {
                    System.out.println("Artwork added successfully!");
                } else {
                    System.out.println("Failed to add artwork.");
                }
                break;
                
            case 2:
                // Update Artwork
                System.out.println("Enter artwork ID to update:");
                int artworkIdToUpdate = sc.nextInt();
                sc.nextLine(); // Consume newline

                try {
                    Artwork artworkToUpdate = artworks.getArtworkById(artworkIdToUpdate);
                    if (artworkToUpdate == null) {
                        System.out.println("Artwork not found.");
                        break;
                    }

                    boolean updated = false;
                    while (!updated) {
                        System.out.println("Which field would you like to update?");
                        System.out.println("1. Title");
                        System.out.println("2. Description");
                        System.out.println("3. Medium");
                        System.out.println("4. Image URL");
                        System.out.println("5. Artist ID");
                        System.out.println("6. Done Updating");

                        int updateChoice = sc.nextInt();
                        sc.nextLine();

                        switch (updateChoice) {
                            case 1:
                                System.out.println("Enter new Title:");
                                String newTitle = sc.nextLine();
                                artworkToUpdate.setTitle(newTitle);
                                break;
                            case 2:
                                System.out.println("Enter new Description:");
                                String newDescription = sc.nextLine();
                                artworkToUpdate.setDescription(newDescription);
                                break;
                            case 3:
                                System.out.println("Enter new Medium:");
                                String newMedium = sc.nextLine();
                                artworkToUpdate.setMedium(newMedium);
                                break;
                            case 4:
                                System.out.println("Enter new Image URL:");
                                String newImageURL = sc.nextLine();
                                artworkToUpdate.setImageURL(newImageURL);
                                break;
                            case 5:
                                System.out.println("Enter new Artist ID:");
                                int newArtistID = sc.nextInt();
                                sc.nextLine();
                                artworkToUpdate.setArtistID(newArtistID);
                                break;
                            case 6:
                                updated = artworks.updateArtwork(artworkToUpdate);
                                if (updated) {
                                    System.out.println("Artwork updated successfully.");
                                } else {
                                    System.out.println("Failed to update artwork.");
                                }
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                        }
                    }
                } catch (ArtworkNotFoundException e) {
                    System.out.println("Artwork not found: " + e.getMessage());
                }
                break;



            case 3:
                // Remove Artwork
                try {
                    System.out.println("Enter the Artwork ID to remove:");
                    int artworkIDToRemove = sc.nextInt();
                    boolean isArtworkRemoved = artworks.removeArtwork(artworkIDToRemove);

                    if (isArtworkRemoved) {
                        System.out.println("Artwork removed successfully!");
                    } else {
                        System.out.println("Failed to remove artwork.");
                    }
                } catch (ArtworkNotFoundException e) {
                    System.out.println("Artwork not found: " + e.getMessage());
                }
                break;

                
            case 4:
            	try {
            	System.out.print("Enter the ArtworkId which you want:");
            	int artworkIdToRetrieve = sc.nextInt(); 
                Artwork retrievedArtwork = artworks.getArtworkById(artworkIdToRetrieve);
                if (retrievedArtwork != null) {
                    
                    System.out.println("Artwork ID: " + retrievedArtwork.getArtworkID());
                    System.out.println("Title: " + retrievedArtwork.getTitle());
                    System.out.println("Description: " + retrievedArtwork.getDescription());
                    System.out.println("CreationDate " + retrievedArtwork.getCreationDate());
                    System.out.println("Medium: " + retrievedArtwork.getMedium());
                    System.out.println("ImageURL: " + retrievedArtwork.getImageURL());
                    System.out.println("ArtistID: " + retrievedArtwork.getArtistID());
                    
                    
               } else {
                    System.out.println("Artwork not found or an error occurred.");
                }
            	}catch(ArtworkNotFoundException e) {
            		System.out.println("Artwork not found: " + e.getMessage());
            	}
                
                break;
            
            case 5:
            	System.out.println("Enter the Keyword to search:");
            	String keywordToSearch = sc.next(); 
                List<Artwork> matchingArtworks = artworks.searchArtworks(keywordToSearch);

                // Display the matching artwork
                if (!matchingArtworks.isEmpty()) {
                    System.out.println("Matching Artworks:");
                    for (Artwork artwork : matchingArtworks) {
                        System.out.println("Artwork ID: " + artwork.getArtworkID());
                        System.out.println("Title: " + artwork.getTitle());
                        System.out.println("Description: " + artwork.getDescription());
                        System.out.println("CreationDate: " + artwork.getCreationDate());
                        System.out.println("Medium: " + artwork.getMedium());
                        System.out.println("ImageURL: " + artwork.getImageURL());
                        System.out.println("ArtistID: " + artwork.getArtistID());
                        
                    }
                } else {
                    System.out.println("No matching artworks found.");
                }

                
                break;
            case 6:
                
                System.out.println("Enter UserId  And ArtworkId");
                int userID = sc.nextInt(); 
                int artworkID = sc.nextInt(); 

                boolean isAddedToFavorite = artworks.addArtworkToFavorite(userID, artworkID);

                if (isAddedToFavorite) {
                    System.out.println("Artwork added to favorites successfully!");
                } else {
                    System.out.println("Failed to add artwork to favorites.");
                }
                break;
                
           
            case 7: 
            	System.out.println("Enter userId:");
            	int usersID = sc.nextInt();
            	System.out.println("Enter ArtworkId:");
                int artworksID = sc.nextInt();

                // Call removeArtworkFromFavorite method
                boolean removed = artworks.removeArtworkFromFavorite(usersID, artworksID);

                if (removed) {
                    System.out.println("Artwork removed from favorites successfully!");
                } else {
                    System.out.println("Failed to remove artwork from favorites.");
                }
                break;
            
            case 8:
                
                System.out.print("Enter UserID:");
                int userId = sc.nextInt();
                System.out.println();
                try {
                    List<Artwork> favoriteArtworks = artworks.getUserFavoriteArtworks(userId);
                    if (!favoriteArtworks.isEmpty()) {
                        System.out.println("User's favorite artworks:");
                        for (Artwork artwork : favoriteArtworks) {
                            System.out.println("Artwork ID: " + artwork.getArtworkID());
                            System.out.println("Title: " + artwork.getTitle());
                            System.out.println("Description: " + artwork.getDescription());
                            System.out.println("Creation Date: " + artwork.getCreationDate());
                            System.out.println("Medium: " + artwork.getMedium());
                            System.out.println("Image URL: " + artwork.getImageURL());
                            System.out.println("Artist ID: " + artwork.getArtistID());
                            System.out.println();
                        }
                    } else {
                        System.out.println("No favorite artworks found for user ID: " + userId);
                    }
                } catch (UserNotFoundException e) {
                    System.out.println("User not found: " + e.getMessage());
                }
                break;
                
            case 9:
                System.out.println("Exiting...");
                sc.close();
                return;

            default:
                System.out.println("Invalid choice. Try again.");
               
        }
        
		}
           
            }
	
        
    
}