/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package taskmanager;

import java.io.*;

/**
 *
 * @author ahmed maher
 */
public class TaskManager {

    public static void bash(String command) throws IOException {
        Runtime.getRuntime().exec(new String[]{"bash", "-c", command});
    }

    public static String bashTerminal(String command) throws IOException {
        String line;
        Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", command});
        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder data = new StringBuilder();

        while ((line = buf.readLine()) != null) {
            data.append(line).append(System.lineSeparator());
        }

        return data.toString().replaceAll("(\\r\\n|[\\n\\x0B\\x0C\\r\\u0085\\u2028\\u2029])$", "");
    }

    public static String javaTerminal(String command) throws IOException {
        String line;
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder data = new StringBuilder();

        while ((line = buf.readLine()) != null) {

            data.append(line).append(System.lineSeparator());
        }
        return data.toString().replaceAll("(\\r\\n|[\\n\\x0B\\x0C\\r\\u0085\\u2028\\u2029])$", "");
    }

    public static String[] strFormatter(String str) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                string.append(str.charAt(i));
            } else if (str.charAt(i) == ' ' && i != 0) {
                if (str.charAt(i - 1) != ' ' && str.charAt(i - 1) != '\n') {
                    string.append(str.charAt(i));
                }
            } else {

            }
        }

        return string.toString().split("\n");
    }

    public static boolean packageExists(String packageName) throws IOException {
        String[] lines = TaskManager.strFormatter(TaskManager.bashTerminal("dpkg-query -l"));

        for (int i = 5; i < lines.length; i++) {
            if (lines[i].split(" ")[1].equals(packageName)) {
                return true;
            }
        }

        return false;
    }

    public static String[] splice(String[] array, int index) {
        String new_array = "";

        for (int i = 0; i < array.length; i++) {
            if (i == index) {
                continue;
            }

            new_array += array[i] + "\t";
        }

        return new_array.trim().split("\t");
    }

    public static void main(String[] args) throws IOException {
        TaskManagerGUI t = new TaskManagerGUI();
        t.show();
    }

}
