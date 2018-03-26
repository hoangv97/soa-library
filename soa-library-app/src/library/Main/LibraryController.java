package library.Main;

import com.google.gson.Gson;
import library.AppConfig.Config;
import library.Model.Book;
import library.Model.BookAPI;
import library.Model.PostResponseAPI;
import library.Model.ResponseAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LibraryController {

    private Gson gson = new Gson();

    public ArrayList getBooks() {
        ArrayList<Book> books = new ArrayList<>();
        ResponseAPI responseAPI = gson.fromJson(requestURL(Config.BASE_URL + "/books", "GET", null), ResponseAPI.class);

        if(responseAPI.getStatus() == 200) {
            books = responseAPI.getBooks();
        }

        return books;
    }

    public PostResponseAPI createNewBook(BookAPI book) {
        System.out.println("Create Book: " + book.toString());
        return gson.fromJson(requestURL(Config.BASE_URL + "/books", "POST", gson.toJson(book)), PostResponseAPI.class);
    }

    public PostResponseAPI updateBook(Book book) {
        BookAPI bookAPI = book.getBookAPI();
        System.out.println("Update Book: " + bookAPI.toString());
        return gson.fromJson(requestURL(Config.BASE_URL + "/books/" + book.getId(), "PUT", gson.toJson(bookAPI)), PostResponseAPI.class);
    }

    public ResponseAPI deleteBook(Book book) {
        System.out.println("Delete Book: " + book.toString());
        return gson.fromJson(requestURL(Config.BASE_URL + "/books/" + book.getId(), "DELETE", null), ResponseAPI.class);
    }


    /**
     * Send request to server by API
     * @param _url
     * @param method
     * @param input
     * @return
     */
    private String requestURL(String _url, String method, String input) {
        try {
            URL url = new URL(_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Accept", "application/json");

            if(input != null && (method.equals("POST") || method.equals("PUT"))) {
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                os.write(input.getBytes());
                os.flush();
            }

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output = "", line;
            while ((line = br.readLine()) != null) {
                output = output.concat(line);
            }

            conn.disconnect();

            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}