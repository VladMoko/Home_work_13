package HomeWork13;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpProcess {
    public static void main(String[] args) {
        info();
        upDateInfo();
        delete();
        getAllUsers();
        getUserId();
        getUsername();
    }


    public static void info() {
        String url = "https://jsonplaceholder.typicode.com/users";

        HttpRequest user = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString("user.json"))
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        try {
            HttpResponse<String> send = client.send(user, HttpResponse.BodyHandlers.ofString());
            System.out.println("send.statusCode() = " + send.statusCode());
            System.out.println("send.body() = " + send.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void upDateInfo() {
        String url = "https://jsonplaceholder.typicode.com/users/1";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.ofString("user.json"))
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("response.statusCode() = " + response.statusCode());
            System.out.println("response.body() = " + response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void delete() {
        String url = "https://jsonplaceholder.typicode.com/users/2";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        try {
            HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("send.statusCode() = " + send.statusCode());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void getAllUsers() {
        String url = "https://jsonplaceholder.typicode.com/users";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        try {
            HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("send.statusCode() = " + send.statusCode());
            System.out.println("send.body() = " + send.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void getUserId() {
        String url = "https://jsonplaceholder.typicode.com/users/2";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        try {
            HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("send.statusCode() = " + send.statusCode());
            System.out.println("send.body() = " + send.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void getUsername() {
        String url = "https://jsonplaceholder.typicode.com/users?username=Bret";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        try {
            HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("send.statusCode() = " + send.statusCode());
            System.out.println("send.body() = " + send.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
