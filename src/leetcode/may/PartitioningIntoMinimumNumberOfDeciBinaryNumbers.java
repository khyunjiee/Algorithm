package leetcode.may;

public class PartitioningIntoMinimumNumberOfDeciBinaryNumbers {
}

class SolutionPartitioningIntoMinimumNumberOfDeciBinaryNumbers {
    public int minPartitions(String n) {
        String[] array = n.split("");
        int max = 0;

        for (String str: array) {
            int num = Integer.parseInt(str);
            if (num > max) max = num;
        }

        return max;
    }
}