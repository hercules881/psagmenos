package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by h.lionakis on 13/11/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "psagmenoss";

    // Contacts table name
    private static final String TABLE_QUESTIONS = "QUESTIONS";
    private static final String TABLE_ANSWERS = "ANSWERS";

    // Contacts Table Columns names
    private static final String KEY_ID_QUESTION  = "questionid";
    private static final String KEY_QUESTION  = "question";
    private static final String KEY_CATEGORY = "category";

    private static final String KEY_ID_ANSWER = "answerid";
    private static final String KEY_QUESTION_ID = "idquestion";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_VALID_ANSWER = "validanswer";
    // private static final String KEY_IMAGEPATH = "image_path";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
                + KEY_ID_QUESTION  + " INTEGER PRIMARY KEY,"+ KEY_QUESTION + " TEXT,"
                + KEY_CATEGORY +" TEXT" + ")";
        String CREATE_ANSWERS_TABLE = "CREATE TABLE " + TABLE_ANSWERS + "("
                + KEY_ID_ANSWER  + " INTEGER PRIMARY KEY,"+ KEY_QUESTION_ID + " INTEGER,"
                + KEY_ANSWER +" TEXT" + KEY_VALID_ANSWER +"INTEGER" + ")";
        db.execSQL(CREATE_QUESTIONS_TABLE);
        db.execSQL(CREATE_ANSWERS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWERS);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public  void addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_QUESTION, question.getQuestionId());
        values.put(KEY_QUESTION, question.getText()); // Contact Name
        values.put(KEY_CATEGORY, question.getCategory()); // Contact Phone
        // values.put(KEY_IMAGEPATH,user.getImageUrl());
        // Inserting Row
        db.insert(TABLE_QUESTIONS, null, values);
        db.close(); // Closing database connection
    }

    public  void addAnswer(Answer answer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_ANSWER, answer.getAnswerId());
        values.put(KEY_ID_QUESTION, answer.getQuestionId()); // Contact Name
        values.put(KEY_ANSWER, answer.getText()); // Contact Phone
        values.put(KEY_VALID_ANSWER, answer.getIsValidAnswer()); // Contact Phone
        // values.put(KEY_IMAGEPATH,user.getImageUrl());
        // Inserting Row
        db.insert(TABLE_ANSWERS, null, values);
        db.close(); // Closing database connection
    }



    // Getting single contact
    public Question getQuestion(int questionId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_QUESTIONS, new String[]{KEY_ID_QUESTION,
                        KEY_QUESTION, KEY_CATEGORY}, KEY_ID_QUESTION + "=?",
                new String[]{(String.valueOf(questionId))}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Question question = new Question((cursor.getInt(0)),cursor.getString(1), (cursor.getString(2)));
        // return contact
        return question;
    }

    public Answer getAnswer(int answerId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ANSWERS, new String[]{KEY_ID_ANSWER,
                        KEY_ANSWER, KEY_QUESTION_ID , KEY_VALID_ANSWER}, KEY_ID_ANSWER + "=?",
                new String[]{(String.valueOf(answerId))}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Answer answer = new Answer((cursor.getInt(0)),cursor.getInt(1), (cursor.getString(2)),cursor.getInt(3));
        // return contact
        return answer;
    }

    // Getting All Contacts
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestionId(cursor.getInt(0));
                question.setText(cursor.getString(1));
                question.setCategory((cursor.getString(2)));
                // Adding contact to list
                questions.add(question);
            } while (cursor.moveToNext());
        }

        // return contact list
        return questions;
    }

    public List<Answer> getAllAnswers() {
        List<Answer> answers = new ArrayList<Answer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ANSWERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Answer answer = new Answer();
                answer.setAnswerId(cursor.getInt(0));
                answer.setQuestionId(cursor.getInt(1));
                answer.setText((cursor.getString(2)));
                answer.setIsValidAnswer(cursor.getInt(3));
                // Adding contact to list
                answers.add(answer);
            } while (cursor.moveToNext());
        }

        // return contact list
        return answers;
    }
    // Updating single contact
    public List<Answer> getPossibleAnswersForQuestion(Question question) {
        List<Answer> answers = new ArrayList<Answer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ANSWERS + "WHERE" + KEY_QUESTION_ID + "="+ String.valueOf(question.getQuestionId());

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Answer answer = new Answer();
                answer.setAnswerId(cursor.getInt(0));
                answer.setQuestionId(cursor.getInt(1));
                answer.setText((cursor.getString(2)));
                answer.setIsValidAnswer(cursor.getInt(3));
                // Adding contact to list
                answers.add(answer);
            } while (cursor.moveToNext());
        }

        // return contact list
        return answers;
    }
}
