package gitmad.gatech.edu.project2340.controller;
//https://www.youtube.com/watch?v=svxKakcKnxE

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import gitmad.gatech.edu.project2340.Model.LocationData;

public class CVReader {
    InputStream inputStream;

    public CVReader(InputStream is) {
        this.inputStream = is;
    }

    public List<LocationData> read() {
        List<LocationData> resultList = new ArrayList<LocationData>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine = reader.readLine();
            while((csvLine = reader.readLine()) != null) {
                LocationData locadata = new LocationData();
                String[] row = csvLine.split(",");
                locadata.setName(row[1]);
                locadata.setLatitude(Double.parseDouble(row[2]));
                locadata.setLongitude(Double.parseDouble(row[3]));
                locadata.setStreetAddress(row[4]);
                locadata.setCity(row[5]);
                locadata.setState(row[6]);
                locadata.setZipcode(Integer.parseInt(row[7]));
                locadata.setType(row[8]);
                locadata.setPhoneNumber((row[9]));
                locadata.setWebsite(row[10]);
                resultList.add(locadata);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file:" + ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        return resultList;
    }
}
