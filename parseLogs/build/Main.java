import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void parseFile(String phrase, String path, String logName) {

        //Путь к папке до логов
        File myFolder = new File(path.replaceAll(",",""));
        File[] logs = myFolder.listFiles();

        String newFile = logName + ".txt";
        File dir = new File(System.getProperty("user.dir"));
        File new_log = new File(dir, newFile);
        System.out.println(new_log);

        //Цикл по всем логам 
        for (File log : logs) {
            System.out.println(log);

            try {

                File file = new File(log.toString());
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();

                try (FileWriter fileWriter = new FileWriter(new_log, true)) {

                    while (line != null) {
                        Pattern pattern1 = Pattern.compile(phrase.replaceAll(",",""));
                        Matcher matcher1 = pattern1.matcher(line);
                        if (matcher1.find() == true) {

                            fileWriter.write(line + "\n");
                        }
                        line = reader.readLine();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        if(args.length != 3) {
            System.out.println("usage: : <фраза_поиска>, <путь_до_лог_файла(ов)>, <имя_нового_файла> ");
        }
        else {
            parseFile(args[0], args[1], args[2]);
        }
    }
}
