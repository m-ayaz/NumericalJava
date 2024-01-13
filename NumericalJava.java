package com.example.tools;


public final class NumericalJava {

    public static Complex add(Complex c, double x) {
        return new Complex(c.real + x, c.imag);
    }

    public static Complex add(double x, Complex c) {
        return new Complex(c.real + x, c.imag);
    }

    public static Complex add(Complex c1, Complex c2) {
        return new Complex(c1.real + c2.real, c1.imag + c2.imag);
    }

    public static Complex sub(Complex c, double x) {
        return new Complex(c.real - x, c.imag);
    }

    public static Complex sub(double x, Complex c) {
        return new Complex(x - c.real, -c.imag);
    }

    public static Complex sub(Complex c1, Complex c2) {
        return new Complex(c1.real - c2.real, c1.imag - c2.imag);
    }

    public static Complex mul(Complex c, double x) {
        return new Complex(c.real * x, c.imag * x);
    }

    public static Complex mul(double x, Complex c) {
        return new Complex(c.real * x, c.imag * x);
    }

    public static Complex mul(Complex c1, Complex c2) {
        return new Complex(c1.real * c2.real - c1.imag + c2.imag, c1.real * c2.imag + c1.imag + c2.real);
    }

    public static Complex div(Complex c, double x) {
        if (x == 0) {
            throw new RuntimeException("[NumericalJava] Division by zero.");
        }
        return mul(c, 1 / x);
    }

    public static Complex div(double x, Complex c) {
        return mul(x, c.inv());
    }

    public static Complex div(Complex c1, Complex c2) {
        return mul(c1, c2.inv());
    }

    public static Complex sin(Complex c) {
        return new Complex(Math.sin(c.real) * Math.cosh(c.imag), Math.cos(c.real) * Math.sinh(c.imag));
    }

    public static Complex cos(Complex c) {
        return new Complex(Math.cos(c.real) * Math.cosh(c.imag), -Math.sin(c.real) * Math.sinh(c.imag));
    }

    public static Complex tan(Complex c) {
        return div(sin(c), cos(c));
    }

    public static Complex cot(Complex c) {
        return div(cos(c), sin(c));
    }

    public static class Complex {
        double real, imag;

        public Complex(double real, double imag) {
            this.real = real;
            this.imag = imag;
        }

        public static Complex valueOf(double real, double imag) {
            return new Complex(real, imag);
        }

        public double absVal() {
            return Math.sqrt(real * real + imag * imag);
        }

        public double absVal2() {
            return real * real + imag * imag;
        }

        public Complex conj() {
            return new Complex(real, -imag);
        }

        public boolean equals(Complex c) {
            return real == c.real && imag == c.imag;
        }

        public Complex inv() {
            if (absVal2() == 0) {
                throw new RuntimeException("[NumericalJava] Division by zero.");
            }
            return mul(conj(), 1 / absVal2());
        }

        public String toString() {
            return "%f+%f i".formatted(real, imag);
        }



    }

    public static Complex exp(Complex c) {
        return new Complex(Math.exp(c.real) * Math.cos(c.imag), Math.exp(c.real) * Math.sin(c.imag));
    }

    public static Complex log(Complex c) {
        if (c.real < 0 && c.imag == 0) {
            return new Complex(0.5 * Math.log(c.absVal2()), -Math.PI);
        } else {
            return new Complex(0.5 * Math.log(c.absVal2()), Math.atan2(c.imag, c.real));
        }
    }


}