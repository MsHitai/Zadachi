package sixth.sprint;

public class TextFixer {

    public String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public String fixText(String text) {
        if (text.contains("„")) {
            text = text.replace("„", "«");
        }
        if (text.contains("“")) {
            text = text.replace("“", "»");
        }
        if (text.contains("цевилизаций")) {
            text = text.replace("цевилизаций", "цивилизаций");
        }
        text = text.trim();
        return capitalize(text);
    }

    public static void main(String[] args) {

        String text = "    история каждой из крупных галактических цевилизаций может быть разделена на три различные, " +
                "ярко выраженные фазы: Борьба за выживание, Любопытство и Утонченность, также именуемые фазами " +
                "„Как?“, „Зачем?“ и „Где?“. Пример: если для первой фазы характерен вопрос: „Как бы нам поесть?“," +
                " а для второй „Зачем мы едим?“, то третья отличается вопросом: „Где бы нам лучше поужинать?“.   ";

        var textFixer = new TextFixer();
        var fixedText = textFixer.fixText(text);
        System.out.println(fixedText);

        StringBuilder initial = new StringBuilder("Большая красная кнопка");

        initial.replace(8, 15, "белая");
        initial.delete(14, 30);
        initial.append("груша");
        initial.setLength(7);
        initial.reverse();

        System.out.println(initial.toString().toLowerCase());
    }
}
