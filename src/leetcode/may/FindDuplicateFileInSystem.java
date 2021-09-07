package leetcode.may;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindDuplicateFileInSystem {
    public static void main(String[] args) {
        SolutionFindDuplicateInSystem solution = new SolutionFindDuplicateInSystem();
        printList(solution.findDuplicate(new String[]{"root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"}));
//        printList(solution.findDuplicate(new String[]{"root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)"}));
    }

    public static void printList(List<List<String>> answer) {
        for (List<String> list: answer) {
            System.out.print("[ ");
            for (String string: list) {
                System.out.print(string + " ");
            }
            System.out.println("]");
        }
    }
}

class SolutionFindDuplicateInSystem {
    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String, ArrayList<String>> fileMap = new HashMap<>();
        List<List<String>> answer = new ArrayList<>();

        for (String path: paths) {
            String[] fileList = path.split(" ");
            String pathString = fileList[0];

            for (int i = 1; i < fileList.length; i++) {
                int splitIndex = fileList[i].indexOf("(");
                String content = fileList[i].substring(splitIndex + 1, fileList[i].length() - 1);
                String file = fileList[i].substring(0, splitIndex);

                if (!fileMap.containsKey(content)) {
                    fileMap.put(content, new ArrayList<>());
                }
                fileMap.get(content).add(pathString + "/" + file);
            }
        }

        for (ArrayList<String> list: fileMap.values()) {
            if (list.size() >= 2) answer.add(list);
        }

        return answer;
    }
}
