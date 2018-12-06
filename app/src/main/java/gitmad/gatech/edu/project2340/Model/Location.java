package gitmad.gatech.edu.project2340.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single Location which may have many location data assigned
 *
 * Information Holder and Structurer (manages the location data assigned to the location
 */

public class Location{

        /** allow us to assign unique number to the location */
        private static int nextNo = 1;

        /** unique key number */
        private int _key;



    /** the location name */
        private String _name;

        /** the list of all registered location data for this location */
        private  List<LocationData> _location_data;

        /**
         * Makes a new Location
         * @param name  the name of the location
         */
        public Location(String name) {
            _name = name;
            _key = Location.nextNo++;
            _location_data = new ArrayList<>();
        }

        public Location() {
            _name = "Enter a new name";
            _key = Location.nextNo++;
            _location_data = new ArrayList<>();
        }

        @Override
        public boolean equals(Object o) {
            Location l = (Location) o;
            return (l.getName().equals(_name));
        }

        /* *****************************************
         * All the property setters and getters
         * */

        public String getName() { return _name; }
        public void setName(String name) { _name = name; }

        public int getKey() { return _key; }

        public List<LocationData> getLocationData() {return _location_data; }

        /* ********************************************
         * Instance Methods
         */

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

        @Override
        public String toString() {
            return _name;
        }

        /**
         * Finds a location data using O(n) linear search given a unique key number
         * @param key
         * @return
         */
        public LocationData findLocationDataByKey(int key) {
            for (LocationData locdata : _location_data) {
                if (key == locdata.getKey()) {
                    return locdata;
                }
            }
            return null;
        }
    }
