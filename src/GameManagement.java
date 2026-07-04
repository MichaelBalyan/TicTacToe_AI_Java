import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameManagement {
    private boolean winX = false;
    private boolean winY = false;
    private boolean draw = false;

    private final int[][] winningPositions =
            {
                    {0, 1, 2},
                    {3, 4, 5},
                    {6, 7, 8},
                    {0, 3, 6},
                    {1, 4, 7},
                    {2, 5, 8},
                    {0, 4, 8},
                    {6, 4, 2}
            };

    private int[] board = new int[9];

    public int[] createBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = i;
        }
        return board;
    }

    public void print(int[] board) {
        if(board == null) {
            throw new NullPointerException("board is null");
        }

        int count = 1;
        for (int i = 0; i < board.length; i++) {
            if (board[i] >= 0) {
                System.out.print("." + "\t\t");
            }
            if (board[i] == -1) {
                System.out.print("X" + "\t\t");
            }
            if (board[i] == -2) {
                System.out.print("O" + "\t\t");
            }

            if (count % 3 == 0) {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
            }
            count++;
        }
    }

// Conditions  - start

    // Conditions for exceptions handling - start
    public void checkExIndex(int i){
        if(i < 0 || i > 8){
            throw new IndexOutOfBoundsException("index i is out of bounds");
        }
    }

    public void checkExBoard(int[] board){
        if(board == null || board.length == 0) {
            throw new NullPointerException("board is null");
        }
    }

    public void checkExWinningPos(int[][] winningPositions){
        if(winningPositions == null || winningPositions.length == 0) {
            throw new NullPointerException("winningPositions is null");
        }
    }

    public void checkExInput(int input){
        if(input < 0 || input > 8) {
            throw new IndexOutOfBoundsException("input is out of bounds");
        }
    }

    public void checkExceptionsIndBW(int i, int[] board, int[][] winningPositions){
        checkExIndex(i);
        checkExBoard(board);
        checkExWinningPos(winningPositions);
    }

    public void checkExceptionsBW(int[] board, int[][] winningPositions){
        checkExBoard(board);
        checkExWinningPos(winningPositions);
    }

    public void checkExceptionsBInpW(int[] board, int input, int[][] winningPositions){
        checkExBoard(board);
        checkExInput(input);
        checkExWinningPos(winningPositions);
    }

    // Conditions for exceptions handling - end

    // Conditions For Win - start
    public boolean isTheXWins(int i, int[] board, int[][] winningPositions) {
        checkExceptionsIndBW(i, board, winningPositions);

        boolean winning = false;
        if (board[winningPositions[i][0]] == -1 && board[winningPositions[i][1]] == -1 && board[winningPositions[i][2]] == -1) {
            winning = true;
        }

        return winning;
    }

    public boolean isTheOWins(int i, int[] board, int[][] winningPositions) {
        checkExceptionsIndBW(i, board, winningPositions);

        boolean winning = false;
        if (board[winningPositions[i][0]] == -2 && board[winningPositions[i][1]] == -2 && board[winningPositions[i][2]] == -2) {
            winning = true;
        }

        return winning;
    }
