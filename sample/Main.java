package sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<String, String> dictionary = new HashMap<>();

    public static void main(String[] args) {

        loadDictionary();

        try {

            BufferedReader reader = new BufferedReader(new FileReader("English.in"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("Ukrainian.out"));
            String line;

            while ((line = reader.readLine()) != null) {

                String[] words = line.split("\\s+");

                for (String word : words) {

                    String translatedWord = dictionary.get(word);
                    writer.write(translatedWord + " ");

                }

                writer.write(System.lineSeparator());

            }

            reader.close();
            writer.close();
            System.out.println("Переклад завершено. Результат збережено у файлi: Ukrainian.out");

        } catch (IOException e) {
            e.printStackTrace();
        }

        manualFillDictionary();

        saveDictionary();
    }

    private static void loadDictionary() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"));
            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                if (parts.length == 2) {

                    String englishWord = parts[0].trim();
                    String ukrainianWord = parts[1].trim();
                    dictionary.put(englishWord, ukrainianWord);

                }
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveDictionary() {
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt"));

            for (Map.Entry<String, String> entry : dictionary.entrySet()) {

                writer.write(entry.getKey() + " = " + entry.getValue() + System.lineSeparator());

            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addToDictionary(String englishWord, String ukrainianWord) {
        dictionary.put(englishWord, ukrainianWord);
    }

    public static void manualFillDictionary() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Додавання слiв до словника (введiть ще один Enter для виходу):");

        while (true) {

            System.out.print("Англiйське слово: ");
            String englishWord = scanner.nextLine();

            if (englishWord.equals("")) {
                break;
            }

            System.out.print("Украiнське слово: ");
            String ukrainianWord = scanner.nextLine();

            if (ukrainianWord.equals("")) {
                break;
            }

            addToDictionary(englishWord, ukrainianWord);

        }

        scanner.close();

    }
}