// blog: https://velog.io/@eello/LeetCode-155.-Min-Stack
class MinStack {

    private static final int MAX_SIZE = 30000;
    private int[] stack = new int[MAX_SIZE + 1];
    private int[] minStack = new int[MAX_SIZE + 1];
    private int top = -1, minTop = -1;


    public MinStack() {
    }
    
    public void push(int val) {
        stack[++top] = val;
        
        if (minTop == - 1 || minStack[minTop] >= val) {
            minStack[++minTop] = val;
        }
    }
    
    public void pop() {
        if (stack[top] == minStack[minTop]) {
            minTop--;
        }
        top--;
    }
    
    public int top() {
        return stack[top];
    }
    
    public int getMin() {
        return minStack[minTop];
    }
}