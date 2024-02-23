import java.util.*;

public class EightPuzzle {
    public static void main(String[] args) {
        EightPuzzle puzzleSolver = new EightPuzzle();
        Scanner scanner = new Scanner(System.in);
        String initialState = "807625341";

        System.out.println("Choose one kind of search: ");
        System.out.println("1.- Breadth First Search");
        System.out.println("2.- Depth First Search");

        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                long startTimeBFS = System.currentTimeMillis();
                puzzleSolver.solvePuzzleBFS(initialState);
                long endTimeBFS = System.currentTimeMillis();
                long elapsedTimeBFS = endTimeBFS - startTimeBFS;
                System.out.println("Breadth First Search Elapsed time: " + elapsedTimeBFS + " milliseconds");
                break;
            case 2:
                long startTimeDFS = System.currentTimeMillis();
                puzzleSolver.solvePuzzleDFS(initialState);
                long endTimeDFS = System.currentTimeMillis();
                long elapsedTimeDFS = endTimeDFS - startTimeDFS;
                System.out.println("Depth First Search Elapsed time: " + elapsedTimeDFS + " milliseconds");
                break;
            default:
                System.out.println("Invalid option");
        }
    }
    public void solvePuzzleBFS(String initialState) {
        Queue<Node> unknownSet = new LinkedList<>();
        Set<String> visitedSet = new HashSet<>();

        Node startNode = new Node(initialState, null);
        unknownSet.add(startNode);

        while (!unknownSet.isEmpty()) {
            Node current = unknownSet.poll();

            if (isGoalState(current.state)) {
                printSolution(current);
                return;
            }
            visitedSet.add(current.state);
            List<String> successors = getSuccessors(current.state);
            for (String successor : successors) {
                if (!visitedSet.contains(successor)) {
                    Node succesorNode = new Node(successor, current);
                    unknownSet.add(succesorNode);
                }
            }
        }

        System.out.println("No solution found");
    }

    public void solvePuzzleDFS(String initialState) {
        Stack<Node> unknownSet = new Stack<>();
        Set<String> visitedSet = new HashSet<>();

        Node startNode = new Node(initialState, null);
        unknownSet.add(startNode);

        while (!unknownSet.isEmpty()) {
            Node current = unknownSet.pop();

            if (isGoalState(current.state)) {
                printSolution(current);
                return;
            }
            visitedSet.add(current.state);
            List<String> successors = getSuccessors(current.state);
            for (String successor : successors) {
                if (!visitedSet.contains(successor)) {
                    Node succesorNode = new Node(successor, current);
                    unknownSet.add(succesorNode);
                }
            }
        }

        System.out.println("No solution found");
    }

    private List<String> getSuccessors(String state) {
        List<String> successors = new ArrayList<>();

        int zeroIndex = state.indexOf("0");

        int[] possibleMoves = {-1, 1, -3, 3};

        for (int move : possibleMoves) {
            int successorIndex = zeroIndex + move;
            if (isValidMove(zeroIndex, successorIndex)) {
                char[] newStateArray = state.toCharArray();
                char temp = newStateArray[zeroIndex];
                newStateArray[zeroIndex] = newStateArray[successorIndex];
                newStateArray[successorIndex] = temp;

                successors.add(new String(newStateArray));
            }
        }

        return successors;
    }

    private boolean isValidMove(int zeroIndex, int successorIndex) {
        return successorIndex >= 0 && successorIndex < 9 &&
                !(zeroIndex % 3 == 0 && successorIndex % 3 == 2) &&
                !(zeroIndex % 3 == 2 && successorIndex % 3 == 0);
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
            System.out.println(format(path.pop()));
        }
    }

    private String format(String state) {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            formatted.append(state.charAt(i));
            if (i % 3 == 2) {
                formatted.append("\n");
            } else {
                formatted.append(" ");
            }
        }
        return formatted.toString();
    }
}

