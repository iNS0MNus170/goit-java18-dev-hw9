package global.goit;

import global.goit.httpstatus.HttpImageStatusCli;
import global.goit.httpstatus.HttpStatusChecker;

public class Main {
    public static void main(String[] args) {
        HttpStatusChecker checker = new HttpStatusChecker();
        try {
            System.out.println(checker.getStatusImage(200));
            System.out.println(checker.getStatusImage(300));
            System.out.println(checker.getStatusImage(404));
            //System.out.println(checker.getStatusImage(600)); - exception
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        HttpImageStatusCli cli = new HttpImageStatusCli();
        cli.askStatus();

    }
}
