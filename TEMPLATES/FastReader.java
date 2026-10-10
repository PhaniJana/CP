import java.io.*;


class FastReader {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1 << 16];
    private int ptr = 0;
    private int len = 0;

    FastReader() {
    }

    private int read() throws IOException {
        if (ptr >= len) {
            len = in.read(buffer);
            ptr = 0;

            if (len <= 0) {
                return -1;
            }
        }

        return buffer[ptr++];
    }

    String next() throws IOException {
        int c;

        do {
            c = read();
        } while (c <= ' ' && c != -1);

        if (c == -1) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        while (c > ' ') {
            sb.append((char) c);
            c = read();
        }

        return sb.toString();
    }

    int nextInt() throws IOException {
        return (int) nextLong();
    }

    long nextLong() throws IOException {
        int c;

        do {
            c = read();
        } while (c <= ' ' && c != -1);

        if (c == -1) {
            throw new EOFException("Unexpected end of input");
        }

        long sign = 1;

        if (c == '-') {
            sign = -1;
            c = read();
        }

        long value = 0;

        while (c > ' ') {
            value = value * 10 + c - '0';
            c = read();
        }

        return value * sign;
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    String nextLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        int c = read();

        if (c == -1) {
            return null;
        }

        while (c != '\n' && c != -1) {
            if (c != '\r') {
                sb.append((char) c);
            }
            c = read();
        }

        return sb.toString();
    }

    char nextChar() throws IOException {
        return next().charAt(0);
    }

    int[] nextIntArray(int n) throws IOException {
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
        }

        return arr;
    }

    long[] nextLongArray(int n) throws IOException {
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = nextLong();
        }

        return arr;
    }

    double[] nextDoubleArray(int n) throws IOException {
        double[] arr = new double[n];

        for (int i = 0; i < n; i++) {
            arr[i] = nextDouble();
        }

        return arr;
    }

    String[] nextStringArray(int n) throws IOException {
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = next();
        }

        return arr;
    }

    char[] nextCharArray() throws IOException {
        return next().toCharArray();
    }
}
