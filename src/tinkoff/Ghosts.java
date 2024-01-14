package tinkoff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Ghosts {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int numberOfGhosts = Integer.parseInt(line[0]);
        int numberOfQuestions = Integer.parseInt(line[1]);

        Map<Integer, Ghost> bands = new HashMap<>();
        for (int i = 1; i <= numberOfGhosts; i++) {
            bands.put(i, new Ghost(i));
        }

        for (int i = 1; i <= numberOfQuestions; i++) {
            line = reader.readLine().split(" ");
            int type = Integer.parseInt(line[0]);
            int x = Integer.parseInt(line[1]);
            if (type == 1) {
                int y = Integer.parseInt(line[2]);
                union(bands, x, y);
            } else if (type == 2) {
                int y = Integer.parseInt(line[2]);
                if (find(bands, x) == find(bands, y)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            } else {
                System.out.println(bands.get(x).gangs.size());
            }
        }
        reader.close();
    }

    private static void union(Map<Integer, Ghost> ghosts, int x, int y) {
        Ghost xRoot = find(ghosts, x);
        Ghost yRoot = find(ghosts, y);
        if (xRoot == yRoot) {
            return;
        }

        xRoot.gangs.addAll(yRoot.gangs);

        for (Ghost ghost : yRoot.gangs) {
            ghosts.put(ghost.id, xRoot);
        }
    }

    private static Ghost find(Map<Integer, Ghost> ghosts, int x) {
        Ghost ghost = ghosts.get(x);
        if (ghost.parent == null) {
            return ghost;
        }
        Ghost root = find(ghosts, ghost.parent.id);
        ghost.parent = root;
        return root;
    }

    private static class Ghost {
        private final int id;
        private final Set<Ghost> gangs;
        private Ghost parent;

        public Ghost(int id) {
            this.id = id;
            gangs = new HashSet<>();
            gangs.add(this);
        }
    }
}
