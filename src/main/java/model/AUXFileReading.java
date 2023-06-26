package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * This class is used to read the questions from the file and store them in an ArrayList.
 */
public class AUXFileReading {

    private static File file;
    private static ArrayList<String> questionList = new ArrayList<>();

    public static void setFile(File file) {
        AUXFileReading.file = file;
    }

    /**
     * This method is used to split the file into an ArrayList
     * so every line in file is one element in ArrayList.
     * @throws IOException - read IOException javadoc.
     */
    public static void splitFileToList() throws IOException {

        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            questionList.add(sc.nextLine());
        }
    }

    /**
     * This method is used to get the statistics from the tests_data.txt file.
     * @return - the statistics from the tests_data.txt file.
     */
    public static String getStatistics() {

        String statisticsText = "";
        try {
            File currentDir = new File(".");
            File parentDir = currentDir.getParentFile();
            File newFile = new File(parentDir,"src\\main\\java\\model\\tests_data.txt");
            BufferedReader bf = new BufferedReader(new FileReader(newFile));
            // This line is used to read the whole file.
            statisticsText = bf.lines().collect(Collectors.joining(System.lineSeparator()));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return statisticsText;
    }

    /**
     * Called when the user chooses the number mode.
     * Sets the questionList to numbers from 1 to numberOfQuestions.
     * @param numberOfQuestions - the number of questions the user wants to answer.
     */
    public static void numberModeQuestionList(int numberOfQuestions) {
        for (int i = 0; i < numberOfQuestions; i++) {
            questionList.add((i + 1) + ".");
        }
    }

    public static File getFile() {
        return file;
    }

    public static int getQuestionListSize() {
        return questionList.size();
    }

    public static String getQuestionListElement(int index) {
        return questionList.get(index);
    }

    public static void removeQuestionListElement(int index) {
        questionList.remove(index);
    }
}

