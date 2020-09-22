import com.jgybzx.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jgybzx
 * @date 2020/9/19 11:05
 * @description
 */
public class testList {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Student student = new Student();
            student.setId(String.valueOf(i));
            student.setAddress("A" + i);
            list.add(student);
        }
        for (int i = 0; i < 4; i++) {
            Student student = new Student();
            student.setId(String.valueOf(i));
            student.setAddress("B" + i);
            list.add(student);
        }
        list.stream().forEach(System.out::println);
        List<Student> collect = list.stream()
                .sorted(Comparator.comparing(Student::getId)
                        .thenComparing(Comparator.comparing(Student::getAddress).reversed())).collect(Collectors.toList());
        collect.stream().forEach(System.err::println);
    }
}
