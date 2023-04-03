package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DateSaver {

    public void saveData(Map<Curs, List<Student>> date) throws IOException {
        saveCourses(date);
        saveStudents(date);
        saveMappings(date);
    }

    private void saveMappings(Map<Curs, List<Student>> date) throws IOException {
        String continut = "";

        for(Map.Entry<Curs, List<Student>> felie : date.entrySet()) {
            Curs curs = felie.getKey();

            int cursId = curs.getIdCurs();
            for(Student student : felie.getValue()) {
                int studentId = student.getIdStudent();
                continut = continut.concat(studentId+","+cursId+"\n");
            }
        }

        DataLoaderUtils.writeFile(DataLoaderUtils.MAPPING_FILE_PATH, continut);

    }

    private void saveStudents(Map<Curs, List<Student>> date) throws IOException {
        String continut = "";
        for (List<Student> valoriDinMap: date.values()) {
            for(Student student: valoriDinMap) {
                continut = continut.concat(student.toString().concat("\n"));
            }
        }


        DataLoaderUtils.writeFile(DataLoaderUtils.STUDENT_FILE_PATH_SAVE, continut);
    }

    private void saveCourses(Map<Curs, List<Student>> date) throws IOException {
        Set<Curs> setCursuri = date.keySet();
        String continut = "";
        for(Curs curs : setCursuri) {
            continut = continut.concat(curs.toString().concat("\n"));
        }

        DataLoaderUtils.writeFile(DataLoaderUtils.COURSE_FILE_PATH, continut);
    }
}
