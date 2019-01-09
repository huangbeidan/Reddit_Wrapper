package cleancode;

public class stringsplit {
    public static String[] mysplit(String before){
        String delimiters = "\\(|\\)";
        String[] after = before.split(delimiters);
        return after;
    }

    public static void main(String[] args){
        String before = "Submission(t3_9k4mgk)<The original sources of MS-DOS 1.25 and 2.0>";
        String[] after = mysplit(before);
        int i = 0;
        for (String part:after){
            System.out.println(part);
            i++;
        }
    }

}
