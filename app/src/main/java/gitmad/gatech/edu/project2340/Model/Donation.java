package gitmad.gatech.edu.project2340.Model;

import java.io.PrintWriter;
import java.io.Serializable;

public class Donation implements Serializable{
    private static int dummyTimestamp = 0;
    //These are arbitrarily chosen, I'm not sure what categories actually make sense
    public enum Category {
        FURNITURE, MENS_CLOTHING, WOMENS_CLOTHING, LINENS, KITCHENWARE, BOOKS, GAMES, FOOD, ALL_CATEGORIES
    }
    private int timestamp;
    private int location;
    private String aDescription;
    private String fullDescription;
    private int price;
    private Category category;
    private String comment;
    private Object image;
    private String locationString;
    //private List<Donation> _donations = new ArrayList<Donation>;

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
        //_donations.add(this);
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

/*
    public String getLocationByname() {
        return this.;
    }
*/

    //String locationname = findLocationDataByKey(location);

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

    /**
     * Save this class in a custom save format
     * I chose to use tab (\t) to make line splitting easy for loading
     * If your data had tabs, you would need something else as a delimiter
     *
     * @param writer the file to write this student to
     */

    //int locationKey, String aDescription,
    //                    String fullDescription, int price, Category category,
    //                    String comment, Object image

    public void saveAsText(PrintWriter writer) {
        System.out.println("Donation saving donation: " + getFullDescription());
        writer.println(getTimestamp() + "\t" + getADescription() + "\t" + getFullDescription()
                + "\t" + getPrice() + "\t" + getCategory() + "\t" + getComment());
    }

    public static Donation parseEntry(String line) {
        assert line != null;
        String[] tokens = line.split("\t");
        assert tokens.length == 6;
        Donation s = new Donation(Integer.parseInt(tokens[0]), tokens[1], tokens[2],
                Integer.parseInt(tokens[3]), Category.valueOf(tokens[4]));

        return s;
    }


}
