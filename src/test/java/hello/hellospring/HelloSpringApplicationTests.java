package hello.hellospring;

import org.junit.jupiter.api.Test;

import java.util.Scanner;


class HelloSpringApplicationTests {

    @Test
public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();

        System.out.println(A-B);
        sc.close();
    }

    @Test
    void contextLoads() {
    }


}
