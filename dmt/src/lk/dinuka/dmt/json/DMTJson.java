package lk.dinuka.dmt.json;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class DMTJson {

    private JSONObject dictionary;

    public DMTJson(String dictionaryFile) {
        try {
            String jsonString = Files.toString(new File(dictionaryFile), Charsets.UTF_8);
            dictionary = new JSONObject(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String stripSentence(String sentence){
        return sentence.replaceAll("[-+.^:,?@;]","");
    }

    public String getTranslatedString(String sentence){
        String stripped = stripSentence(sentence);
        String[] words = stripped.split(" ");
        String translated = "";
        for (String word : words){
            try{
                translated += dictionary.getString(word)+" ";
            }catch (JSONException e){
                translated += "";
            }
        }
        return translated;
    }

    public static void main(String[] args) {
        DMTJson dmtJson = new DMTJson("./src/lk/dinuka/dmt/json/en-sin.dict");
        String translated = dmtJson.getTranslatedString("I go home");
        System.out.println(translated);
    }

}
