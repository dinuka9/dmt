package lk.dinuka.dmt.filerw;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DMTFileRW {
    private List<String> wordlist = new ArrayList<>();

    public DMTFileRW(String dictionary) {
        try {
            wordlist = Files.readLines(new File(dictionary), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String stripSentence(String sentence) {
        return sentence.replaceAll("[-+.^:,?@;]", "");
    }

    private String matchWord(String word) {
        String matchingWord = "";
        for (String item : wordlist){
            String[] split = item.split(" ");
            if (split[0].equals(word)){
                matchingWord = split[1]+" ";
                break;
            }else {
                matchingWord = "";
            }
        }
        return matchingWord;
    }

    public String translateString(String toTranslate) {
        String stripped = stripSentence(toTranslate);
        String[] words = stripped.split(" ");
        String translated = "";
        for (String word : words) {
            translated += matchWord(word);
        }
        return translated;
    }

    public static void main(String[] args) {
        DMTFileRW dmtFileRW = new DMTFileRW("./src/lk/dinuka/dmt/filerw/en-sin.dict");
        String translated = dmtFileRW.translateString("I go home");
        System.out.println(translated);
    }

}
