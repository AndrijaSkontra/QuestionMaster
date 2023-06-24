package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class QuestionsAndAnswersData {

    private static ArrayList<String> allAnswers = new ArrayList<>();
    private static ArrayList<String> allQuestions = new ArrayList<>();

    public static void appendToTestsData(String percetnageOfKnownAnswers) {
        Date date = new Date();
        try {
            File currentDir = new File(".");
            File parentDir = currentDir.getParentFile();
            File newFile = new File(parentDir,"src\\main\\java\\model\\tests_data.txt");
            FileWriter fw = new FileWriter(newFile, true);
            String toWrite = "File: "
                    + (AUXFileReading.getFile() != null ? AUXFileReading.getFile().getName() : "NUMBER MODE")
                    + "\nTime: "
                    + date
                    + "\nAccuracy: "
                    + percetnageOfKnownAnswers
                    + "\n\n";
            fw.write(toWrite);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToAllAnswers(String answer) {
        allAnswers.add(answer);
    }

    public static void addToAllQuestions(String question) {
        allQuestions.add(question);
    }

    public static int getAllAnswersSize() {
        return allAnswers.size();
    }

    public static int getAllQuestionsSize() {
        return allQuestions.size();
    }

    public static void clearAllAnswers() {
        allAnswers.clear();
    }

    public static void clearAllQuestions() {
        allQuestions.clear();
    }

    public static String getAllQuestionElement(int index) {
        return allQuestions.get(index);
    }

    public static String getAllAnswerElement(int index) {
        return allAnswers.get(index);
    }

    public static ArrayList<String> getAllAnswers() {
        return allAnswers;
    }
}
