package main;

import helpers.Queue;

public class Qtest {
    public static void main(String[] args) {
        Queue q = new Queue();
        q.push("1");
        q.push("2");
        q.push("3");
        q.push("4");
        while (q.peek() != null) {
            System.out.println(q.pop());
        }
    }

}
