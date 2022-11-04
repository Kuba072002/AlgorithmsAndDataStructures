package pl.edu.pw.ee;

import java.util.ArrayList;

class LongestCommonSubsequence {
    private String firstStr;
    private String secondStr;
    private int l1;
    private int l2;
    private Cell[][] grid;
    private ArrayList<Pair> arr;

    public LongestCommonSubsequence(String firstStr, String secondStr) {
        if (firstStr == null || secondStr == null)
            throw new IllegalArgumentException("Argument cannot be null");
        if (firstStr.length() == 0 || secondStr.length() == 0)
            throw new IllegalArgumentException("Illegal argument");

        this.firstStr = firstStr;
        this.secondStr = secondStr;
        l1 = firstStr.length();
        l2 = secondStr.length();
        grid = new Cell[l1][l2];
    }

    public String findLCS() {
        completeGrid();

        String result = "";
        int i = l1 - 1;
        int j = l2 - 1;
        Cell tmp = grid[i][j];
        arr = new ArrayList<>();
        while (i >= 0 && j >= 0) {
            arr.add(new Pair(i, j));
            if (tmp.getDirection() == '\\') {
                result += firstStr.charAt(i);
                tmp = getCell(--i, --j);
            } else if (tmp.getDirection() == '<')
                tmp = getCell(i, --j);
            else if (tmp.getDirection() == '^')
                tmp = getCell(--i, j);
        }

        return new StringBuilder(result).reverse().toString();
    }

    public void display() {
        if (l1 > 42 || l2 > 42)
            throw new IllegalStateException("Grid is too big");
        if (arr == null)
            throw new IllegalStateException("You have to execute findLCS method");
        String starter = "|     |     |";
        printLine();
        printVerticalLine();
        System.out.print(starter);
        for (int i = 0; i < l2; i++)
            System.out.print(" " + checkChar(secondStr.charAt(i)) + "  |");
        System.out.print('\n');
        printLine();

        printVerticalLine();
        System.out.print("|     |");
        for (int i = 0; i < l2 + 1; i++)
            System.out.print("  0  |");
        System.out.print('\n');
        printLine();

        for (int i = 0; i < l1; i++) {
            System.out.print(starter);
            for (int j = 0; j < l2; j++) {
                if (arr.contains(new Pair(i, j))) {
                    if (grid[i][j].getDirection() == '\\')
                        System.out.print(" \\   |");
                    else if (grid[i][j].getDirection() == '^')
                        System.out.print("  ^  |");
                    else
                        System.out.print("     |");
                } else
                    System.out.print("     |");
            }
            System.out.print('\n');
            System.out.print("| " + checkChar(firstStr.charAt(i)) + "  |  0  |");
            for (int j = 0; j < l2; j++) {
                if (arr.contains(new Pair(i, j))) {
                    if (grid[i][j].getDirection() == '<')
                        System.out.print(" <" + grid[i][j].getValue() + "  |");
                    else
                        System.out.print("  " + grid[i][j].getValue() + "  |");
                } else
                    System.out.print("  " + grid[i][j].getValue() + "  |");
            }
            System.out.print('\n');
            printLine();
        }
    }

    private void printVerticalLine() {
        String line2 = "     |";
        System.out.print("|");
        for (int i = 0; i < l2 + 2; i++)
            System.out.print(line2);
        System.out.print('\n');
    }

    private void printLine() {
        String line = "- - - ";
        System.out.print(" ");
        for (int i = 0; i < l2 + 2; i++)
            System.out.print(line);
        System.out.print('\n');
    }

    private String checkChar(char c) {
        char[] specialChar = { '\"', '\t', '\b', '\r', '\f', '\n', '\'' };
        char[] charToExchange = { '"', 't', 'b', 'r', 'f', 'n' };

        boolean isSpecial = false;
        int k = 0;
        for (int j = 0; j < specialChar.length; j++)
            if (c == specialChar[j]) {
                isSpecial = true;
                k = j;
            }
        if (isSpecial)
            if (k == specialChar.length - 1)
                return "\\'";
            else
                return "\\" + charToExchange[k];

        return " " + c;
    }

    private void completeGrid() {
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                grid[i][j] = new Cell();
                if (firstStr.charAt(i) == secondStr.charAt(j)) {
                    grid[i][j].setValue((byte) (getValue(i - 1, j - 1) + 1));
                    grid[i][j].setDirection('\\');
                } else {
                    byte val1 = getValue(i - 1, j);
                    byte val2 = getValue(i, j - 1);
                    if (val2 > val1) {
                        grid[i][j].setValue(val2);
                        grid[i][j].setDirection('<');
                    } else {
                        grid[i][j].setValue(val1);
                        grid[i][j].setDirection('^');
                    }
                }
            }
        }
    }

    private byte getValue(int i, int j) {
        if (i < 0 || j < 0)
            return 0;
        else
            return grid[i][j].getValue();
    }

    private Cell getCell(int i, int j) {
        if (i < 0 || j < 0)
            return null;
        else
            return grid[i][j];
    }

    private class Cell {
        private byte value;
        private char direction;

        public void setValue(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }

        public void setDirection(char direction) {
            this.direction = direction;
        }

        public char getDirection() {
            return direction;
        }

    }

    private class Pair {
        int row;
        int column;

        public Pair(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        @Override
        public boolean equals(Object obj) {
            boolean result = false;
            if (obj instanceof Pair) {
                Pair p = (Pair) obj;
                if (row == p.getRow() && column == p.getColumn())
                    result = true;
            }
            return result;
        }
    }
}
