package org.example;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // incarcam date din fisier
        DataLoader dl = new DataLoader();
        // salvam date in fisier
        DateSaver ds = new DateSaver();
        dl.loadData();
        Map<Curs, List<Student>> date = dl.getDataMap();
        // ar fi  bine ca in clasa Metode sa scrieti toate metodele din main si aici doar sa le apelati
        Metode metode = new Metode();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Puteti introduce urmatoarele optiuni:\n" +
                "0 – Ies din program.\n" +
                "1 – Afiseaza cursuri\n" +
                "2 – Introduceti un curs nou\n" +
                "3 – Introduceti un student nou si inrolati-l la curs\n" +
                "4 – Cautati un student dupa nume (OPTIONAL)\n" +
                "5 – Afiseaza studentii si cursul la care participa. (OPTIONAL)");



        while(true) {
            System.out.println("Va rugam introduceti o optiune");
            int optiune = Integer.parseInt(scanner.nextLine()); // scanner.nextInt()

            switch (optiune) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Afiseaza cursuri:");
                    metode.afiseazaCursuri(date);
                    break;
                case 2:
                    System.out.println("Introduceti un curs nou:");
                    System.out.println("Introduceti id-ul cursului:");
                    int idCurs = Integer.parseInt(scanner.nextLine());
                    System.out.println("Introduceti numele cursului:");
                    String numeCurs = scanner.nextLine();
                    System.out.println("Introduceti pretul cursului:");
                    double pretCurs = Double.parseDouble(scanner.nextLine());
                    System.out.println("Introduceti data cursului in formatul: dd/MM/yyyy");
                    String dataCursString = scanner.nextLine();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataCurs = LocalDate.parse(dataCursString, format);

                    Curs cursNou = new Curs(idCurs, numeCurs, pretCurs, dataCurs);
                    metode.adaugaCurs(cursNou, date);
                    metode.afiseazaCursuri(date);

                    ds.saveData(date);
                    break;
                case 3:
                    System.out.println("Introduceti un student nou si inrolati-l la curs");
                    //metode.afiseazaCursuri(date);

                    int cursuriDisponibile = 0;
                    for(Map.Entry<Curs, List<Student>> felie: date.entrySet()) {
                        if (felie.getValue().size() < 9 ) {
                            System.out.println("CURS disponibil: "+felie.getKey());

                            cursuriDisponibile++;
                        }
                    }

                    if(cursuriDisponibile == 0) {
                        // aici puteti crea si o lista de asteptare, o structura asemanatoare cu ce avem
                        System.out.println("Ne pare rau, nu avem niciun curs disponibil");
                        break;
                    }


                    System.out.println("Introduceti numele unui curs din lista de mai sus:");
                    String numeCursCautat = scanner.nextLine();

                    Curs cursgasit = null;
                    LocalDate acum = LocalDate.now();

                    for(Curs curs: date.keySet()) {
                        if (curs.getNumeCurs().equals(numeCursCautat) && acum.isBefore(curs.getDataInceput())) {
                            System.out.println("Am gasit cursul");
                            cursgasit = curs;
                            break;
                        }
                    }

                    // ca sa evitam exceptiile nullpointer, avem grija sa nu lucram mai departe cu curs daca este null
                    if(cursgasit == null) {
                        System.out.println("Acest curs nu exista");
                        break;
                    }

                    System.out.println("Introdu idul studentului:");
                    int idStudent = Integer.parseInt(scanner.nextLine());

                    System.out.println("Introdu numele studentului:");
                    String numeStudent = scanner.nextLine();

                    System.out.println("Introdu bugetul studentului:");
                    double bugetStudent = Double.parseDouble(scanner.nextLine());

                    Student student = new Student(idStudent,numeStudent,bugetStudent);

                    System.out.println("Ati introdus studentul: "+student);

                    try {
                        // se incearca setarea bugetului studentului care poate arunca o exceptie
                        student.setBugetStudent(bugetStudent - cursgasit.getPretCurs());
                        // adaug studentul la curs doar daca nu avem exceptie, daca apare exceptia, linia asta nu se mai executa
                        date.get(cursgasit).add(student);

                    } catch (BugetInvalidException e) {
                        System.out.println("Bugestul este invalid");
                    }
                    // aici putem scrie si un mesaj de confirmare ca s-a introdus studentul xy la cursul z
                    System.out.println(dl.getDataMap());
                    ds.saveData(dl.getDataMap());

                    break;
                case 4:
                    System.out.println("Cautati un student dupa nume");

                    System.out.println("Introduceti numele studentului:");
                    String numeStudentCautat = scanner.nextLine();
                    // ideal ar fi ca si aceste foruri sa mearga intr-o metoda separata,in main doar apelam metodele implementate
                    for(List<Student> lista : date.values()) {
                        for (Student studentDinLista:lista) {
                            if (studentDinLista.getNumeStudent().contains(numeStudentCautat)) {
                                System.out.println("Am gasit studentul: " + studentDinLista);
                            }
                        }
                    }

                    break;
                case 5:
                    System.out.println("Afiseaza studentii si cursul la care participa");

                    for (Curs curs : date.keySet()) {
                        System.out.println("La cursul " + curs.getNumeCurs() + " participa urmatorii studenti:");
                        for (Student studentDeLaCurs : date.get(curs)) {
                            System.out.println(studentDeLaCurs.getNumeStudent());
                        }
                        System.out.println("\n");
                    }
                    break;
                default:
                    System.out.println("Aceasta optiune nu exista");
            }
        }
    }
}