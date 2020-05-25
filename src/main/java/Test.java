public class Test {

    public String solution(int n) {
        int[] arr = new int[32];
        boolean flag = n >= 0;
        int i = 0;
        while (n != 0) {
            if (flag) {
                arr[i++] = n % 2;
            } else {
                int num = 1 ^ (n % 2);
                arr[i++] = num;
            }
            n = n / 2;
        }

        if (! flag) {
            for (int j = 0; j < arr.length; j ++) {
                int temp = arr[j] + 1;
                arr[j] = temp % 2;
                temp = temp / 2;
                if (temp <= 1) {
                    break;
                }
            }

            arr[31] = 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int j = arr.length - 1; j >= 0; j--) {
            sb.append(arr[j]);
        }

        return sb.toString();
    }
}

