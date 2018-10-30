package gitmad.gatech.edu.project2340.Model;

import java.util.List;
import java.util.ArrayList;

public class Donation {
    private static int dummyTimestamp = 0;
    //These are arbitrarily chosen, I'm not sure what categories actually make sense
    public enum Category {
        FURNITURE, MENS_CLOTHING, WOMENS_CLOTHING, LINENS, KITCHENWARE, BOOKS, GAMES, FOOD
    }
    private int timestamp;
    private int location;
    private String aDescription;
    private String fullDescription;
    private int price;
    private Category category;
    private String comment;
    private Object image;
    //private List<Donation> _donations;

    public Donation(int locationKey, String aDescription,
                    String fullDescription, int price, Category category,
                    String comment, Object image) {
        this.timestamp = dummyTimestamp++;
        this.location = locationKey;
        this.aDescription = aDescription;
        this.fullDescription = fullDescription;
        this.price = price;
        this.category = category;
        this.comment = comment;
        this.image = image;
        //this._donations = new ArrayList<>();
    }

    public Donation(int location, String aDescription,
                    String fullDescription, int price, Category category) {
        this(location, aDescription, fullDescription, price, category, null, null);
    }

    public Donation() {
        this(0, "enter new description", "enter new full description", 0,
                null,null,null);
    }

    public int getTimestamp() {return this.timestamp;}
    public void setTimestamp(int nTimestamp) { this.timestamp = nTimestamp;}

    public int getLocation() {return this.location;}
    public void setLocation(int nLocKey) {this.location = nLocKey;}

    public String getADescription() {return this.aDescription;}
    public void setADescription(String nDescription) { this.aDescription = nDescription;}

    public String getFullDescription() {return this.fullDescription;}
    public void setFullDescription(String nFullDescription) {this.fullDescription = nFullDescription;}

    public int getPrice() {return this.price;}
    public void setPrice(int nPrice) {this.price = nPrice;}

    public Category getCategory() {return this.category;}
    public void setCategory(Category nCategory) {this.category = nCategory;}

    public String getComment() {return this.comment;}
    public void setComment(String nComment) {this.comment = nComment;}

    public Object getPicture() {return this.image;}
    public void setPicture(Object nImage) {this.image = nImage;}

    @Override
    public String toString() {return aDescription;}


}
