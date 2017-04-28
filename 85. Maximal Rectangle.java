public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int[][] matrixCopy = new int[matrix.length][matrix[0].length];
        for (int j = 0; j < matrix[0].length; j++)
            matrixCopy[0][j] = matrix[0][j] - '0';
        for (int i = 1; i < matrixCopy.length; i++){
            for (int j = 0; j < matrixCopy[0].length; j++){
                matrixCopy[i][j] = matrix[i][j] - '0';
                if (matrixCopy[i-1][j]!=0 && matrixCopy[i][j]==1) matrixCopy[i][j]+=matrixCopy[i-1][j];
            }
        }
        int max = largestRectangleArea(matrixCopy[0]);
        for (int i = 1; i < matrixCopy.length; i++){
            max = max > largestRectangleArea(matrixCopy[i])? max : largestRectangleArea(matrixCopy[i]);
        }
        return max;
    }
    
    private int largestRectangleArea(int[] heights) {
        int max = 0;
        Stack<Integer> stack = new Stack<Integer>();
        if (heights.length == 0) return 0;
        int[] newHeights = new int[heights.length+1];
        newHeights[0] = 0;
        for (int i = 0; i < heights.length; i++)
            newHeights[i+1] = heights[i];
        heights = newHeights;
        for (int i = 0; i < heights.length; i++){
            if (stack.empty()) stack.push(i);
            else if (heights[i] >= heights[stack.peek()]) stack.push(i);
            else{
                while(!stack.empty() && heights[i] < heights[stack.peek()]){
                    int curIndex = stack.pop();
                    max = max > (i-stack.peek()-1)*heights[curIndex]? max : (i-stack.peek()-1)*heights[curIndex];
                }
                stack.push(i);
            }
        }
        if (!stack.empty()){
            int maxIndex = stack.peek();
            int preIndex = stack.pop();
            if (!stack.empty()){
                while (!stack.empty()){
                    int curIndex = preIndex;
                    preIndex = stack.pop();
                    max = max > (maxIndex-preIndex)*heights[curIndex]? max : (maxIndex-preIndex)*heights[curIndex];
                }
                max = max > (maxIndex-preIndex+1)*heights[preIndex] ? max : (maxIndex-preIndex+1)*heights[preIndex];
            }
            else
                max = max > heights[maxIndex] ? max : heights[maxIndex];
        }
        return max;
    }
}