package lt.viko.eif.tpetrauskas.file;

import lt.viko.eif.tpetrauskas.password.AES;
import lt.viko.eif.tpetrauskas.password.Pass;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileService {

    private static Pass createPassword(String[] metadata) {
        String name = metadata[0];
        String password = AES.decrypt(metadata[1], "secret");
        String app = metadata[2];
        String comment = metadata[2];

        return new Pass(name, password, app, comment);
    }

    public void updatePasswordByName(String name) {
        List<Pass> passwords = getAllPasswords();
        for (Pass p : passwords) {
            if (p.getName().equals(name)) {
                updateAttributes(p);
            }
        }
        saveAllPasswords(passwords);
    }

    private void updateAttributes(Pass p) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Rastas slaptažodis: " + p);
        System.out.println("Iveskite nauja slaptažodį: ");
        p.setPassword(sc.nextLine());
        System.out.println("Naujas slaptažodis: " + p);
    }

    public void deletePasswordByName(String name) {
        List<Pass> passwords = getAllPasswords();
        Iterator<Pass> iter = passwords.iterator();

        while (iter.hasNext()) {
            Pass p = iter.next();

            if (p.getName().equals(name))
                iter.remove();
        }
        saveAllPasswords(passwords);
    }

    public void saveUserAndEncrypt(String username, String password) {
        try {
            FileWriter myWriter = new FileWriter("user.txt");
            String data = AES.encrypt(username + " " + password, "secret");
            myWriter.write(data);
            myWriter.close();
            //System.out.println("Successfully wrote to the file.: " + data);
        } catch (IOException e) {
            System.out.println("Klaida.");
            e.printStackTrace();
        }
    }

    public void savePasswordToCsv(Pass pass) {
        List<Pass> passwords = getAllPasswords();
        passwords.add(pass);
        System.out.println("Slaptazodis pridetas: " + pass);
        saveAllPasswords(passwords);
    }

    private void saveAllPasswords(List<Pass> passwords) {
        final char CSV_SEPARATOR = ',';

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("passwords.csv"))) {
            passwords.forEach(psw -> {
                try {
                    writer.append(psw.getName()).append(CSV_SEPARATOR)
                            .append(AES.encrypt(psw.getPassword(), "secret")).append(CSV_SEPARATOR)
                            .append(psw.getApp()).append(CSV_SEPARATOR)
                            .append(psw.getComment()).append(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private List<Pass> getAllPasswords() {
        List<Pass> passwords = new ArrayList<>();
        Path pathToFile = Paths.get("passwords.csv");

        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                Pass password = createPassword(attributes);
                passwords.add(password);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return passwords;
    }

    public String getUserStringFromFileAndDecrypt() {

        String data = "";
        try {
            File myObj = new File("user.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println("Nuskaityta is " + myObj.getName() +  ", uzsifruotas slaptazodis: " + data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Klaida.");
            e.printStackTrace();

        }
        return AES.decrypt(data, "secret");
    }
}
