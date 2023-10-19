package com.example.polyfood.Validation;

import java.util.*;

public class ValidationNumberGenerator {
    // Quy định mã Code sẽ có độ dài là 6
    private static final int VALIDATION_NUMBER_LENGTH = 6;
    // List lưu các mảng String bao gồm cặp code và email
    public static List<String[]> storeValidation = new ArrayList<>();
    // List lưu các mảng String bao gồm cặp code và email

    public static void generateValidationNumber(String email) {
        Random random = new Random();
        // lưu mã code (6 số)
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < VALIDATION_NUMBER_LENGTH; i++) {
            sb.append(random.nextInt(10)); // thêm từng số
        }

        // Tạo một mảng chuỗi bao gồm cặp [email, code]
        String[] str = {email, String.valueOf(sb)};

        // Lưu vào danh sách các code hiện có [...[email, code]]
        storeValidation.add(str);
    }

    public static void expireValidation(String email) {
        Timer timer = new Timer();
        final boolean[] remove = {false};

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for(String[] item: storeValidation) {
                    for(String i: item) {
                        if(i.equals(email)) {
                            storeValidation.remove(item);
                            remove[0] = true;
                            break;
                        }
                    }

                    if(remove[0]) {
                        break;
                    }
                }
                System.out.println("Dữ liệu của storeValidation đã bị xóa sau 1h");
            }
        }, 60 * 60 * 1000); // miliseconds
    }
}
