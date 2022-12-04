package taskOne;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import otions.Comment;
import otions.Todo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class HttpProcess {


    public static void main(String[] args) throws IOException, InterruptedException {
        HttpProcess user = new HttpProcess();
        info();
        upDateInfo();
        getAllUsers();
        getUsername();
        delete();
        getUserId();
        System.out.println("user.toDo(4) = " + user.toDo(4));
        System.out.println("user.getComments(1) = " + user.getComments(1));
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
        } catch (IOException | InterruptedException e) {
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
        } catch (IOException | InterruptedException e) {
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
        } catch (IOException | InterruptedException e) {
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
        } catch (IOException | InterruptedException e) {
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
        } catch (IOException | InterruptedException e) {
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
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Comment> getComments(int id) throws IOException, InterruptedException {
        String userURL = "https://jsonplaceholder.typicode.com/users/";
        int y = id * 10;
        Gson gson = new Gson();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(userURL + id + "posts")))
                .header("Content-type", "")
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Comment> list = gson.fromJson(send.body(), new TypeToken<List<Comment>>(){}.getType());

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("user-" + id + "-post-" + y + "-comments.json"));

        return list;
    }


    public List<Todo> toDo(int id) {
        String url = "https://jsonplaceholder.typicode.com/users/";
        Gson gson = new Gson();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url + id + "/todos?completed=false")))
                .GET()
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpResponse<String> send = null;
        try {
            send = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Todo> list = gson.fromJson(send.body(), new TypeToken<List<Todo>>() {
        }.getType());
        return list;
    }
}
