package Database;

import android.content.Context;

/**
 * Created by h.lionakis on 13/11/2015.
 */
public class DatabaseConfig {
    static final String[] questions = new String[]{"Πότε γεννήθηκε ο Κολοκοτρώνης;"};
    static final int[] questionsId = new int[]{1};
    static final   String[] categories = new String[]{"Ιστορία"};

    public static void createDatabase(DatabaseHelper databaseHelper){

        for(int i = 0 ; i< questions.length; i++){
            databaseHelper.addQuestion(new Question(questionsId[i], questions[i], categories[i]));
        }
    }

}
