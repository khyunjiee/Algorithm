package leetcode.june;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumUnitsOnATruck {
    public static void main(String[] args) {
        SolutionMaximumUnitsOnATruck solution = new SolutionMaximumUnitsOnATruck();
//        System.out.println(solution.maximumUnits(new int[][]{{1,3},{2,2},{3,1}}, 4));
//        System.out.println(solution.maximumUnits(new int[][]{{5,10},{2,5},{4,7},{3,9}}, 10));
//        System.out.println(solution.maximumUnits(new int[][]{{1,3},{5,5},{2,5},{4,2},{4,1},{3,1},{2,2},{1,3},{2,5},{3,2}}, 10));
    }
}

class SolutionMaximumUnitsOnATruck {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int result = 0;
        int index = 0;

        Arrays.sort(boxTypes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o2[0] - o1[0];
                } else {
                    return o2[1] - o1[1];
                }
            }
        });

        while (index < boxTypes.length) {
            if (truckSize - boxTypes[index][0] < 0) {
                result += boxTypes[index][1] * truckSize;
                break;
            } else {
                result += boxTypes[index][1] * boxTypes[index][0];
                truckSize -= boxTypes[index][0];
                index++;
            }
        }

        return result;
    }
}
