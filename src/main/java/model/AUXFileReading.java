package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AUXFileReading {

    private static File file;
    private static ArrayList<String> questionList = new ArrayList<>();

    public static void setFile(File file) {
        AUXFileReading.file = file;
    }

    public static void splitFileToList() throws IOException {

        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            questionList.add(sc.nextLine());
        }
    }

    public static String getStatistics() {

        String statisticsText = "";
        try {
            File currentDir = new File(".");
            File parentDir = currentDir.getParentFile();
            File newFile = new File(parentDir,"src\\main\\java\\model\\tests_data.txt");
            BufferedReader bf = new BufferedReader(new FileReader(newFile));
            statisticsText = bf.lines().collect(Collectors.joining(System.lineSeparator()));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return statisticsText;
    }

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

