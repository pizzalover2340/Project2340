package gitmad.gatech.edu.project2340.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Information Holder - represents a single location datum in model
 *
 * We are passing this object in a bundle between intents, so we implement
 * the Parcelable interface.
 *
 */

//add "implements Parcelable" if bottom methods are uncommented
public class LocationData{

    /** a demonstration of using something other than an enum for holding choices */
        //public static List<String> legalMajors = Arrays.asList("CS", "CM", "ISYE", "MATH", "EE", "CMPE", "NA");

        //public static List<ClassStanding> legalClassStanding = Arrays.asList(ClassStanding.FR, ClassStanding.SO, ClassStanding.JR, ClassStanding.SR);

        public static List<String> legalTypes = Arrays.asList("Drop off", "Warehouse", "Store");

        /** allow us to assign unique key numbers to each location datum */
        private static int Next_Key = 0;

        /** this location datum's key number */
        private int _key;

        /** this location datum's name */
        private String _name;

        /** this location datum's latitude */
        private double _latitude;

        /** this location datum's longitude */
        private double _longitude;

        /** this location datum's street address */
        private String _street_address;

        /** this location datum's city */
        private String _city;

        /** this location datum's state */
        private String _state;

        /** this location datum's zipcode */
        private int _zipcode;

        /** this location datum's type */
        private String _type;

        /** this location datum's phone number */
        private String _phone_number;

        /** this location datum's website */
        private String _website;

        private  List<LocationData> _location_data;

        /** this students major */
        //private String _major;

        /** this students class standing */
        //private ClassStanding _class_standing;


        /* **********************
         * Getters and setters
         */
        public String getName() { return _name; }
        public void setName(String name) { _name = name; }

        //no setter for this.  key is a read only field
        public int getKey() { return _key; }

        public double getLatitude() { return _latitude;}
        public void setLatitude(double latitude) { _latitude = latitude; }

        public double getLongitude() { return _longitude;}
        public void setLongitude(double longitude) { _longitude = longitude; }

        public String getStreetAddress() { return _street_address;}
        public void setStreetAddress(String street_address) { _street_address = street_address; }

        public String getCity() { return _city;}
        public void setCity(String city) { _city = city; }

        public String getState() { return _state;}
        public void setState(String state) { _state = state; }

        public int getZipcode() { return _zipcode;}
        public void setZipcode(int zipcode) { _zipcode = zipcode; }

        public String getType() { return _type;}
        public void setType(String type) { _type = type; }

        public String getPhoneNumber() { return _phone_number;}
        public void setPhoneNumber(String phone_number) { _phone_number = phone_number; }

        public String getWebsite() { return _website;}
        public void setWebsite(String website) { _website = website; }

        public List<LocationData> getLocationData() {return _location_data; }

        /**
         * Lookup a type based on its code.  Returns the position of that
         * type in the array
         *
         * @param code the type to find
         *
         * @return the index of the array that corresponds to the submitted type
         */
        public static int findPosition(String code) {
            int i = 0;
            while (i < legalTypes.size()) {
                if (code.equals(legalTypes.get(i))) return i;
                ++i;
            }
            return 0;
        }

        /**
         * Make a new Location Datum
         * @param name           the location datum's name
         * @param latitude       the location datum's latitude
         * @param longitude      the location datum's longitude
         * @param streetAddress  the location datum's street address
         * @param city           the location datum's city
         * @param state          the location datum's state
         * @param zipcode        the location datum's zipcode
         * @param type           the location datum's type
         * @param phoneNumber    the location datum's phone number
         * @param website        the location datum's website
         */
        public LocationData(String name, double latitude, double longitude, String streetAddress,
                            String city, String state, int zipcode, String type, String phoneNumber,
                            String website) {
            _name = name;
            _latitude = latitude;
            _longitude = longitude;
            _street_address = streetAddress;
            _city = city;
            _state = state;
            _zipcode = zipcode;
            _type = type;
            _phone_number = phoneNumber;
            _website = website;
            _key = LocationData.Next_Key++;
            _location_data = new ArrayList<>();
        }

        /**
         * Make a new Location Datum
         * @param name           the location datum's name
         * @param type           the location datum's type
         * @param phoneNumber    the location datum's phone number
         * @param website        the location datum's website
         */
        public LocationData(String name, String type, String phoneNumber, String website) {
            this(name , 0.0, 0.0, null, null, null, 0, type, phoneNumber, website);
        }

        /**
         * No param constructor -- DO NOT CALL NORMALLY
         * This constructor only for GUI use in edit/new location data dialog
         */
        public LocationData() {
            this("Enter a new name" , 0.0, 0.0, "Enter a new "
                    + "street address", "Enter a new city", "Enter a new state", 0,
                    "Enter a new type", "Enter a new phoneNumber", "Enter a new website");
        }

    /**
     * Adds the requested location data.  If location data is already in the location, return false
     * This is an O(n) search
     *
     * This assumes all location data names are unique
     *
     * @param locationData   the location data to add to the location
     * @return true if success, false if location data already in the location
     */
    public boolean addLocationData(LocationData locationData) {

        //go through each location data looking for duplicate name   O(n)
        for (LocationData locdata : _location_data) {

            if (locdata.getName().equals(locationData.getName())) {
                //oops found duplicate name, don't add and return failure signal
                return false;
            }

        }
        //never found the name so safe to add it.
        _location_data.add(locationData);

        //return the success signal
        return true;
    }

        /**
         *
         * @return the display string representation
         */
        @Override
        public String toString() {
            return _name + " " + _latitude + " " + _longitude + " " + _street_address + " " + _city
                    + " " + _state + " " + _zipcode + " " + _type + " " + _phone_number + " " + _website;
        }


        /* *********************************
         * These methods are required by the Parcelable interface
         * I just wanted to demo how to pass information from one activity
         * to another through an intent.   If this were a real project, I
         * would probably have the facade maintain information about the
         * currently selected location data which would remove the need to
         * pass the location data object in an intent, which would remove the need
         * to implement the Parcelable interface and these methods.
         */

        /**
         * Constructor used by Parcel to make a new location data out of the
         * parceled information
         *
         * @param in  the parcel containing the location data information
         //comment closer here
        private LocationData(Parcel in) {
            _name = in.readString();
            _major = in.readString();
            _class_standing = (ClassStanding) in.readSerializable();
            _id = in.readInt();


        }

        @Override
        public int describeContents() {
            return 0;
        }

        /* *************************
           If you add new instance vars to Student, you will need to add them to the write
           Be sure the order you write information matches the order that the constructor above
           reads them.
         //comment closer here
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(_name);
            dest.writeString(_major);
            dest.writeSerializable(_class_standing);
            dest.writeInt(_id);


        }

        /**
         * Should not have to edit this method if the constructor and write method are
         * working correctly.
         //comment closer here
        public static final Parcelable.Creator<Student> CREATOR
                = new Parcelable.Creator<Student>() {
            public Student createFromParcel(Parcel in) {
                return new Student(in);
            }

            public Student[] newArray(int size) {
                return new Student[size];
            }
        }; */

}
