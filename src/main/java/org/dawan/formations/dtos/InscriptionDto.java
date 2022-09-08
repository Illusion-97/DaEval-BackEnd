package org.dawan.formations.dtos;

public class InscriptionDto {

    private long promotionId;
    private long[] utilisateursId;

    public long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(long promotionId) {
        this.promotionId = promotionId;
    }

    public long[] getUtilisateursId() {
        return utilisateursId;
    }

    public void setUtilisateursId(long[] utilisateursId) {
        this.utilisateursId = utilisateursId;
    }
}
