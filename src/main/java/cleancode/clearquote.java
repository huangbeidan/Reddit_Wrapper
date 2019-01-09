package cleancode;

public class clearquote {
    public static String clearsinglequote(String sub){
                String after = sub.replace('\'', '~');
                return after;
    }

    public static void main (String[] args){
        String after = clearsinglequote("Beidan's Home");
        System.out.println(after);
    }
}
