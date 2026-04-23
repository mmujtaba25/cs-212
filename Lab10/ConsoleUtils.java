package Lab10;

import javax.swing.*;

public class ConsoleUtils
{
    private static boolean modernMode = true;

    public static void setModernMode(boolean modernMode) { ConsoleUtils.modernMode = modernMode; }

    /* SYMBOLS */

    private static String h() { return modernMode ? "─" : "-"; }

    private static String v() { return modernMode ? "│" : "|"; }

    private static String tl() { return modernMode ? "┌" : "+"; }

    private static String tr() { return modernMode ? "┐" : "+"; }

    private static String bl() { return modernMode ? "└" : "+"; }

    private static String br() { return modernMode ? "┘" : "+"; }

    private static String ml() { return modernMode ? "├" : "+"; }

    private static String mr() { return modernMode ? "┤" : "+"; }

    /* BOX */

    public static void printTopBorder(int width) { println(tl() + repeat(h(), width) + tr()); }

    public static void printMiddleBorder(int width) { println(ml() + repeat(h(), width) + mr()); }

    public static void printBottomBorder(int width) { println(bl() + repeat(h(), width) + br()); }

    public static void printCenteredLine(String text, int width) { println(v() + centerText(text, width) + v()); }

    public static void printLeftLine(String text, int width)
    {
        println(v() + " " + padRight(text, width - 2) + " " + v());
    }

    /* COMPONENTS */

    public static void printBox(String title, String[] lines, int width)
    {
        printTopBorder(width);

        if (title != null && !title.isEmpty())
        {
            printCenteredLine(title, width);
            printMiddleBorder(width);
        }

        for (String line : lines)
        {
            printLeftLine(line, width);
        }

        printBottomBorder(width);
    }

    /* GENERAL */

    public static String repeat(String s, int count) { return s.repeat(Math.max(0, count)); }

    public static String centerText(String text, int width)
    {
        if (text.length() >= width) return text;

        int padding = width - text.length();
        int left = padding / 2;
        int right = padding - left;

        return " ".repeat(left) + text + " ".repeat(right);
    }

    public static String padRight(String text, int width)
    {
        if (text.length() >= width) return text;
        return text + " ".repeat(width - text.length());
    }

    /* INTERNAL */

    private static void println(String s) { System.out.println(s); }

    public static void oneLineSpace() { System.out.println(); }
}