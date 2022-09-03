
package starwarscharacter;
import apiConsume.ApiRequest;
import com.google.gson.Gson;
import models.GeneralResponses;
import models.Person;

import javax.swing.*;
import java.util.ArrayList;

// External libraries used (gson 2.8.2) from mavenrepository

public class StarWarsCharacter {

    public StarWarsCharacter(String searchName) {
        this.searchName = searchName;
    }

    private String searchName;
    private int sizeResults;
    
    public ArrayList<Person> getResults (){
        
        ApiRequest nameRequest = new ApiRequest(searchName);
        nameRequest.connection();
        
        Gson gsonName = new Gson();
        GeneralResponses person = gsonName.fromJson(nameRequest.getJsonString(), GeneralResponses.class);
        
        // Total results
        setSizeResults(person.getSize());
        
        // Set homeworld to JsonString
        person.getResults().stream().parallel()
                .forEach(Person::traduceHomeWorld);
        
        ArrayList<Person> results = new ArrayList<>();
        
        // Add Persons on Results
        for (int object=0; object<person.getSize(); object++){
            results.add(person.getResults().get(object));
        }
        
        return results;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public int getSizeResults() {
        return sizeResults;
    }

    public void setSizeResults(int sizeResults) {
        this.sizeResults = sizeResults;
    }

}

