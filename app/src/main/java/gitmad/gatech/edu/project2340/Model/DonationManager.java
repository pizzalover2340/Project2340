package gitmad.gatech.edu.project2340.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DonationManager implements Serializable {

    /**
     * A list of students
     */
    private final List<Donation> donations = new ArrayList<>();

    /**
     * A map of students by Key == user name Value == student object
     *
     * This is marked as transient so it will not be serialized.
     * It is derived from the students collection above, so it does not
     * need to be serialized.
     */
    private transient Map<String, Donation> donationMap = new HashMap<>();


    /**
     * add a student to the collection
     * @param name the student name
     * @param uid the student user id
     * @param pwd the student password
     * @param email the student email
     * @param id the student id code
     */
    void addDonation(int locationKey, String aDescription,
                     String fullDescription, int price, Donation.Category category,
                     String comment, Object image) {
        Donation student = new Donation(locationKey, aDescription,
                fullDescription, price, category,
                comment, image);
        //students.add(student);
        //studentMap.put(name, student);
        AddDonationCommand cmd = new AddDonationCommand(student);
        CommandManager commandManager = AbstractCommand.manager;
        commandManager.executeCommand(cmd);
    }

    /**
     * this is package vis because only model should be asking for this data
     *
     * @return
     */
    List<Donation> getDonations() {
        return donations;
    }

    Donation getDonationByName(String name) {
        return donationMap.get(name);
    }

    /**
     *
     * @param writer
     */
    void saveAsText(PrintWriter writer) {
        System.out.println("Manager saving: " + donations.size() + " students" );
        writer.println(donations.size());
        for(Donation s : donations) {
            s.saveAsText(writer);
        }
    }

    /**
     * load the model from a custom text file
     *
     * @param reader  the file to read from
     */
    void loadFromText(BufferedReader reader) {
        System.out.println("Loading Text File");
        donationMap.clear();
        donations.clear();
        try {
            String countStr = reader.readLine();
            assert countStr != null;
            int count = Integer.parseInt(countStr);

            //then read in each user to model
            for (int i = 0; i < count; ++i) {
                String line = reader.readLine();
                Donation s = Donation.parseEntry(line);
                donations.add(s);
                donationMap.put(s.getFullDescription(), s);
            }
            //be sure and close the file
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done loading text file with " + donations.size() + " donations");

    }


    /**
     * This should only be called during serialization (reading in).
     *
     * This recomputes the student map which is derived from the student collection.
     *
     */
    void regenMap() {
        if (donationMap != null)
            donationMap.clear();
        else
            donationMap = new HashMap<>();
        for (Donation s : donations) {
            donationMap.put(s.getFullDescription(), s);
        }
    }

    /**
     * used by command pattern, should be not called otherwise
     *
     * @param student the student to add
     */
    void addDonation(Donation student) {
        donations.add(student);
        donationMap.put(student.getFullDescription(), student);
    }

    void removeDonation(Donation student) {
        donations.remove(student);
        donationMap.remove(student.getFullDescription());
    }
}

