package com.org;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);
        Solution sl = new Solution();
        sl.reverseStack(stack);
        System.out.println(stack);
    }
}


class Solution {
    public void reverseStack(Stack<Integer> st) {
        if (st.isEmpty()) return;
        int temp = st.pop();
        reverseStack(st);
        insertAtStart(st, temp);
    }

    private void insertAtStart(Stack<Integer> st, int num) {
        if (st.isEmpty()) {
            st.push(num);
            return;
        }
        int temp = st.pop();
        insertAtStart(st, num);
        st.push(temp);
    }
}
