package stepik.training;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StepikTwoStarLists {

    public static int[][] createTriangle(int height) {
        int size = height + height - 1;
        int half = size / 2;
        int j = 0;
        int[][] res = new int[height][size];
        for (int i = 0; i < res.length; i++) {
            res[i][half + j] = 1;
            res[i][half - j] = 1;
            if (i + 1 < res.length) {
                res[i + 1] = Arrays.copyOf(res[i], res[i].length);
            }
            j++;
        }
        return res;
    }
}
