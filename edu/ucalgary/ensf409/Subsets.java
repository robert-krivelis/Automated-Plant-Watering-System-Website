package edu.ucalgary.ensf409;

import static java.util.stream.IntStream.range;

import java.util.*;
import java.util.LinkedList;
// generator for all possible combinations 
// maybe we use simulated annealing to get to a decent spot but not algorithmically gauranteed?
public class Subsets implements Iterator<List<Integer>> {

    private final int level;
    private final LinkedList<List<Integer>> queue = new LinkedList<>();


    public Subsets(int level) {
        this.level = level;
        range(0, level).forEach(i -> queue.add(Arrays.asList(i)));
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public List<Integer> next() {
        List<Integer> list = queue.removeFirst();
        int maxValue = list.get(list.size() - 1);

        if(list.size() < level) {

            for (int k = maxValue+1; k < level; k++) {
                List<Integer> newList = new ArrayList<>(list);
                newList.add(k);
                queue.addFirst(newList);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Subsets s4 = new Subsets(4);
        while (s4.hasNext()) {
            System.err.println(s4.next());

        }
    }
}