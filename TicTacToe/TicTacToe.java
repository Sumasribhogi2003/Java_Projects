package game;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class TicTacToe {

    private static class Player {
        char sign;
        int move = 0;
        int moves[];

        public Player(char s, int size) {
            sign = s;
            moves = new int[(size * size) + 2];
        }
    }

    public static class InvalidMoveException extends Exception {
        public InvalidMoveException(int boardSize) {
            super("Invalid move! Please enter coordinates within the range 0 to " + (boardSize - 1));
        }
    }

    static char[][] generateBoard(int size) {
        char board[][] = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '.';
            }
        }
        return board;
    }

    static void printBoard(char board[][], int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

    static void move(Player p, char board[][], int x, int y) throws InvalidMoveException {
        if (x < 0 || y < 0 || x >= board.length || y >= board.length || board[y][x] != '.') {
            throw new InvalidMoveException(board.length);
        }
        p.moves[2 * p.move] = x;
        p.moves[2 * p.move + 1] = y;
        p.move += 1;
        board[y][x] = p.sign;
    }

    static void winner(Player p) {
        System.out.println("****************");
        System.out.println("*    " + p.sign + " won!    *");
        System.out.println("****************");
    }

    static void draw() {
        System.out.println("****************");
        System.out.println("*  match draw! *");
        System.out.println("****************");
    }

    static void checkWinner(char board[][], Player p1, Player p2) {
        Player p;

        if (p1.move > p2.move)
            p = p1;
        else
            p = p2;

        // Check row and columns
        Map<Integer, Integer> X = new HashMap<Integer, Integer>();
        Map<Integer, Integer> Y = new HashMap<Integer, Integer>();

        for (int i = 0, j = 1; j < (2 * p.move); i += 2, j += 2) {
            if (X.containsKey(p.moves[i]) || Y.containsKey(p.moves[j])) {
                if (X.containsKey(p.moves[i])) {
                    if (X.get(p.moves[i]) + 1 == board.length) {
                        winner(p);
                        System.exit(0);
                    }
                    X.put(p.moves[i], X.get(p.moves[i]) + 1);
                }
                if (Y.containsKey(p.moves[j])) {
                    if (Y.get(p.moves[j]) + 1 == board.length) {
                        winner(p);
                        System.exit(0);
                    }
                    Y.put(p.moves[j], Y.get(p.moves[j]) + 1);
                }
            } else {
                X.put(p.moves[i], 1);
                Y.put(p.moves[j], 1);
            }
        }

        // Check diagonals
        boolean d1 = true, d2 = true;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (!d1 && !d2)
                    break;
                if (i == j && d1) {
                    if (!(board[i][j] == p.sign))
                        d1 = false;
                }
                if (i + j == (board.length - 1) && d2) {
                    if (!(board[i][j] == p.sign))
                        d2 = false;
                }
            }
            if (!d1 && !d2)
                break;
        }
        if (d1 || d2) {
            winner(p);
            System.exit(0);
        }
    }

    static void startGame(char board[][], Player p1, Player p2, int size) {
        System.out.println("==============================");
        System.out.println("| enter x and y co-ordinates |");
        System.out.println("| of your move. [ex- 0 0] |");
        System.out.println("==============================");

        int n = 1;
        int numberOfMoves = size * size;
        Scanner sc = new Scanner(System.in);

        while (numberOfMoves > 0) {
            try {
                System.out.print("\nPlayer " + n + " move--> ");
                int x = sc.nextInt();
                int y = sc.nextInt();

                if (n == 1) {
                    move(p1, board, x, y);
                    n++;
                } else {
                    move(p2, board, x, y);
                    n--;
                }
                numberOfMoves--;
            } catch (InvalidMoveException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Wrong input! Please enter integers.");
                sc.next();
            }
            printBoard(board, size);
            checkWinner(board, p1, p2);
        }
        draw();
        sc.close();
    }

    public static void main(String[] args) {
        int size = 3;
        char board[][] = generateBoard(size);
        Player p1 = new Player('x', size);
        Player p2 = new Player('o', size);
        startGame(board, p1, p2, size);
    }
}
