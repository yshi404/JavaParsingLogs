package com.company;

import com.opencsv.CSVWriter;

import java.io.*;

public class Main {

    public static void csvParse(String separator, String path, String csvName) throws IOException {

        //Путь до логов
        File myFolder = new File(path.replaceAll(",", ""));
        File[] logs = myFolder.listFiles();
        
        //Создание нового csv
        String newFile = csvName + ".csv";
        File dir = new File(System.getProperty("user.dir"));
        File newLog = new File(dir, newFile);
        FileWriter fileWriter = new FileWriter(newLog);
        char sep = separator.charAt(0);
        CSVWriter writer = new CSVWriter(fileWriter,
                sep,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);

        //Считываем логи
        for (File log : logs) {

            File file = new File(log.toString());
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            //Записываем в новый файл
            while (line != null) {
                //System.out.println(line);
                String[] record = line.split("\\s+", 5);
                writer.writeNext(record);
                line = reader.readLine();
            }

        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {

        if (args.length != 3) {
            System.out.println("usage: : <тип_разделителя>,<путь_до_лог_файла(ов)>,<имя_нового_файла>");
        } else {
            csvParse(args[0], args[1], args[2]);
        }
    }
}