package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import users.Address;
import users.Company;
import users.Geo;
import users.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GsonObj {
    public static void main(String[] args) {
        creator();
    }

    public static Object options(){
        Address address = new Address("Address", "Wall", "Apt. 332", "Boston", "23213");
        Geo geo = new Geo("Geo", "38.21441", "32.51532");
        Company company = new Company("Company", "123455151",
                "google.com", "Google","dadsa","s");
        User user = new User("11", "Jack", "Bart", "google@gmail.com", address, geo, company);
        return user;
    }

    public static String creator(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(options());
        System.out.println("json = " + json);

        try {
            OutputStream fos = new FileOutputStream("user.json");
            fos.write(json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
