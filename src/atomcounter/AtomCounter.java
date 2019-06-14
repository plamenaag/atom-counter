package atomcounter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class AtomCounter {

    private static List<Atom> finalList = new ArrayList<>();
    private static Stack<Object> stack = new Stack<Object>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input Molecule: ");
        String molecule = sc.nextLine();
        System.out.println(countAtoms(molecule));
    }

    public static String countAtoms(String molecule) {
        Atom lastAtom = null;
        for (int i = 0; i < molecule.length(); i++) {

            if (Character.isUpperCase(molecule.charAt(i))) {
                lastAtom = new Atom(molecule.charAt(i));
                if (stack.empty()) {
                    finalList.add(lastAtom);
                } else {
                    stack.push(lastAtom);
                }
            } else if (Character.isLowerCase(molecule.charAt(i))) {
                lastAtom.addChar(molecule.charAt(i));
            } else if (Character.isDigit(molecule.charAt(i))) {
                String number = "" + molecule.charAt(i);
                while (i + 1 < molecule.length() && Character.isDigit(molecule.charAt(i+1))) {
                    i++;
                    number += molecule.charAt(i);
                }
                lastAtom.setCount(Integer.parseInt(number));
                lastAtom = null;
            } else if ((int) molecule.charAt(i) == 40 || (int) molecule.charAt(i) == 91
                    || (int) molecule.charAt(i) == 123) {
                stack.push(molecule.charAt(i));
                lastAtom = null;
            } else {
                lastAtom = null;
                Integer multiplier = 1;
                String number = "";
                while (i + 1 < molecule.length() && Character.isDigit(molecule.charAt(i+1))) {
                    i++;
                    number += molecule.charAt(i);
                }
                if (!number.isEmpty()) {
                    multiplier = Integer.parseInt(number);
                }

                processStack(multiplier);
            }
        }

        Map<String, Integer> map = new TreeMap<>();
        for (Atom atom : finalList) {
            if (map.containsKey(atom.getElementName())) {
                Integer count = map.get(atom.getElementName());
                map.put(atom.getElementName(), atom.getCount() + count);
            } else {
                map.put(atom.getElementName(), atom.getCount());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey() + entry.getValue());
        }

        return sb.toString();
    }

    public static void processStack(Integer multiplier) {
        List<Atom> tempList = new ArrayList<>();
        Boolean repeat = true;
        do {
            Object obj = stack.pop();
            if (obj instanceof Atom) {
                Atom atom = (Atom) obj;
                Integer count = atom.getCount() * multiplier;
                atom.setCount(count);
                tempList.add(atom);
            } else if (stack.empty()) {
                finalList.addAll(tempList);
                repeat = false;
            } else {
                for (Atom atom : tempList) {
                    stack.push(atom);
                }
                tempList = null;
                repeat = false;
            }
        } while (repeat);
    }
}
