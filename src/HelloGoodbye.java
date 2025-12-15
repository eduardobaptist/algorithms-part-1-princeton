public class HelloGoodbye {
    public static void main(String[] args) {
        StringBuilder hello = new StringBuilder("Hello ");
        for (int i = 0; i < args.length; i++) {
            if (i > 0) hello.append(" and ");
            hello.append(args[i]);
        }

        hello.append(".");

        StringBuilder goodbye = new StringBuilder("Goodbye ");
        for (int i = args.length - 1; i >= 0; i--) {
            if (i < args.length - 1) goodbye.append(" and ");
            goodbye.append(args[i]);
        }

        goodbye.append(".");

        System.out.println(hello.toString());
        System.out.println(goodbye.toString());
    }
}
