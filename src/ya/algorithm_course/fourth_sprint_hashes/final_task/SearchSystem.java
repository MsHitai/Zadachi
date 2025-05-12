package ya.algorithm_course.fourth_sprint_hashes.final_task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://contest.yandex.ru/contest/24414/run-report/136995244/">...</a>
 * <p>
 * -- ПРИНЦИП РАБОТЫ --
 * После считывания данных, мы строим по этим данным поисковый индекс, т.е. сохраняем позиции слов документов и запоминаем
 * их релевантность. То есть по слову в документе, сохраняем его номер и количество вхождений. Для этого я использовала
 * мапу в мапе. После построения индекса, для каждого уникального слова в отдельном поисковом запросе соотносим номер
 * документа и определяем его релевантность (количество встречаний слова во всех документах). После этого, сортируем
 * идентификаторы документов (сначала проверяем по кол-ву встречаний count от большего к меньшему, а при равности
 * смотрим на порядок документов, от более раннего, к более позднему).
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Рассмотрим первый тест кейс, где нам даны "i love coffee", "coffee with milk and sugar" и "free tea for everyone" и
 * рассмотрим пошагово поиск для первого поискового запроса "i like black coffee without milk". Итак, после считывания
 * данных идем в метод searchText и оттуда переходим в метод построения индекса. Разделяем текст на отдельные слова и
 * кладем слово к номеру документа и включаем счетчик. (Изначально у меня был метод с добавлением индекса с помощью
 * getOrDefault, вида indexToCount.put(i + 1, indexToCount.getOrDefault(indexToCount.get(i + 1), 0) + 1); Но я немного
 * запуталась с индексами и считалось не всегда правильно, потом встретился метод computeIfAbsent и merge, c которыми были
 * уже стабильные результаты). Итак, итоговый индекс будет вида - {love={1=1}, tea={3=1}, with={2=1}, everyone={3=1},
 * and={2=1}, coffee={1=1, 2=1}, for={3=1}, milk={2=1}, i={1=1}, free={3=1}, sugar={2=1}}
 * <p>
 * Смотрим поисковой запрос, берем из него только уникальные слова. Идем по этим уникальным словам. Порядок в сете может
 * быть любым, но для простоты примера, будем смотреть по порядку. Первое слово "i". Смотрим в индексе (positions) есть
 * ли такое слово в текстах, да, встречается в 1 документе. Кладем в мапу, где видели это слово номер документа и начинаем
 * вести счетчик сколько еще нам встретится слов из нашего поискового запроса плюс количество, которое встретили по слову.
 * Смотрим дальше, "like" и "black" не было в тексте, пропускаем их, дальше "coffee". Кофе встречалось в 1 документе 1 раз
 * и во 2 один раз. Кладем в мапу seenIn, получим, что для 1го документа у нас уже 2 попадания, а для 2го пока один (1 от
 * i и 1 от coffee для первого документа). Далее, "without" в тексте не было, но был "milk", его видели во 2м документе
 * один раз, кладем это значение в seenIn, получается, что по 2му документу у нас также 2 попадания.
 * <p>
 * Слова в этом запросе закончились, сохраняем результат в отсортированном виде. По условию, нам нужно было сортировать
 * по убыванию от количества вхождений (здесь получаем 2=2, т.к. для обоих документов получили 2 попадания), либо в
 * возрастающем порядке от номера документа. Это наш случай, получаем результат 1 2 - номера документов по возрастанию.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Считывание данных, два линейных прохода O(d), где d-количество документов и O(s), где s-количество поисковых запросов.
 * Построения индекса - идем по количеству документов и внутри смотрим на все слова в отдельном документе, т.е. O(d * w),
 * где w - количество слов в конкретном документе, плюс разбивка String.split(" "); также занимает O(n), где n - длина
 * документа, плюс в хеш-мапе мы храним ключ-строку, хеш которой нужно вычислить за O(n), где n - длина конкретной строки.
 * Идем дальше в поиск текста. Здесь идет в цикле по поисковым запросам O(s), линейное разбиение слов по String.split(" ");
 * и проход по уникальным словам, в худшем случае все слова в запросе уникальны (O(s)), как было в разобранном примере выше.
 * Далее, используем встроенную сортировку O(d log d) на каждый поисковой запрос, берем из нее 5 первых элементов и
 * сохраняем результат. Итого получаем O(s * (d log d + d * w)).
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Храним 2 массива входных данных, мапу в мапе для поискового индекса, ключ в которой является строкой и в памяти занимает
 * место пропорциональное ее длине (O(n)), сет уникальных слов поиска, список списков результата, еще одну мапу, которая
 * перезаписывается в цикле для расчета релевантности документов. Я думаю, что итоговая сложность
 * будет O(d * uw * n) , где d - количество документов и uw - количество уникальных слов поискового запроса, а n - длина
 * слова, хранимого в качестве ключа в мапе.
 */
public class SearchSystem {

    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int textsSize = Integer.parseInt(reader.readLine());
            String[] texts = new String[textsSize];
            for (int i = 0; i < textsSize; i++) {
                texts[i] = reader.readLine();
            }
            int searchSize = Integer.parseInt(reader.readLine());
            String[] searches = new String[searchSize];
            for (int i = 0; i < searchSize; i++) {
                searches[i] = reader.readLine();
            }
            List<String> result = searchText(texts, searches).stream()
                    .map(list -> list.stream().map(String::valueOf).collect(Collectors.joining(" ")))
                    .toList();
            System.out.println(String.join("\n", result));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<List<Integer>> searchText(String[] texts, String[] searches) {
        List<List<Integer>> result = new ArrayList<>();
        Map<String, Map<Integer, Integer>> positions = constructSearchIndex(texts);
        Set<String> uniqueWords;
        Map<Integer, Integer> seenIn;
        for (String search : searches) {
            uniqueWords = new HashSet<>(Arrays.stream(search.split(" ")).toList());
            seenIn = new HashMap<>();
            for (String word : uniqueWords) {
                Map<Integer, Integer> documents = positions.getOrDefault(word, Collections.emptyMap());
                for (Map.Entry<Integer, Integer> entry : documents.entrySet()) {
                    int documentId = entry.getKey();
                    int count = entry.getValue();
                    seenIn.put(documentId, seenIn.getOrDefault(documentId, 0) + count);
                }
            }
            List<Integer> sortedDocs = seenIn.entrySet().stream()
                    .sorted((entry1, entry2) -> {
                        int compare = Integer.compare(entry2.getValue(), entry1.getValue());
                        if (compare != 0) {
                            return compare;
                        }
                        return Integer.compare(entry1.getKey(), entry2.getKey());
                    })
                    .map(Map.Entry::getKey)
                    .limit(5)
                    .collect(Collectors.toList());
            result.add(sortedDocs);
        }

        return result;
    }

    private static Map<String, Map<Integer, Integer>> constructSearchIndex(String[] texts) {
        Map<String, Map<Integer, Integer>> positions = new HashMap<>();
        for (int i = 0; i < texts.length; i++) {
            int docId = i + 1;
            String[] words = texts[i].split(" ");
            for (String word : words) {
                positions.computeIfAbsent(word, k -> new HashMap<>())
                        .merge(docId, 1, Integer::sum);
            }
        }
        return positions;
    }
}
