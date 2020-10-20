import java.util.Scanner;
public class Polynom {
    public double[] koef;
    public int step;

    public Polynom(int n) {
        step = n;
        koef = new double[step+1];
        for (int i = 0; i <= step; i++) {
            koef[i] = 0;
        }
    }

    public Polynom(double[] k){
        step = k.length;
        koef = new double[step+1];
        for (int i = 0; i < step; i++) {
            koef[i] = k[i];
        }
    }

    public void Input() {
        koef = new double[step+1];
        Scanner sc = new Scanner(System.in);
        System.out.println("vvedy koefits");
        for (int i = 0; i <= step; i++) {
            koef[i] = sc.nextInt();
        }
        sc.close();
    }

    public static Polynom Summ(Polynom a, Polynom b) {
        int max_deg = Math.max(a.step, b.step);
        int min_deg = Math.min(a.step, b.step);
        Polynom res = new Polynom(max_deg);
        for (int i=0; i<=min_deg; i++) {
            res.koef[i] = a.koef[i] + b.koef[i];
        }
        for (int i=min_deg; i<=max_deg; i++) {
            if(a.step>b.step){
                res.koef[i] = a.koef[i];
            }
            else res.koef[i] = b.koef[i];
        }
        return  res;
    }

    public static  Polynom ChMult(Polynom a, double b){
        Polynom c = new Polynom(a.step);
        for (int i = 0; i <= a.step; i++) {
            c.koef[i] = a.koef[i]*b;
        }
        return c;
    }

    public static Polynom Myltiply(Polynom a, Polynom b) {
        Polynom temp = new Polynom(a.step + b.step);
        for (int i = 0; i <= a.step; i++) {
            for (int j = 0; j <= b.step; j++) {
                temp.koef[i + j] += a.koef[i] * b.koef[j];
            }
        }
        return temp;
    }

    public static Polynom Raz(Polynom a, Polynom b){
        Polynom c = ChMult(b, -1);
        Polynom d = Summ(a,c);
        return d;
    }

    public double getValue(int x){
        double res = koef[0];
        double px = x;
        for (int i = 1; i<koef.length; i++){
            res += px*koef[i];
            px*=x;
        }
        return res;
    }

    public static Polynom LP(Double[] z){
        Polynom l = new Polynom(new double[] {1});
        Polynom L = new Polynom(new double[] {0});
        for(int k = 0;k < z.length; k++) {
            if(z[k] != 0) {
                for (int j = 0; j < z.length; j++) {
                    if (j != k) {
                        Polynom numerator = new Polynom(new double[]{-z[j], 1});
                        double denominator = 1 / (z[k] - z[j]);
                        l = Polynom.Myltiply(l, Polynom.ChMult(numerator, denominator));
                    }
                }
                L = Polynom.Summ(L, l);
                l = new Polynom(new double[]{1});
            }
        }
        return L;
    }

    @Override
    public String toString() {
        String str = " ";
        int b;
        b = step;
        while (koef[b] == 0 && b >= 0)
            b = b - 1;
        if (b < 0) {
            str = "Polinom = 0\\n";
        } else {
            if (b == 0) {
                str += koef[0];
            } else {
                if(koef[b] == 1){
                    if(b == 1){
                        str +="x";
                    }
                    else str +="x^" + b;
                }
                else{if(koef[b] == -1){
                    if(b == 1){
                        str +="x";
                    }
                    else str +="-" + "x^" + b;
                }
                    else str += koef[b] + "x^" + b;
                }
                b = b - 1;
                for (int i = b; i > 0; i--) {
                    if (koef[i] < 0) {
                        if(koef[i] == -1){
                            str += "-";
                        }
                        else {
                            str += koef[i];
                        }
                    }
                    if (koef[i] > 0) {
                        if(koef[i] == 1){
                            str += "+";
                        }
                        else {
                            str += "+" + koef[i];
                        }
                    }
                    if (koef[i] != 0) {
                        if(i==1){
                            str += "x";
                        }
                        else str += "x^" + i;

                    }

                }
                ;
                if (koef[0] < 0) {
                    str += koef[0];
                }
                if (koef[0] > 0) {
                    str += "+" + koef[0];
                }
            }
        }
        return str;
    }
}
