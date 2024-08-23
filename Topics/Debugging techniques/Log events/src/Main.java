class Util {
    public static String capitalize(String str) {
        String finalStr;
        System.out.println("Before: " + str);
        if (str == null || str.isBlank()) {
            finalStr = str;
            System.out.println("After: " + finalStr);
            return finalStr;
        }

        if (str.length() == 1) {
            finalStr = str.toUpperCase();
            System.out.println("After: " + finalStr);
            return finalStr;
        }

        finalStr = Character.toUpperCase(str.charAt(0)) + str.substring(1);
        System.out.println("After: " + finalStr);
        return finalStr;
    }
}