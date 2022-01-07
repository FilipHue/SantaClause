package main;

import checker.Checker;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import database.Database;
import entities.PapaNoel;
import fileio.InputLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {

        File inputDirectory = new File(Constants.TESTS_PATH);
        File outputDirectory = new File(Constants.RESULT_PATH);
        Path resultPath = Paths.get(Constants.RESULT_PATH);

        delete(outputDirectory.listFiles());

        if (!Files.exists(resultPath)) {
            Files.createDirectories(resultPath);
        }

        delete(outputDirectory.listFiles());
        for (File file : Objects.requireNonNull(inputDirectory.listFiles())) {
            String filepath = Constants.OUTPUT_PATH + file.getName().substring(Constants.FOUR);
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     *
     * @param filePath1 for input files
     * @param filePath2 for output files
     */

    public static void action(final String filePath1, final String filePath2) throws IOException {

        InputLoader inputLoader = new InputLoader(filePath1);

        ObjectMapper objectMapper = new ObjectMapper();

        PapaNoel papaNoel = new PapaNoel();
        inputLoader.readData();
        Database database = Database.getInstance();

        FirstRound.initSetup(database, papaNoel);

        for (int year = 1; year <= database.getNumberOfYears(); year++) {
            NextRound.playRound(database, papaNoel, year);
        }

        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(filePath2), database.getAnnualChanges());
        database.clearAll();
    }

    /**
     *
     * @param directory The name of the output directory
     */

    public static void delete(final File[] directory) {
        if (directory != null) {
            for (File file : directory) {
                if (!file.delete()) {
                    System.out.println("Didn't delete");
                }
            }
        }
    }

}
