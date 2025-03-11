package ya.java_course.sprints.sixth;

public class Grades {

    public static void main(String[] args) {
        Grades grades = new Grades();

        String journal = "вероника,чехова,ФИЗИКА,5;анна,строкова,МАТЕМАТИКА,4;иван,петров,ГЕОМЕТРИЯ,5";

        grades.gradeBeautifier(journal);

        String[] reversed = new String[]{"Вероника Чехова физика — Безупречно", "Анна Строкова математика — Потрясающе",
                "Иван Петров геометрия — Безупречно"};

        System.out.println(grades.serializeGrades(reversed));
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private String gradeToString(String grade) {
        switch (grade) {
            case "5": {
                return "Безупречно";
            }
            case "4": {
                return "Потрясающе";
            }
            case "3": {
                return "Восхитительно";
            }
            case "2": {
                return "Прекрасно";
            }
            default:
                return "Очаровательно";
        }
    }

    // grades - строка вида "имя,фамилия,предмет,оценка;имя,фамилия,предмет,оценка;"
    public void gradeBeautifier(String grades) {
        String[] students = grades.split(";"); // реализуйте метод здесь
        for (int i = 0; i < students.length; i++) {
            String[] each = students[i].split(",");
            for (int j = 0; j < each.length; j++) {
                if (j == 0 || j == 1) {
                    each[j] = capitalize(each[j]);
                } else if (j == 2) {
                    each[j] = each[j].toLowerCase() + " — ";
                } else {
                    each[j] = gradeToString(each[j]) + "\n";
                }

            }

            System.out.println(grades = String.join(" ", each));
        }
    }

    private String gradeStringToInt(String grade) {
        switch (grade) {
            case "Безупречно": {
                return "5";
            }
            case "Потрясающе": {
                return "4";
            }
            case "Восхитительно": {
                return "3";
            }
            case "Прекрасно": {
                return "2";
            }
            default:
                return "1";
        }
    }

    // "имя,фамилия,предмет,оценка;имя,фамилия,предмет,оценка"
    public String serializeGrades(String[] grades) {
        String toReturn = "";
        for (int i = 0; i < grades.length; i++) {
            String[] separate = grades[i].split(" ");
            for (int j = 0; j < separate.length; j++) {
                if (j == 0 || j == 1) {
                    toReturn += separate[j].toLowerCase() + ",";
                } else if (j == 2) {
                    toReturn += separate[j].toUpperCase() + ",";
                } else if (j == 4) {
                    toReturn += gradeStringToInt(separate[j]) + ";";
                }
            }

        }
        return toReturn;
    }
}
