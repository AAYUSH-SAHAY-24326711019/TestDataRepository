import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Git Data Commit Console App ===");
        System.out.print("Enter comma-separated values to append to dataset.csv: ");
        String data = sc.nextLine();

        try {
            // Run 'git add .'
            runCommand("git add .");

            // Append data to dataset.csv
            appendToCSV(data);

            // Get current date and time
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            // Run 'git commit -m "date time"'
            runCommand("git commit -m \"" + dateTime + "\"");

            // Run 'git push'
            runCommand("git push");

            System.out.println("\n✅ Data committed and pushed successfully!\n");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        // Ask to quit
        System.out.println("Press 'q' and Enter to quit.");
        while (!sc.nextLine().trim().equalsIgnoreCase("q")) {
            System.out.println("Press 'q' to quit.");
        }

        System.out.println("👋 Application closed.");
        sc.close();
    }

    private static void runCommand(String command) throws IOException, InterruptedException {
        System.out.println("Running: " + command);
        Process process = new ProcessBuilder("cmd.exe", "/c", command)
                .redirectErrorStream(true)
                .start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        process.waitFor();
    }

    private static void appendToCSV(String data) throws IOException {
        FileWriter writer = new FileWriter("dataset.csv", true);
        writer.write(data + "\n");
        writer.close();
        System.out.println("📁 Data appended to dataset.csv");
    }
}
