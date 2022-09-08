package org.dawan.formations.dtos;

import org.joda.time.DateTime;


public class NextSession {
    public DateTime beginAt;
    public DateTime finishAt;
    public boolean discounted;
    public Training training;
    public String location;
    public String slug;
    public String type;

    public DateTime getBeginAt() {
        return beginAt;
    }

    public void setBeginAt(String beginAt) {
        this.beginAt = new DateTime(beginAt.substring(0, 18));
    }

    public DateTime getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(String finishAt) {
        this.finishAt = new DateTime(finishAt.substring(0, 18));
    }

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
