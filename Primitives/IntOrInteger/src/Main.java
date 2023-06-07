public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(33); // Ограничиться только алфавитом русского языка
        System.out.println(container.getCount());

        // Найти все числовые коды символов алфавита русского языка
        for (int i = 1072; i <= 1103; i++) {
            char c = (char) i;
            System.out.println(i + " - " + c);
        }

        for (int i = 1040; i <= 1071; i++) {
            char c = (char) i;
            System.out.println(i + " - " + c);
        }

        char lowercaseYo = 'ё'; // Символ строчной буквы ё
        char uppercaseYo = 'Ё'; // Символ прописной буквы Ё

        System.out.println((int) lowercaseYo + " - " + lowercaseYo);
        System.out.println((int) uppercaseYo + " - " + uppercaseYo);

    }

}

