package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AUXFileReading {

    public static File file;
    public static ArrayList<String> questionList = new ArrayList<>();
    public static String statisticsText = "";

    public static void splitFileToList() throws IOException {

        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            questionList.add(sc.nextLine());
        }
    }

    public static String getStatistics() {

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
}
