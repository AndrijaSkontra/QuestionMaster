package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class is used to store all the answers and questions from the current test.
 * It also sends the data to a tests_data-txt file.
 * Uses 2 array lists to store the data. One for the answers and one for the questions.
 */
public class QuestionsAndAnswersData {

    private static ArrayList<String> allAnswers = new ArrayList<>();
    private static ArrayList<String> allQuestions = new ArrayList<>();

    /**
     * This method is used to append the data to the tests_data.txt file.
     * @param percetnageOfKnownAnswers - the percentage of the known answers from last attempt.
     */
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

    /**
     * This method is used to add an answer to the allAnswers ArrayList.
     * @param answer - the answer to be added.
     */
    public static void addToAllAnswers(String answer) {
        allAnswers.add(answer);
    }

    /**
     * This method is used to add a question to the allQuestions ArrayList.
     * @param question - the question to be added.
     */
    public static void addToAllQuestions(String question) {
        allQuestions.add(question);
    }

    /**
     * This method is used to get the size of the allAnswers ArrayList.
     * @return - the size of the allAnswers ArrayList.
     */
    public static int getAllAnswersSize() {
        return allAnswers.size();
    }

    /**
     * This method is used to get the size of the allQuestions ArrayList.
     * @return - the size of the allQuestions ArrayList.
     */
    public static int getAllQuestionsSize() {
        return allQuestions.size();
    }

    /**
     * This method is used to clear the allAnswers ArrayList.
     */
    public static void clearAllAnswers() {
        allAnswers.clear();
    }

    /**
     * This method is used to clear the allQuestions ArrayList.
     */
    public static void clearAllQuestions() {
        allQuestions.clear();
    }

    /**
     * This method is used to get an element from the allQuestions ArrayList.
     * @param index - the index of the element to be returned.
     * @return - the element from the allQuestions ArrayList.
     */
    public static String getAllQuestionElement(int index) {
        return allQuestions.get(index);
    }

    /**
     * This method is used to get an element from the allAnswers ArrayList.
     * @param index - the index of the element to be returned.
     * @return - the element from the allAnswers ArrayList.
     */
    public static String getAllAnswerElement(int index) {
        return allAnswers.get(index);
    }

    public static ArrayList<String> getAllAnswers() {
        return allAnswers;
    }
}
