package global.goit.httpstatus;

import java.util.Scanner;

public class HttpImageStatusCli {
    private final HttpStatusImageDownloader downloader;

    public HttpImageStatusCli() {
        downloader = new HttpStatusImageDownloader();
    }

    public void askStatus() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter HTTP status code: ");
        String input = scanner.nextLine();

        try {
            int statusCode = Integer.parseInt(input);
            try {
                downloader.downloadStatusImage(statusCode);
            } catch (Exception e) {
                System.out.println("There is no image for HTTP status " + statusCode);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
        }
    }

}
