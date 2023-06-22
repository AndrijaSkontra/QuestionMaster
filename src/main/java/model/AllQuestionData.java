package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AllQuestionData {

    public static ArrayList<String> questionAnswers = new ArrayList<>();
    public static ArrayList<String> allQuestions = new ArrayList<>();


    public static void appendToTestsData(String percetnageOfKnownAnswers) {
        Date date = new Date();
        try {
            File currentDir = new File(".");
            File parentDir = currentDir.getParentFile();
            File newFile = new File(parentDir,"src\\main\\java\\model\\tests_data.txt");
            FileWriter fw = new FileWriter(newFile, true);
            String toWrite = "File: " + (AUXFileReading.file != null ? AUXFileReading.file.getName() : "NUMBER MODE") + "\nTime: " + date +
                    "\nAccuracy: " + percetnageOfKnownAnswers + "\n\n";
            fw.write(toWrite);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
