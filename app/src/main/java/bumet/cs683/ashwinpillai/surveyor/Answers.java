package bumet.cs683.ashwinpillai.surveyor;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashwinpillai on 11/8/17.
 */

@Entity
public class Answers {

    private Long timestamp;
    private String question;
    private String answer;

    @Generated(hash = 1236458999)
    public Answers(Long timestamp, String question, String answer) {
        this.timestamp = timestamp;
        this.question = question;
        this.answer = answer;
    }
    @Generated(hash = 49110312)
    public Answers() {
    }



    public Long getTimestamp() {
        return this.timestamp;
    }
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    public String getQuestion() {
        return this.question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswer() {
        return this.answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static void create(DaoSession daoSession, Long timestamp, String question, String answer) {
        AnswersDao answersDao = daoSession.getAnswersDao();
        Answers answerobj = new Answers();
        answerobj.timestamp = timestamp;
        answerobj.question = question;
        answerobj.answer = answer;
        answersDao.save(answerobj);
    }


    public static List<Answers> getAllData(DaoSession daoSession) {
        AnswersDao answersDao = daoSession.getAnswersDao();

        ArrayList<Answers> answers = (ArrayList<Answers>) answersDao.loadAll();
        return answers;
    }
    @Override
    public String toString() {
        return answer;
    }
}
