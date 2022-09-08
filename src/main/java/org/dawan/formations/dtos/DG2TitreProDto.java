package org.dawan.formations.dtos;

import java.util.ArrayList;

public class DG2TitreProDto {
    public String full_alias;
    public int score;
    public Training training;
    public ArrayList<NextSession> next_sessions;

    public String getFull_alias() {
        return full_alias;
    }

    public void setFull_alias(String full_alias) {
        this.full_alias = full_alias;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public ArrayList<NextSession> getNext_sessions() {
        return next_sessions;
    }

    public void setNext_sessions(ArrayList<NextSession> next_sessions) {
        this.next_sessions = next_sessions;
    }
}
