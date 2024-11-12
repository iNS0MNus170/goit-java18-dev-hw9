package global.goit.httpstatus;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpStatusImageDownloader {
    private final HttpStatusChecker checker;
    private final OkHttpClient client;

    public HttpStatusImageDownloader() {
        checker = new HttpStatusChecker();
        client = new OkHttpClient();
    }

    public void downloadStatusImage(int code) throws Exception {
        String imageUrl = checker.getStatusImage(code);

        Request request = new Request.Builder()
                .url(imageUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to download image: " + response);
            }

            String fileName = code + ".jpg";
            try (InputStream inputStream = response.body().byteStream();
                 FileOutputStream outputStream = new FileOutputStream(fileName)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                System.out.println("Image saved as " + fileName);
            }
        } catch (IOException e) {
            throw new Exception("Error downloading image for status code " + code, e);
        }
    }
}
