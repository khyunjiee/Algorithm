package baekjoon.aug.aug18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b13904 {

    static List<int[]> works;
    static int total, day;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        works = new ArrayList<>();
        int standard = N*2;

        total = 0;
        day = 1;

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            works.add(new int[]{d, w, 0});
        }

        for (int i = 0; i < N; ++i) {
            int d = standard - works.get(i)[0];
            int weight = d * works.get(i)[1];
            works.get(i)[2] = weight;
        }

        Collections.sort(works, (o1, o2) -> {
            if (o2[2] == o1[2]) return o1[0]-o2[0];
            else return o2[2]-o1[2];
        });

        working();
        System.out.println(total);
    }

    private static void working() {
        while (works.size() > 0) {
            printArr();
            List<int[]> remove = new ArrayList<>();

            for (int i = 0; i < works.size(); ++i) {
                if (works.get(i)[0] < day) remove.add(works.get(i));
                else {
                    int score = works.get(i)[1];
                    int[] removeWork = works.get(i);

                    for (int j = i; j < works.size(); ++j) {
                        if (day == works.get(j)[0] && works.get(i)[2]/2 <= works.get(j)[2]) {
                            score = works.get(j)[1];
                            removeWork = works.get(j);
                            break;
                        }
                    }

                    remove.add(removeWork);
                    total += score;
                }
            }

            ++day;
            works.removeAll(remove);
        }
    }

    private static void printArr() {
        for (int i = 0; i < works.size(); i++) {
            System.out.println(Arrays.toString(works.get(i)));
        }
    }
}
