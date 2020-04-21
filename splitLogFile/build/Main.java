import java.io.*;

public class Main {

    public static void splitFile(File f, String fileName) throws IOException {

        int partCounter = 1;
        int sizeOfFiles = 1024 * 7168; //7 MB
        byte[] buffer = new byte[sizeOfFiles];

        //Создание папки для логов
        File dir = new File(System.getProperty("user.dir"));
        String folderName = "\\logs";
        File folder = new File(dir + folderName);
        folder.mkdir();

        //Разбивка лога
        try(FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {
                String filePartName = String.format("%s.%03d.log", fileName, partCounter++);
                File newFile = new File(folder, filePartName);
                System.out.println("created: " + newFile);
                try (FileOutputStream out = new FileOutputStream(newFile)) {
                    out.write(buffer, 0, bytesAmount);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        if(args.length != 2) {
            System.out.println("usage: : <имя_лог_файла>,<постоянная_часть_имени_полученных_файлов> ");
        }
        else {
            splitFile(new File(args[0]), args[1]);
        }
    }
}
