package Exemplul1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {

    public static void scriere(List<Angajati> lista) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            File file=new File("src/main/resources/angajati.json");
            mapper.writeValue(file,lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Angajati> citire() {
        try {
            File file=new File("src/main/resources/angajati.json");
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<Angajati> angajati = mapper
                    .readValue(file, new TypeReference<List<Angajati>>(){});
            return angajati;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
//        List<Angajati> angajati = new ArrayList<Angajati>();
//        angajati.add(new Angajati("Daniel","Manager", LocalDate.of(2020,03,12),4000f));
//        angajati.add(new Angajati("Marian","Securitate", LocalDate.of(2021,05,8),3000f));
//        angajati.add(new Angajati("Alexandra","Developer", LocalDate.of(2019,07,17),5000f));
//        angajati.add(new Angajati("Bianca","Developer", LocalDate.of(2022,01,3),4000f));
//        angajati.add(new Angajati("Dorin","Developer", LocalDate.of(2022,11,23),3500f));
//
//        for(Angajati a:angajati) {
//            System.out.println(a);
//        }

//        scriere(angajati);
        List<Angajati> angajati=citire();
        angajati.forEach(System.out::println);
        System.out.println("angajatii cu salariul peste 2500 sunt:");
        angajati.stream().filter(angajati1 ->angajati1.getSalariul()>2500 ).forEach(System.out::println);


        int ancurent=LocalDate.now().getYear();
        List<Angajati>angajatifiltru=angajati.stream()
                .filter(angajati1 -> angajati1.getData_angajarii().getMonth()== Month.APRIL && angajati1.getData_angajarii().getYear()==(ancurent-1))
                .filter(angajati1 -> angajati1.getPostul().contains("sef")|| angajati1.getPostul().contains("director")).collect(Collectors.toList());
        angajatifiltru.forEach(System.out::println);


    }
}
