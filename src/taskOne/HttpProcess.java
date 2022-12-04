package taskOne;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import otions.Comment;
import otions.Post;
import otions.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
        System.out.println("user.toDo(1) = " + user.toDo(1));
        user.getCommentsToLastPost(4);

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

    public int getLastPost(int id) throws IOException, InterruptedException {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + id + "/posts"))
                .GET()
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Post> posts = new Gson().fromJson(send.body(), new TypeToken<List<Post>>() {
        }.getType());

        return getLastPostId(posts);
    }

    public void getCommentsToLastPost(int userId) throws IOException, InterruptedException {
        int lastPostId = getLastPost(userId);
        URI uri = null;
        try {
            uri = new URI("https://jsonplaceholder.typicode.com/posts/" + userId + "/comments");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assert response != null;
        List<Comment> comments = new Gson().fromJson(response.body(), new TypeToken<List<Comment>>() {
        }
                .getType());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("user-" + userId +
                "-post-" + lastPostId + "-comments.json")) {
            gson.toJson(comments, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Todo> toDo(int id) {
        String url = "https://jsonplaceholder.typicode.com/users/";
        Gson gson = new Gson();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + id + "/todos?completed=false"))
                .GET()
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpResponse<String> send = null;
        try {
            send = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert send != null;
        return gson.fromJson(send.body(), new TypeToken<List<Todo>>() {
        }.getType());
    }

    private int getLastPostId(List<Post> post) {
        Integer lastId = 0;
        for (Post o : post) {
            if (o.getId() > lastId) {
                lastId = o.getId();
            }
        }
        return lastId;
    }

}
