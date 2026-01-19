package org.example.GettingToKnowTheLanguage.NumbersAndStrings;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Locale;

import org.example.Utils.SubArticle;

public class Numbers implements SubArticle {
  public void execute() {
    SubArticle.display("Number abstract class base for", new NumbersDemo());

    SubArticle.display("Formating converters and flags", new FormaterDemo());

    SubArticle.display("Basic Math", new BasicMathDemo());

    SubArticle.display("Exponencial", new ExponentialDemo());

    SubArticle.display("Trigonometric", new TrigonometricDemo());
  }
}

class NumbersDemo implements SubArticle {
  public void execute() {
    System.out.println(
        "  - Byte\n" +
            "  - Short\n" +
            "  - Integer\n" +
            "  - Long\n" +
            "  - BigInteger\n" +
            "  - AtomicInteger\n" +
            "  - Float\n" +
            "  - Double\n" +
            "  - BigDecimal\n" +
            "  - AtomicLong\n");
  }
}

class FormaterDemo implements SubArticle {
  static public void customFormat(String pattern, double value) {
    DecimalFormat myFormatter = new DecimalFormat(pattern);
    String output = myFormatter.format(value);
    System.out.println(value + " + " + pattern + " -> " + output);
  }

  public void execute() {
    System.out.println(
        "Formater Converters:\n" +
            "  - d: A decimal integer.\n" +
            "  - f: A float.\n" +
            "  - n: A new line character appropriate to the platform running the application. You should always use %n, rather than \\n.\n"
            +
            "  - tB: A date & time conversion-locale specific full name of month.\n" +
            "  - td, te: A date & time conversion-2 digit day of month. td has leading zeroes as needed, te does not.\n"
            +
            "  - ty, tY: A date & time conversion-ty = 2-digit year, tY = 4-digit year.\n" +
            "  - tl: A date & time conversion-hour in 12-hour clock.\n" +
            "  - tM: A date & time conversion-minutes in 2digits, with leading zeroes as necessary.\n" +
            "  - tp: A date & time conversion-local specific am/pm (lower case).\n" +
            "  - tm: A date & time conversion-months in 2 digits, with leading zeroes as necessary.\n" +
            "  - tD: A date & time conversion-date as %tm%td%ty.\n");

    System.out.println(
        "Formater Flags:.\n" +
            "  -   08: Eight characters in width, with leading zeroes as necessary.\n" +
            "  -    +: Includes sign, whether positive or negative.\n" +
            "  -    ,: Includes local-specific grouping characters.\n" +
            "  -    -: Left-justified.\n" +
            "  -   .3: Three places after decimal point.\n" +
            "  - 10.3: Ten characters in width, right justified, with three places after decimal point.\n");

    long n = 461012;
    System.out.format("%d%n", n); // --> "461012"
    System.out.format("%08d%n", n); // --> "00461012"
    System.out.format("%+8d%n", n); // --> " +461012"
    System.out.format("%,8d%n", n); // --> " 461,012"
    System.out.format("%+,8d%n%n", n); // --> "+461,012"

    double pi = Math.PI;

    System.out.format("%f%n", pi); // --> "3.141593"
    System.out.format("%.3f%n", pi); // --> "3.142"
    System.out.format("%10.3f%n", pi); // --> " 3.142"
    System.out.format("%-10.3f%n", pi); // --> "3.142"
    System.out.format(Locale.FRANCE,
        "%-10.4f%n%n", pi); // --> "3,1416"

    Calendar c = Calendar.getInstance();
    System.out.format("%tB %te, %tY%n", c, c, c); // --> "May 29, 2006"

    System.out.format("%tl:%tM %tp%n", c, c, c); // --> "2:34 am"

    System.out.format("%tD%n", c); // --> "05/29/06"

    // Custom formats
    System.out.println("Custom formats: ");
    customFormat("###,###.###", 123456.789);
    customFormat("###.##", 123456.789);
    customFormat("000000.000", 123.78);
    customFormat("$###,###.###", 12345.67);
  }
}

class BasicMathDemo implements SubArticle {
  public void execute() {
    double a = -191.635;
    double b = 43.74;
    int c = 16, d = 45;

    System.out.printf("The absolute value " + "of %.3f is %.3f%n",
        a, Math.abs(a));

    System.out.printf("The ceiling of " + "%.2f is %.0f%n",
        b, Math.ceil(b));

    System.out.printf("The floor of " + "%.2f is %.0f%n",
        b, Math.floor(b));

    System.out.printf("The rint of %.2f " + "is %.0f%n",
        b, Math.rint(b));

    System.out.printf("The max of %d and " + "%d is %d%n",
        c, d, Math.max(c, d));

    System.out.printf("The min of of %d " + "and %d is %d%n",
        c, d, Math.min(c, d));

  }
}

class ExponentialDemo implements SubArticle {
  public void execute() {
    double x = 11.635;
    double y = 2.76;

    System.out.printf("The value of " + "e is %.4f%n",
        Math.E);

    System.out.printf("exp(%.3f) " + "is %.3f%n",
        x, Math.exp(x));

    System.out.printf("log(%.3f) is " + "%.3f%n",
        x, Math.log(x));

    System.out.printf("pow(%.3f, %.3f) " + "is %.3f%n",
        x, y, Math.pow(x, y));

    System.out.printf("sqrt(%.3f) is " + "%.3f%n",
        x, Math.sqrt(x));
  }
}

class TrigonometricDemo implements SubArticle {
  public void execute() {
    double degrees = 45.0;
    double radians = Math.toRadians(degrees);

    System.out.format("The value of pi " + "is %.4f%n",
        Math.PI);

    System.out.format("The sine of %.1f " + "degrees is %.4f%n",
        degrees, Math.sin(radians));

    System.out.format("The cosine of %.1f " + "degrees is %.4f%n",
        degrees, Math.cos(radians));

    System.out.format("The tangent of %.1f " + "degrees is %.4f%n",
        degrees, Math.tan(radians));

    System.out.format("The arcsine of %.4f " + "is %.4f degrees %n",
        Math.sin(radians),
        Math.toDegrees(Math.asin(Math.sin(radians))));

    System.out.format("The arccosine of %.4f " + "is %.4f degrees %n",
        Math.cos(radians),
        Math.toDegrees(Math.acos(Math.cos(radians))));

    System.out.format("The arctangent of %.4f " + "is %.4f degrees %n",
        Math.tan(radians),
        Math.toDegrees(Math.atan(Math.tan(radians))));
  }
}
