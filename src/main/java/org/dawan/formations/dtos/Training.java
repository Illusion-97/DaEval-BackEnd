package org.dawan.formations.dtos;

public class Training {
    public String title;
    public String duration;
    public String description;
    public String slug;
    public String alias;
    public String fullAlias;
    public int standardPrice;
    public int customPrice;
    public int customPriceExtra;
    public int remotelyPrice;
    public String objectives;
    public String prerequisites;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFullAlias() {
        return fullAlias;
    }

    public void setFullAlias(String fullAlias) {
        this.fullAlias = fullAlias;
    }

    public int getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(int standardPrice) {
        this.standardPrice = standardPrice;
    }

    public int getCustomPrice() {
        return customPrice;
    }

    public void setCustomPrice(int customPrice) {
        this.customPrice = customPrice;
    }

    public int getCustomPriceExtra() {
        return customPriceExtra;
    }

    public void setCustomPriceExtra(int customPriceExtra) {
        this.customPriceExtra = customPriceExtra;
    }

    public int getRemotelyPrice() {
        return remotelyPrice;
    }

    public void setRemotelyPrice(int remotelyPrice) {
        this.remotelyPrice = remotelyPrice;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }
}
