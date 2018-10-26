package gitmad.gatech.edu.project2340.Model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    /** holds the list of all location data */
    private List<LocationData> _location_data;

    /** the currently selected location data, defaults to first location data*/
    private LocationData _currentLocationData;

    /** Null Object pattern, returned when no location data is found */
    private final LocationData theNullLocationData = new LocationData();

    /** holds a list of all donations */
    private List<Donation> _donations;


    /**
     * make a new model
     */
    private Model() {
        _location_data = new ArrayList<>();
        _donations = new ArrayList<>();

        //comment this out after full app developed -- for homework leave in
        //loadDummyData();

    }

    /**
     * populate the model with some dummy data.  The full app would not require this.
     * comment out when adding new courses functionality is present.
    private void loadDummyData() {
        _courses.add(new Course("Objects and Design", "2340", SchoolCode.CS));
        _courses.add(new Course( "TQM", "4321", SchoolCode.IE));
        _courses.add(new Course("Concrete Ideas", "5432", SchoolCode.AR));
        _courses.add(new Course("Calc I", "2213", SchoolCode.MATH));
        _courses.get(0).getStudents().add(new Student("Bob", "CS"));
        _courses.get(0).getStudents().add(new Student("Sally", "ISYE"));
        _courses.get(1).getStudents().add(new Student("Fred", "Math"));
        _courses.get(1).getStudents().add(new Student("Edith", "CM"));
        _currentCourse = _courses.get(0);
    } */

    /**
     * get the location data
     * @return a list of the location data in the app
     */
    public List<LocationData> getLocationData() { return _location_data; }

    /**
     * add a location data to the app.  checks if the location data is already entered
     *
     * uses O(n) linear search for location data
     *
     * @param locationData  the location data to be added
     * @return true if added, false if a duplicate
     */
    public boolean addLocationData(LocationData locationData) {
        for (LocationData locdat : _location_data) {
            if (locdat.equals(locationData)) return false;
        }
        _location_data.add(locationData);
        return true;
    }

    /**
     *
     * @return  the currently selected location data
     */
    public LocationData getCurrentLocationData() { return _currentLocationData;}

    public void setCurrentLocationData(LocationData locationData) { _currentLocationData = locationData; }

    /**
     * Return a location data that has matching key.
     * This uses an O(n) linear search.
     *
     * @param key the key of the location data to find
     * @return  the location with that key or the NullLocationData if no such key exists.
     *
     */
    public LocationData getLocationDataByNumber (int key) {
        for (LocationData locdat : _location_data) {
            if (locdat.getKey() == key) return locdat;
        }
        return theNullLocationData;
    }

    public List<Donation> getDonations() {return this._donations;}

    public boolean addDonation(Donation donation) {
        for (Donation donated : _donations) {
            if (donated.equals(donation)) {return false;}
        }
        _donations.add(donation);
        return true;
    }

    /**
     * Return a course that has the matching id
     * This uses a linear O(n) search
     *
     * @param id the id number of the course
     * @return the course with this id or theNullCourse if no such id exists.
    public Course getCourseById(int id) {
        for (Course c : _courses ) {
            if (c.getId() == id) {
                return c;
            }
        }import gitmad.gatech.edu.project2340.Model
        return theNullCourse;
    }

    /**
     * add a student to the current course
     *
     * @param student the student to add
     * @return true if student added, false if not added
    public boolean addStudent(Student student) {
        return _currentCourse != null && _currentCourse.addStudent(student);
    }

    /**
     * Replace an existing students data with new data
     *
     * @param student the student being edited
    public void replaceStudentData(Student student) {
        Student existing = _currentCourse.findStudentById(student.getId());

        //if existing comes back null, something is seriously wrong
        if (BuildConfig.DEBUG && (existing == null)) { throw new AssertionError(); }

        //update the name
        existing.setName(student.getName());

        //update the major
        existing.setMajor(student.getMajor());

        //update the class standing
        existing.setClassStanding(student.getClassStanding());
    } */
}
