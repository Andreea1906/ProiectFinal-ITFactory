package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Metode {

    public void afiseazaCursuri(Map<Curs, List<Student>> dataMap) {
        System.out.println(dataMap.keySet());
    }

    public void adaugaCurs(Curs curs, Map<Curs, List<Student>> dataMap) {
        dataMap.put(curs, new ArrayList<>());
    }
}