// Conditions For Win - end

    // Conditions For O Logic - start
    public boolean canAttackToTheLastField(int i, int[] board, int[][] winningPositions) {
        checkExceptionsIndBW(i, board, winningPositions);

        boolean can = false;
        if (board[winningPositions[i][0]] == -2 && board[winningPositions[i][1]] == -2 && board[winningPositions[i][2]] >= 0) {
            can = true;
        }

        return can;
    }

    public boolean canAttackToTheFirstField(int i, int[] board, int[][] winningPositions) {
        checkExceptionsIndBW(i, board, winningPositions);

        boolean can = false;
        if (board[winningPositions[i][1]] == -2 && board[winningPositions[i][2]] == -2 && board[winningPositions[i][0]] >= 0) {
            can = true;
        }

        return can;
    }

    public boolean canAttackToTheMiddleField(int i, int[] board, int[][] winningPositions) {
        checkExceptionsIndBW(i, board, winningPositions);

        boolean can = false;
        if (board[winningPositions[i][0]] == -2 && board[winningPositions[i][2]] == -2 && board[winningPositions[i][1]] >= 0) {
            can = true;
        }

        return can;
    }

    public boolean canBeDefendedByTheLastField(int i, int[] board, int[][] winningPositions) {
        checkExceptionsIndBW(i, board, winningPositions);

        boolean can = false;
        if (board[winningPositions[i][0]] == -1 && board[winningPositions[i][1]] == -1 && board[winningPositions[i][2]] >= 0) {
            can = true;
        }

        return can;
    }

    public boolean canBeDefendedByTheFirstField(int i, int[] board, int[][] winningPositions) {
        checkExceptionsIndBW(i, board, winningPositions);

        boolean can = false;
        if (board[winningPositions[i][1]] == -1 && board[winningPositions[i][2]] == -1 && board[winningPositions[i][0]] >= 0) {
            can = true;
        }

        return can;
    }

    public boolean canBeDefendedByTheMiddleField(int i, int[] board, int[][] winningPositions) {
        checkExceptionsIndBW(i, board, winningPositions);

        boolean can = false;
        if (board[winningPositions[i][0]] == -1 && board[winningPositions[i][2]] == -1 && board[winningPositions[i][1]] >= 0) {
            can = true;
        }

        return can;
    }
// Conditions For O Logic - end

