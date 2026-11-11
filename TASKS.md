# Алгоритмы для подготовки к FAANG

Подборка решений из этого репозитория, которые стоит повторять перед собеседованиями. В неё вошли только задачи с аналогом на LeetCode, сгруппированные по паттернам — в том же порядке, что в roadmap [NeetCode 150](https://neetcode.io/roadmap). Отдельно — раздел про многопоточность.

Все решения проверены. Найденные ошибки исправлены, корявые решения заменены на эталонные: они скомпилированы и прогнаны на контрпримерах и случайных тестах против эталона.

**Как читать таблицы**
- **LC** — номер и сложность задачи на LeetCode (E — easy, M — medium, H — hard). Если указано «≈», то в репозитории решена похожая задача из Я.Практикума или Stepik, а ссылка ведёт на её ближайший аналог.
- **Идея** — то, что нужно уметь объяснить на собеседовании за 30 секунд.

---

## 1. Массивы и хеширование

| Задача | LC | Решение | Идея |
|---|---|---|---|
| Two Sum | [1](https://leetcode.com/problems/two-sum/) E | [EasySolutions#twoSum](src/leetcode/easy/EasySolutions.java) | в хеш-мапе ищем `target - x`, O(n) |
| Majority Element | [169](https://leetcode.com/problems/majority-element/) E | [EasySolutions#majorityElement](src/leetcode/easy/EasySolutions.java) | подсчёт в мапе; на собесе спросят Бойера–Мура за O(1) памяти |
| Isomorphic Strings | ≈[205](https://leetcode.com/problems/isomorphic-strings/) E | [WeirdComparison](src/ya/algorithm_course/fourth_sprint_hashes/WeirdComparison.java) | **исправлено**: нужна биекция, две мапы в обе стороны |
| Product of Array Except Self | [238](https://leetcode.com/problems/product-of-array-except-self/) M | [MediumSolutions#productExceptSelf](src/leetcode/medium/MediumSolutions.java) | **заменено на эталон**: префиксные и суффиксные произведения без деления |
| Contiguous Array | ≈[525](https://leetcode.com/problems/contiguous-array/) M | [Contest](src/ya/algorithm_course/fourth_sprint_hashes/Contest.java) | 0 → −1, ищем две одинаковые префиксные суммы |
| Repeated DNA Sequences | [187](https://leetcode.com/problems/repeated-dna-sequences/) M | [PatternSearch](src/leetcode/challenges/PatternSearch.java) | частоты подстрок фиксированной длины |
| Rabin–Karp: подстроки, встречающиеся ≥ k раз | ≈[1044](https://leetcode.com/problems/longest-duplicate-substring/) H | [SequenceKTimes](src/ya/algorithm_course/fourth_sprint_hashes/SequenceKTimes.java) | **переписано**: скользящий полиномиальный хеш, O(n) |
| Хеши подстрок через префиксы | ≈[1316](https://leetcode.com/problems/distinct-echo-substrings/) H | [PrefixHash](src/ya/algorithm_course/fourth_sprint_hashes/PrefixHash.java) | `h[r] − h[l−1]·p^len` по модулю |
| Design HashMap | ≈[706](https://leetcode.com/problems/design-hashmap/) E | [CustomHashTable](src/ya/algorithm_course/fourth_sprint_hashes/final_task/CustomHashTable.java) | **исправлено** удаление из середины цепочки; метод цепочек |
| Поисковая система (инвертированный индекс + top-5) | — | [SearchSystem](src/ya/algorithm_course/fourth_sprint_hashes/final_task/SearchSystem.java) | слово → {документ: частота}, сортировка по релевантности |

## 2. Два указателя

| Задача | LC | Решение | Идея |
|---|---|---|---|
| Valid Palindrome | [125](https://leetcode.com/problems/valid-palindrome/) E | [EasySolutions#isPalindrome](src/leetcode/easy/EasySolutions.java) | указатели с двух концов, пропускаем не-буквы |
| Move Zeroes | [283](https://leetcode.com/problems/move-zeroes/) E | [EasySolutions#moveZeroes](src/leetcode/easy/EasySolutions.java) | указатель записи и указатель чтения |
| Merge Sorted Array | [88](https://leetcode.com/problems/merge-sorted-array/) E | [EasySolutions#merge](src/leetcode/easy/EasySolutions.java) | слияние; можно без копии, если идти с конца |
| Remove Duplicates I / II | [26](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) E, [80](https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/) M | [EasySolutions](src/leetcode/easy/EasySolutions.java) | сравниваем с `nums[j−2]` |
| Is Subsequence | ≈[392](https://leetcode.com/problems/is-subsequence/) E | [SubselectChecker](src/ya/algorithm_course/third_sprint_sort/SubselectChecker.java) | **переписано**, теперь работает и с пустой строкой |
| Shortest Distance to a Character | ≈[821](https://leetcode.com/problems/shortest-distance-to-a-character/) E | [ClosestZero](src/ya/algorithm_course/first_sprint_pointers/final_test/ClosestZero.java) | два прохода: слева направо и справа налево |
| Assign Cookies | ≈[455](https://leetcode.com/problems/assign-cookies/) E | [Cookies](src/ya/algorithm_course/third_sprint_sort/Cookies.java) | **заменено на эталон**: сортировка и два указателя |
| Trapping Rain Water | [42](https://leetcode.com/problems/trapping-rain-water/) H | [HardSolutions#trapTwoPointers](src/leetcode/hard/HardSolutions.java) | двигаем указатель с меньшим максимумом; есть вариант на стеке |

## 3. Скользящее окно

| Задача | LC | Решение | Идея |
|---|---|---|---|
| Longest Substring Without Repeating Characters | ≈[3](https://leetcode.com/problems/longest-substring-without-repeating-characters/) M | [CountUniqueSubstring](src/ya/algorithm_course/fourth_sprint_hashes/CountUniqueSubstring.java) | сужаем окно, пока символ повторяется |
| Minimum Size Subarray Sum | [209](https://leetcode.com/problems/minimum-size-subarray-sum/) M | [MediumSolutions#minSubArrayLen](src/leetcode/medium/MediumSolutions.java) | расширяем справа, сжимаем слева |

## 4. Стек

| Задача | LC | Решение | Идея |
|---|---|---|---|
| Valid Parentheses | [20](https://leetcode.com/problems/valid-parentheses/) E | [ValidParentheses](src/leetcode/challenges/ValidParentheses.java) | стек открывающих скобок |
| Min Stack (у нас Max) | ≈[155](https://leetcode.com/problems/min-stack/) M | [StackMax](src/ya/algorithm_course/second_sprint_recursion/StackMax.java) | **заменено на эталон**: параллельный стек максимумов, всё за O(1) |
| Evaluate Reverse Polish Notation | ≈[150](https://leetcode.com/problems/evaluate-reverse-polish-notation/) M | [PolskaCalculator](src/ya/algorithm_course/second_sprint_recursion/PolskaCalculator.java) | числа в стек, на операции снимаем два |
| Decode String | ≈[394](https://leetcode.com/problems/decode-string/) M | [PackedPrefix](src/ya/algorithm_course/eighth_sprint_strings/final_task/PackedPrefix.java) | **исправлен разбор многозначных множителей**; стек множителей и префиксов |
| Largest Rectangle in Histogram | [84](https://leetcode.com/problems/largest-rectangle-in-histogram/) H | [LargestRectangle](src/leetcode/hard/LargestRectangle.java) | монотонный стек; нерабочий черновик удалён |

## 5. Бинарный поиск

| Задача | LC | Решение | Идея |
|---|---|---|---|
| Search Insert Position | [35](https://leetcode.com/problems/search-insert-position/) E | [SearchInsertPosition](src/leetcode/challenges/SearchInsertPosition.java) | **заменено на эталон**: lower bound на полуинтервале `[l, r)` |
| Search in Rotated Sorted Array | ≈[33](https://leetcode.com/problems/search-in-rotated-sorted-array/) M | [SearchInBrokenArray](src/ya/algorithm_course/third_sprint_sort/final_test/SearchInBrokenArray.java) | одна половина всегда отсортирована |
| Koko Eating Bananas | [875](https://leetcode.com/problems/koko-eating-bananas/) M | [KokoEatingBananas](src/leetcode/challenges/KokoEatingBananas.java) | бинпоиск по ответу |
| Split Array Largest Sum | [410](https://leetcode.com/problems/split-array-largest-sum/) H | [MockInterviewTask](src/ya/algorithm_course/MockInterviewTask.java) | бинпоиск по ответу и жадная проверка |
| Два велосипеда (первая позиция ≥ x) | ≈[34](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) M | [TwoBicycles](src/ya/algorithm_course/third_sprint_sort/TwoBicycles.java) | левый бинпоиск два раза |

## 6. Связные списки и структуры данных

| Задача | LC | Решение | Идея |
|---|---|---|---|
| Merge Two Sorted Lists | [21](https://leetcode.com/problems/merge-two-sorted-lists/) E | [Merge_Two_Sorted_Lists](src/leetcode/challenges/Merge_Two_Sorted_Lists.java) | фиктивная голова (dummy) |
| Remove Duplicates from Sorted List | [83](https://leetcode.com/problems/remove-duplicates-from-sorted-list/) E | [RemoveDuplicates](src/leetcode/challenges/RemoveDuplicates.java) | |
| Reverse Linked List (двусвязный) | ≈[206](https://leetcode.com/problems/reverse-linked-list/) E | [TestTheory](src/ya/algorithm_course/first_sprint_pointers/TestTheory.java) | меняем местами `prev`/`next` у каждого узла |
| Insert GCD in Linked List | [2807](https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/) M | [MediumSolutions#insertGCD](src/leetcode/medium/MediumSolutions.java) | |
| Design Circular Deque | ≈[641](https://leetcode.com/problems/design-circular-deque/) M | [CustomDeque](src/ya/algorithm_course/second_sprint_recursion/CustomDeque.java) | кольцевой буфер, `(i − 1 + n) % n` |
| Design Circular Queue | ≈[622](https://leetcode.com/problems/design-circular-queue/) M | [RestrictedQueue](src/ya/algorithm_course/second_sprint_recursion/RestrictedQueue.java) | |

## 7. Деревья и кучи

| Задача | LC | Решение | Идея |
|---|---|---|---|
| Validate BST | ≈[98](https://leetcode.com/problems/validate-binary-search-tree/) M | [SearchTree](src/ya/algorithm_course/fifth_sprint_trees/SearchTree.java) | передаём вниз границы `(min, max)` |
| Balanced Binary Tree | ≈[110](https://leetcode.com/problems/balanced-binary-tree/) E | [IsBalancedTree](src/ya/algorithm_course/fifth_sprint_trees/IsBalancedTree.java) | высота или −1 за один проход |
| Insert into a BST | ≈[701](https://leetcode.com/problems/insert-into-a-binary-search-tree/) M | [InsertNodeInTree](src/ya/algorithm_course/fifth_sprint_trees/InsertNodeInTree.java) | |
| Delete Node in a BST | ≈[450](https://leetcode.com/problems/delete-node-in-a-bst/) M | [RemoveNode](src/ya/algorithm_course/fifth_sprint_trees/final_task/RemoveNode.java) | 0, 1 или 2 ребёнка; во втором случае берём минимум справа |
| Range Sum / Range in BST | ≈[938](https://leetcode.com/problems/range-sum-of-bst/) E | [PrintTreeRange](src/ya/algorithm_course/fifth_sprint_trees/PrintTreeRange.java) | in-order обход; можно отсекать ветки |
| Unique Binary Search Trees | ≈[96](https://leetcode.com/problems/unique-binary-search-trees/) M | [GenerateUniqueTrees](src/ya/algorithm_course/fifth_sprint_trees/GenerateUniqueTrees.java) | числа Каталана через ДП |
| Heap: sift up / sift down, heap sort | ≈[912](https://leetcode.com/problems/sort-an-array/) M | [SiftHeap](src/ya/algorithm_course/fifth_sprint_trees/SiftHeap.java), [HeapSort](src/ya/algorithm_course/fifth_sprint_trees/final_task/HeapSort.java) | куча на массиве; дети `2i+1`, `2i+2` |

## 8. Графы

| Задача | LC | Решение | Идея |
|---|---|---|---|
| Max Area of Island / Number of Islands | ≈[695](https://leetcode.com/problems/max-area-of-island/) M, [200](https://leetcode.com/problems/number-of-islands/) M | [MaxIslandsDistance](src/ya/algorithm_course/sixth_sprint_graphs/final_task/MaxIslandsDistance.java) | BFS по сетке, отмечаем посещённые при добавлении в очередь |
| Number of Connected Components | ≈[323](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/) M | [ConnectionSeeker](src/ya/algorithm_course/sixth_sprint_graphs/ConnectionSeeker.java) | итеративный DFS со стеком |
| Shortest Path in Binary Matrix | ≈[1091](https://leetcode.com/problems/shortest-path-in-binary-matrix/) M | [MazeEscape](src/ya/algorithm_course/sixth_sprint_graphs/MazeEscape.java) | BFS даёт кратчайший путь в невзвешенном графе |
| Course Schedule II (топосортировка) | ≈[210](https://leetcode.com/problems/course-schedule-ii/) M | [TopologicalSort](src/ya/algorithm_course/sixth_sprint_graphs/TopologicalSort.java) | **убран дублирующий вывод**; DFS и обратный порядок выхода (белый/серый/чёрный) |
| Network Delay Time (Дейкстра) | ≈[743](https://leetcode.com/problems/network-delay-time/) M | [BFSSitesDjiakstra](src/ya/algorithm_course/sixth_sprint_graphs/BFSSitesDjiakstra.java) | PriorityQueue и ленивое удаление |
| Min Cost to Connect All Points (MST, Прим) | ≈[1584](https://leetcode.com/problems/min-cost-to-connect-all-points/) M | [MaxSpanningTree](src/ya/algorithm_course/sixth_sprint_graphs/final_task/MaxSpanningTree.java) | Прим на куче (здесь максимальное остовное дерево) |
| Время входа и выхода в DFS | — | [DFSInOutTime](src/ya/algorithm_course/sixth_sprint_graphs/DFSInOutTime.java) | база для поиска циклов, мостов, SCC |

## 9. Динамическое программирование

| Задача | LC | Решение | Идея |
|---|---|---|---|
| Climbing Stairs | [70](https://leetcode.com/problems/climbing-stairs/) E | [ClimbingStairs](src/leetcode/challenges/ClimbingStairs.java) | `dp[i] = dp[i−1] + dp[i−2]` |
| Ступеньки с прыжком до k | ≈[70](https://leetcode.com/problems/climbing-stairs/) обобщение | [AllaLadders](src/ya/algorithm_course/seventh_sprint_dynamic/AllaLadders.java) | скользящая сумма последних k значений, O(n) |
| Coin Change | [322](https://leetcode.com/problems/coin-change/) M | [CoinChange](src/stepik/algorithms/CoinChange.java) | **исправлено**: ДП и восстановление набора монет; жадность тут не работает |
| Longest Increasing Subsequence (за n log n) | ≈[300](https://leetcode.com/problems/longest-increasing-subsequence/) M | [LongestSubSequence](src/stepik/algorithms/LongestSubSequence.java) | бинпоиск по «хвостам» и восстановление индексов |
| Largest Divisible Subset | ≈[368](https://leetcode.com/problems/largest-divisible-subset/) M | [LargestDividerSubSelect](src/stepik/algorithms/LargestDividerSubSelect.java) | LIS-подобное ДП за O(n²) |
| Partition Equal Subset Sum | ≈[416](https://leetcode.com/problems/partition-equal-subset-sum/) M | [SameSums](src/ya/algorithm_course/seventh_sprint_dynamic/final_task/SameSums.java) | рюкзак на boolean, обход с конца |
| Рюкзак 0/1 (максимальный вес) | ≈[1049](https://leetcode.com/problems/last-stone-weight-ii/) M | [LeprechaunThieves](src/ya/algorithm_course/seventh_sprint_dynamic/LeprechaunThieves.java) | **убран отладочный вывод**; одномерный dp |
| Word Break | ≈[139](https://leetcode.com/problems/word-break/) M | [CheatSheet](src/ya/algorithm_course/eighth_sprint_strings/final_task/CheatSheet.java) | ДП по позициям и бор (Trie) для словаря |
| Edit Distance | ≈[72](https://leetcode.com/problems/edit-distance/) M | [LevenshteinDistance](src/ya/algorithm_course/seventh_sprint_dynamic/final_task/LevenshteinDistance.java) | двумерное ДП на двух строках, O(m) памяти |
| Путь с максимумом на сетке (+ восстановление пути) | ≈[64](https://leetcode.com/problems/minimum-path-sum/) M | [FlowerField](src/ya/algorithm_course/seventh_sprint_dynamic/FlowerField.java), [FlowerFieldWithPrevious](src/ya/algorithm_course/seventh_sprint_dynamic/FlowerFieldWithPrevious.java) | ДП по сетке; путь восстанавливаем обратным ходом |
| Jump Game / Jump Game II | [55](https://leetcode.com/problems/jump-game/) M, [45](https://leetcode.com/problems/jump-game-ii/) M | [MediumSolutions#canJumpNoDp / jump](src/leetcode/medium/MediumSolutions.java) | жадно держим самую дальнюю достижимую позицию |
| Best Time to Buy and Sell Stock I / II | [121](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) E, ≈[122](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/) M | [EasySolutions#maxProfitNoDp](src/leetcode/easy/EasySolutions.java), [Actions](src/ya/algorithm_course/seventh_sprint_dynamic/Actions.java) | минимум слева / сумма всех подъёмов |

## 10. Жадные алгоритмы и интервалы

| Задача | LC | Решение | Идея |
|---|---|---|---|
| Merge Intervals | ≈[56](https://leetcode.com/problems/merge-intervals/) M | [IntervalsGarden](src/ya/algorithm_course/third_sprint_sort/IntervalsGarden.java) | **заменено на эталон**: сортировка по началу, расширяем последний отрезок |
| Non-overlapping Intervals / максимум занятий | ≈[435](https://leetcode.com/problems/non-overlapping-intervals/) M | [Schedule](src/ya/algorithm_course/seventh_sprint_dynamic/Schedule.java) | сортировка по концу, берём самый ранний конец |
| Minimum Number of Arrows (покрыть отрезки точками) | ≈[452](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/) M | [CoverDotsByLines](src/stepik/algorithms/CoverDotsByLines.java) | точка в конце первого непокрытого отрезка (ввод захардкожен) |
| Gas Station | [134](https://leetcode.com/problems/gas-station/) M | [MediumSolutions#canCompleteCircuit](src/leetcode/medium/MediumSolutions.java) | при отрицательном балансе начинаем со следующей станции |
| Candy | [135](https://leetcode.com/problems/candy/) H | [HardSolutions#candy](src/leetcode/hard/HardSolutions.java) | два прохода: слева направо и справа налево |
| Непрерывный рюкзак | — | [GoldenRage](src/ya/algorithm_course/seventh_sprint_dynamic/GoldenRage.java) | сортировка по удельной цене |

## 11. Перебор с возвратом (backtracking)

| Задача | LC | Решение | Идея |
|---|---|---|---|
| Generate Parentheses | ≈[22](https://leetcode.com/problems/generate-parentheses/) M | [GenerateParenthesis](src/ya/algorithm_course/third_sprint_sort/GenerateParenthesis.java) | ставим `(` пока `open < n`, `)` пока `close < open` |
| Letter Combinations of a Phone Number | [17](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) M | [EasySolutions#letterCombinations](src/leetcode/easy/EasySolutions.java) | DFS по позициям |

## 12. Сортировки

| Задача | LC | Решение | Идея |
|---|---|---|---|
| Quick Sort (3-way partition) | ≈[912](https://leetcode.com/problems/sort-an-array/) M, [75](https://leetcode.com/problems/sort-colors/) M | [QuickSortAlgorithms](src/stepik/algorithms/QuickSortAlgorithms.java) | **заменено на эталон**: на одинаковых элементах O(n), глубина рекурсии O(log n) |
| In-place quicksort с компаратором | ≈[912](https://leetcode.com/problems/sort-an-array/) M | [EffectiveQuicksort](src/ya/algorithm_course/third_sprint_sort/final_test/EffectiveQuicksort.java) | Ломуто со случайным опорным элементом |
| Merge Sort | ≈[912](https://leetcode.com/problems/sort-an-array/) M | [MakeMergeSort](src/ya/algorithm_course/third_sprint_sort/MakeMergeSort.java) | |
| Sort Colors (counting sort) | ≈[75](https://leetcode.com/problems/sort-colors/) M | [ColorCountingSort](src/ya/algorithm_course/third_sprint_sort/ColorCountingSort.java) | на собесе ждут one-pass «голландский флаг» |
| Largest Number | ≈[179](https://leetcode.com/problems/largest-number/) M | [BigNumber](src/ya/algorithm_course/third_sprint_sort/BigNumber.java) | компаратор `a+b` против `b+a` |

## 13. Строки

| Задача | LC | Решение | Идея |
|---|---|---|---|
| Longest Common Prefix | [14](https://leetcode.com/problems/longest-common-prefix/) E | [LongestCommonPrefix](src/leetcode/challenges/LongestCommonPrefix.java) | после сортировки достаточно сравнить первую и последнюю строку |
| Reverse Words in a String | [151](https://leetcode.com/problems/reverse-words-in-a-string/) M | [MediumSolutions#reverseWords](src/leetcode/medium/MediumSolutions.java) | |
| Zigzag Conversion | [6](https://leetcode.com/problems/zigzag-conversion/) M | [MediumSolutions#convert](src/leetcode/medium/MediumSolutions.java) | |
| Roman ↔ Integer | [13](https://leetcode.com/problems/roman-to-integer/) E, [12](https://leetcode.com/problems/integer-to-roman/) M | [RomanToInteger](src/leetcode/challenges/RomanToInteger.java), [MediumSolutions#intToRoman](src/leetcode/medium/MediumSolutions.java) | |
| One Edit Distance | ≈[161](https://leetcode.com/problems/one-edit-distance/) M | [CompareOneSymbolDifference](src/ya/algorithm_course/eighth_sprint_strings/CompareOneSymbolDifference.java) | **исправлено** сравнение без учёта регистра; при первом расхождении сравниваем хвосты |
| Поиск шаблона со сдвигом | ≈[28](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/) E | [SearchWithMove](src/ya/algorithm_course/eighth_sprint_strings/SearchWithMove.java) | **исправлено** для шаблона длины 1; сравниваем массивы разностей (можно через КМП) |

## 14. Математика

| Задача | LC | Решение | Идея |
|---|---|---|---|
| Palindrome Number | [9](https://leetcode.com/problems/palindrome-number/) E | [Palindrome#isPalindrome](src/leetcode/challenges/Palindrome.java) | разворачиваем только половину числа |
| Plus One | [66](https://leetcode.com/problems/plus-one/) E | [EasySolutions#plusOneShort](src/leetcode/easy/EasySolutions.java) | ошибочный вариант удалён |
| Rotate Array | [189](https://leetcode.com/problems/rotate-array/) M | [MediumSolutions#rotate](src/leetcode/medium/MediumSolutions.java) | три разворота |
| H-Index | [274](https://leetcode.com/problems/h-index/) M | [MediumSolutions#hIndex](src/leetcode/medium/MediumSolutions.java) | |
| Fibonacci по модулю за O(log n) | ≈[509](https://leetcode.com/problems/fibonacci-number/) E | [FibonacciMod](src/ya/algorithm_course/second_sprint_recursion/FibonacciMod.java) | быстрое удвоение (fast doubling) |
| Count Primes (решето) | ≈[204](https://leetcode.com/problems/count-primes/) M | [Primes](src/fox/Primes.java) | вычёркиваем с `d²` |

---

## 15. Многопоточность (Java concurrency)

На собеседованиях в FAANG это отдельный блок, а у LeetCode есть [раздел Concurrency](https://leetcode.com/problemset/concurrency/). Ниже — примеры из [src/stepik/multithreading](src/stepik/multithreading) с исправленными ошибками.

| Тема | Решение | Что показывает | Аналог на LeetCode |
|---|---|---|---|
| Очерёдность потоков (ABCABC…) | [ABCThreeThreadsWriter](src/stepik/multithreading/ABCThreeThreadsWriter.java) | `wait`/`notifyAll` на одном мониторе, ожидание в `while` | [1114](https://leetcode.com/problems/print-in-order/), [1115](https://leetcode.com/problems/print-foobar-alternately/), [1116](https://leetcode.com/problems/print-zero-even-odd/) |
| Bounded Blocking Queue | [BoundedBlockingQueue](src/stepik/multithreading/BoundedBlockingQueue.java) | **новый эталон**: `ReentrantLock` и два `Condition` (notFull/notEmpty) | [1188](https://leetcode.com/problems/design-bounded-blocking-queue/) |
| Producer–consumer на мониторе | [BlockQueue](src/stepik/multithreading/BlockQueue.java) | **исправлено**: при прерывании восстанавливается флаг interrupt | — |
| Deadlock и как его избежать | [DeadLockAccount](src/stepik/multithreading/DeadLockAccount.java) | **добавлен `transferSafely`**: единый порядок захвата блокировок | [1226 Dining Philosophers](https://leetcode.com/problems/the-dining-philosophers/) |
| Видимость, `volatile` | [MultiThreadingGuessNumber](src/stepik/multithreading/MultiThreadingGuessNumber.java) | **исправлено**: без `volatile` флаг мог никогда не стать видимым другому потоку | — |
| Гонки: `synchronized` и Atomic | [Counter](src/stepik/multithreading/Counter.java), [ThreadDaemon](src/stepik/multithreading/ThreadDaemon.java), [AtomicAndVolatile](src/stepik/multithreading/AtomicAndVolatile.java) | разные мониторы для независимых данных, CAS | — |
| Чтение под блокировкой | [CountDownLatchSample](src/stepik/multithreading/CountDownLatchSample.java) + [тест](Test/stepik/multithreading/CountDownLatchSampleTest.java) | **исправлено**: значение возвращалось уже после выхода из `synchronized` | — |
| CyclicBarrier, Semaphore, CountDownLatch | [CarRace](src/stepik/multithreading/CarRace.java) | все три синхронизатора в одной задаче; **исправлено**: `release` без успешного `acquire` | [1117 H2O](https://leetcode.com/problems/building-h2o/) |
| ExecutorService, Callable/Future | [CallableAndFuture](src/stepik/multithreading/CallableAndFuture.java), [ThreeThreadsCountElements](src/stepik/multithreading/ThreeThreadsCountElements.java) | **исправлено**: single-thread executor выполнял задачи последовательно | [1195 Fizz Buzz Multithreaded](https://leetcode.com/problems/fizz-buzz-multithreaded/) |
| CompletableFuture | [CompletableExample](src/stepik/multithreading/CompletableExample.java) | `thenApply`, `thenCombine`, `exceptionally` | [1242 Web Crawler Multithreaded](https://leetcode.com/problems/web-crawler-multithreaded/) |
| Параллельная обработка массива | [Concurrency](src/stepik/multithreading/Concurrency.java) | **исправлено**: вторая половина считалась с неверным индексом, и результат не совпадал с однопоточным | — |

---

## Чего не хватает до уровня FAANG

Этих паттернов в репозитории пока нет или почти нет, а на собеседованиях они встречаются часто. Ниже — минимальный набор задач, чтобы закрыть каждый пробел.

| Паттерн | Задачи |
|---|---|
| Скользящее окно с условием | [76](https://leetcode.com/problems/minimum-window-substring/), [424](https://leetcode.com/problems/longest-repeating-character-replacement/), [239](https://leetcode.com/problems/sliding-window-maximum/) (монотонная очередь) |
| Связные списки | [206](https://leetcode.com/problems/reverse-linked-list/), [141](https://leetcode.com/problems/linked-list-cycle/), [19](https://leetcode.com/problems/remove-nth-node-from-end-of-list/), [146 LRU Cache](https://leetcode.com/problems/lru-cache/), [23](https://leetcode.com/problems/merge-k-sorted-lists/) |
| Деревья (обходы) | [102](https://leetcode.com/problems/binary-tree-level-order-traversal/), [236](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/), [124](https://leetcode.com/problems/binary-tree-maximum-path-sum/), [297](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/), [230](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) |
| Heap / Top-K | [215](https://leetcode.com/problems/kth-largest-element-in-an-array/), [347](https://leetcode.com/problems/top-k-frequent-elements/), [295](https://leetcode.com/problems/find-median-from-data-stream/) |
| Backtracking | [46](https://leetcode.com/problems/permutations/), [78](https://leetcode.com/problems/subsets/), [39](https://leetcode.com/problems/combination-sum/), [79](https://leetcode.com/problems/word-search/), [51](https://leetcode.com/problems/n-queens/) |
| Union-Find | [547](https://leetcode.com/problems/number-of-provinces/), [684](https://leetcode.com/problems/redundant-connection/), [721](https://leetcode.com/problems/accounts-merge/) |
| Trie | [208](https://leetcode.com/problems/implement-trie-prefix-tree/), [212](https://leetcode.com/problems/word-search-ii/) |
| Графы | [207](https://leetcode.com/problems/course-schedule/), [133](https://leetcode.com/problems/clone-graph/), [994](https://leetcode.com/problems/rotting-oranges/) (multi-source BFS), [127](https://leetcode.com/problems/word-ladder/) |
| Интервалы | [57](https://leetcode.com/problems/insert-interval/), [253 Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) |
| ДП | [198](https://leetcode.com/problems/house-robber/), [1143](https://leetcode.com/problems/longest-common-subsequence/), [518](https://leetcode.com/problems/coin-change-ii/), [312](https://leetcode.com/problems/burst-balloons/) (интервальное) |
| Монотонный стек | [739](https://leetcode.com/problems/daily-temperatures/), [496](https://leetcode.com/problems/next-greater-element-i/) |
| Биты | [136](https://leetcode.com/problems/single-number/), [191](https://leetcode.com/problems/number-of-1-bits/), [338](https://leetcode.com/problems/counting-bits/) |
| Бинарный поиск | [153](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/), [1011](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/), [4](https://leetcode.com/problems/median-of-two-sorted-arrays/) |

---

## Не вошло в подборку

- **Решения с неизвестным условием** — исправить их нельзя, пока не найдено условие: [tinkoff/BankRobbery](src/tinkoff/BankRobbery.java) (жадность вместо перебора подмножеств), [tinkoff/Cards](src/tinkoff/Cards.java) (выход за границу массива при `i = 0`), [algo_training/Main](src/ya/algo_training/Main.java).
- **Исправлено, но слишком простое для FAANG:** [tinkoff/Sheriff](src/tinkoff/Sheriff.java) (≈ LC 1189), [MinDistanceForXAndY](src/leetcode/challenges/MinDistanceForXAndY.java) (≈ LC 243), [LampsTree](src/ya/algorithm_course/fifth_sprint_trees/LampsTree.java).
- **Простые задачи на ввод-вывод и разминку:** спринты 1–2 Практикума (матрицы, двоичные числа, очереди без нюансов), лёгкие LC (1431, 2769, 3110, 1929, 58, 28, 344, 118).
- **Не алгоритмы:** `stepik/training`, `stepik/files`, `ya/java_course`, `beans`, `test/assignments`, `yandex_cup`, `ReadWrite`, `ManyFunctionalTool`, `DownloadFilesImitator`.
- **Учебные эксперименты Stepik:** `HanoiTower`, `Huffman`, `KnapsackProblem` (ввод захардкожен), `DifferentSummands`, `RocksGame`, `PolynominalHash` (случайный перебор коллизий).
