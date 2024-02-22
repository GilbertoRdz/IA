import java.util.*;

public class EightPuzzle {
    public static void main(String[] args) {
        EightPuzzle puzzleSolver = new EightPuzzle();
        String initialState = "807625341";
        puzzleSolver.solvePuzzleBFS(initialState);
    }

    public void solvePuzzleBFS(String initialState) {
        Queue<Node> openSet = new LinkedList<>();
        Set<String> closedSet = new HashSet<>();

        Node startNode = new Node(initialState, 0, null);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (isGoalState(current.state)) {
                printSolution(current);
                return;
            }
            closedSet.add(current.state);
            List<String> successors = getSuccessors(current.state);
            for (String successor : successors) {
                if (!closedSet.contains(successor)) {
                    Node succesorNode = new Node(successor, 0, current);
                    openSet.add(succesorNode);
                }
            }
        }

        System.out.println("No solution found");
    }

    private List<String> getSuccessors(String state) {
        List<String> successors = new ArrayList<>();

        int spaceIndex = state.indexOf("0");

        int[] possibleMoves = {-1, 1, -3, 3};

        for (int move : possibleMoves) {
            int successorIndex = spaceIndex + move;
            if (isValidMove(spaceIndex, successorIndex)) {
                char[] newStateArray = state.toCharArray();
                char temp = newStateArray[spaceIndex];
                newStateArray[spaceIndex] = newStateArray[successorIndex];
                newStateArray[successorIndex] = temp;

                successors.add(new String(newStateArray));
            }
        }

        return successors;
    }

    private boolean isValidMove(int spaceIndex, int neighborIndex) {
        return neighborIndex >= 0 && neighborIndex < 9 &&
                !(spaceIndex % 3 == 0 && neighborIndex % 3 == 2) &&
                !(spaceIndex % 3 == 2 && neighborIndex % 3 == 0);
    }

    private boolean isGoalState(String state) {
        return state.equals("123456780");
    }

    private void printSolution(Node goalNode) {
        Stack<String> path = new Stack<>();
        Node current = goalNode;

        while (current != null) {
            path.push(current.state);
            current = current.parent;
        }

        while (!path.isEmpty()) {
            System.out.println(path.pop());
        }
    }
}

