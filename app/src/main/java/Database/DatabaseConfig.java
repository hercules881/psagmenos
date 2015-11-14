package Database;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by h.lionakis on 13/11/2015.
 */
public class DatabaseConfig {
   public interface DatabaseCreationListener{
        void onDatabaseCreated();
    }

    static final String[] questions = new String[]{"Πότε γεννήθηκε ο Κολοκοτρώνης;"};
    static final int[] questionsId = new int[]{1};
    static final   String[] categories = new String[]{"Ιστορία"};

    static final String[] answers = new String[]{"1769","1770","1771","1772" };
    static final int[] answersId = new int[]{1, 2, 3, 4};
    static final   int[] questionIds = new int[]{1, 1 ,1 ,1};
    static final   int[] isValidAnswer = new int[]{0, 1, 0 ,0};

    public static void createDatabase(DatabaseHelper databaseHelper, DatabaseCreationListener databaseCreationListener){

        for(int i = 0 ; i< questions.length; i++){
            databaseHelper.addQuestion(new Question(questionsId[i], questions[i], categories[i]));
        }
        for(int i = 0 ; i< answers.length; i++){
            databaseHelper.addAnswer(new Answer(answersId[i], questionIds[i], answers[i],isValidAnswer[i]));
        }
        databaseCreationListener.onDatabaseCreated();
    }

}
