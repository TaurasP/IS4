package lt.viko.eif.tpetrauskas.password;

public class Menu {
    public void printLoginOrRegister(){
        System.out.println("*** PRADZIA ***");
        System.out.println("1. Registracija");
        System.out.println("2. Prisijungimas");
        System.out.println("***");
    }

    public void printInputUsername() {
        System.out.println("Iveskite naudotojo varda:");
    }

    public void printPasswordMenu() {
        System.out.println("Pasirinkite slaptazodzio issaugojimo buda:");
        System.out.println("1. Automatinis sugeneravimas");
        System.out.println("2. Rankinis ivedimas");
    }

    public void printInputPassword() {
        System.out.println("Įveskite naudotojo slaptažodį:");
    }

    public void printString(String string) {
        System.out.println(string);
    }

    public void printPasswordControlMenu() {
        System.out.println();
        System.out.println("*** MANO SLAPTAZODZIAI ***");
        System.out.println("1. Sukurti naują");
        System.out.println("2. Surasti esamą ir atnaujinti slaptazodį");
        System.out.println("3. Surasti esamą ir istrinti");
        System.out.println("***");
    }
}