// Conditions - end

    public boolean isDraw(int[] board, int[][] winningPositions) {
        checkExceptionsBW(board, winningPositions);

        List<Integer> nullFields = new ArrayList<>();
        boolean willWin = false;
        int nullCount = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] >= 0) {
                nullCount++;
                nullFields.add(i);
            }
        }

        if (nullCount == 0 || nullCount > 2) {
            willWin = true;
        }

        if (!willWin) {
            if (nullCount == 2) {
                board[nullFields.get(0)] = -1;
                board[nullFields.get(1)] = -2;

                for (int i = 0; i < 8; i++) {
                    if (isTheXWins(i, board, winningPositions)) {
                        willWin = true;
                        break;
                    } else if (isTheOWins(i, board, winningPositions)) {
                        willWin = true;
                        break;
                    }
                }

                if (!willWin) {
                    board[nullFields.get(0)] = -2;
                    board[nullFields.get(1)] = -1;

                    for (int i = 0; i < 8; i++) {
                        if (isTheXWins(i, board, winningPositions)) {
                            willWin = true;
                            break;
                        } else if (isTheOWins(i, board, winningPositions)) {
                            willWin = true;
                            break;
                        }
                    }
                }

                board[nullFields.get(0)] = nullFields.get(0);
                board[nullFields.get(1)] = nullFields.get(1);
            }
        }

        if (!willWin) {
            if (nullCount == 1) {
                board[nullFields.get(0)] = -1;

                for (int i = 0; i < 8; i++) {
                    if (isTheXWins(i, board, winningPositions)) {
                        willWin = true;
                        break;
                    }
                }

                board[nullFields.get(0)] = nullFields.get(0);
            }
        }

        if (willWin) {
            draw = false;
        } else {
            draw = true;
        }

        return draw;
    }

    public int[] turnX(int[] board, int input, int[][] winningPositions) {
        checkExceptionsBInpW(board, input, winningPositions);

        board[input] = -1;

        for (int i = 0; i < 8; i++) {
            if (isTheXWins(i, board, winningPositions)) {
                winX = true;
                break;
            }
        }

        return board;
    }

    public int[] turnO(int[] board, int[][] winningPositions) {
        checkExceptionsBW(board, winningPositions);

        // O logic - start
        if (!winX) {
            boolean isPlayed = false;
            boolean attack = false;
            boolean defend = false;
            for (int i = 0; i < 8; i++) {
                if (canAttackToTheLastField(i, board, winningPositions)) {
                    board[winningPositions[i][2]] = -2;
                    winY = true;
                    attack = true;
                    isPlayed = true;
                    break;
                }
                if (canAttackToTheFirstField(i, board, winningPositions)) {
                    board[winningPositions[i][0]] = -2;
                    winY = true;
                    attack = true;
                    isPlayed = true;
                    break;
                }
                if (canAttackToTheMiddleField(i, board, winningPositions)) {
                    board[winningPositions[i][1]] = -2;
                    winY = true;
                    attack = true;
                    isPlayed = true;
                    break;
                }
            }

            if (!attack) {
                if (!defend) {
                    for (int i = 0; i < 8; i++) {
                        if (canBeDefendedByTheLastField(i, board, winningPositions)) {
                            board[winningPositions[i][2]] = -2;
                            isPlayed = true;
                            defend = true;
                            break;
                        }
                        if (canBeDefendedByTheFirstField(i, board, winningPositions)) {
                            board[winningPositions[i][0]] = -2;
                            isPlayed = true;
                            defend = true;
                            break;
                        }
                        if (canBeDefendedByTheMiddleField(i, board, winningPositions)) {
                            board[winningPositions[i][1]] = -2;
                            isPlayed = true;
                            defend = true;
                            break;
                        }
                    }
                }

                if (!defend) {
                    if (board[4] == -1 && board[8] == 8) {
                        board[8] = -2;
                        isPlayed = true;
                    } else if (board[4] > 0) {
                        board[4] = -2;
                        isPlayed = true;
                    }
                }
            }

            if (!attack && !defend && !isPlayed && ((board[0] == -1 && board[5] == -1) || (board[1] == -1 && board[8] == -1))) {
                if (board[2] == 2) {
                    board[2] = -2;
                } else if (board[1] == 1) {
                    board[1] = -2;
                }
                isPlayed = true;
            }

            if (!attack && !defend && !isPlayed && (board[5] == -1 && board[6] == -1)) {
                if (board[8] == 8) {
                    board[8] = -2;
                } else if (board[7] == 7) {
                    board[7] = -2;
                }
                isPlayed = true;
            }

            if (!attack && !defend && !isPlayed) {
                if ((board[0] == -1 && board[8] == -1) || (board[2] == -1 && board[6] == -1)) {
                    board[7] = -2;
                    defend = true;
                    isPlayed = true;
                }
            }

            if (!attack && !defend && !isPlayed && board[6] == 6) {
                if (board[1] == -1 && board[5] == -1) {
                    board[2] = -2;
                } else {
                    board[6] = -2;
                }
                isPlayed = true;
            }


            for (int i = 0; i < board.length; i++) {
                if (!attack && !defend && !isPlayed && board[i] >= 0) {
                    board[i] = -2;
                    isPlayed = true;
                    break;
                }
            }
        }

        // O logic - end

        return board;
    }

    public void printHeaderAndBoard(){
        System.out.println("=====================");
        System.out.println("=== X TicTacToe O ===");
        System.out.println("=====================");

        System.out.println();

        System.out.println("This is your positions in numbers.");
        System.out.println();
        System.out.println("0 | 1 | 2");
        System.out.println("--|---|---");
        System.out.println("3 | 4 | 5");
        System.out.println("--|---|---");
        System.out.println("6 | 7 | 8");

        System.out.println();

        System.out.println("This is the board.");
        System.out.println();

        print(board);

        System.out.println("Your turn!");
        System.out.print("> ");
    }


    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        while (!winX && !winY) {
            printHeaderAndBoard();

            try {
                int input = scanner.nextInt();
                System.out.println();
                if (input >= 0 && input < 9 && board[input] >= 0) {
                    board = turnX(board, input, winningPositions);
                    isDraw(board, winningPositions);
                    if (!draw) {
                        board = turnO(board,winningPositions);
                        if (winY) {
                            print(board);
                            break;
                        }
                    }
                    isDraw(board, winningPositions);
                    print(board);

                    if (draw) {
                        break;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                scanner.next();
                return;
            }

        }
    }

    public void checkWinner() {
        if (winX && !winY && !draw) {
            System.out.print("Great! You have WON the match!");
            System.out.println();
        } else if (!winX && winY && !draw) {
            System.out.print("Oo... You have lose the game.");
            System.out.println();
        }

        if (!winX && !winY && draw) {
            System.out.print("Draw!");
        }
        System.out.println();
    }

    public void play(){
        createBoard();
        startGame();
        checkWinner();
    }
}


