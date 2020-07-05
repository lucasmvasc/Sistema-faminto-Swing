package view.console;


import java.util.Scanner;

public class ViewBase {
    protected final Scanner sc;

    public ViewBase(Scanner sc) {
        this.sc = sc;
    }

    protected int readInt() {
        int v = this.sc.nextInt();
        this.sc.nextLine();
        return v;
    }

    protected void CLS() {
        for (int i = 0; i < 10; i += 1) {
            System.out.println();
        }
    }
}
