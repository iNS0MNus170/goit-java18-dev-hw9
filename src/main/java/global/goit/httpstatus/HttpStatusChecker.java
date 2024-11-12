package global.goit.httpstatus;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpStatusChecker {
    private static final String BASE_URL = "https://http.cat/";
    private final OkHttpClient client;

    public HttpStatusChecker() {
        client = new OkHttpClient();
    }

    public String getStatusImage(int code) throws Exception {
        String imageUrl = BASE_URL + code + ".jpg";
        Request request = new Request.Builder()
                .url(imageUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() && response.code() == 404) {
                throw new Exception("Image not found for HTTP status code: " + code);
            }

            return imageUrl;
        } catch (IOException e) {
            throw new Exception("An error occurred while fetching the image", e);
        }
    }
}
