package fstf.models;

import java.util.ArrayList;
import java.util.List;

public class ListeRessource {
    public String code;
    public String type;
    public String date_liv;
    public Integer duree_gar;
    public String marque;
    public String details;

    public boolean affacte = false;
    public Affectation affectation;


    public ListeRessource(String code, String type, String date_liv, Integer duree_gar, String marque, String details) {
        this.code = code;
        this.type = type;
        this.date_liv = date_liv;
        this.duree_gar = duree_gar;
        this.marque = marque;
        this.details = details;
    }

    @Override
    public String toString() {
        return "ListeRessource{" +
                "code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", date_liv='" + date_liv + '\'' +
                ", duree_gar=" + duree_gar +
                ", marque='" + marque + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    public List<String> toList(){
        List<String> l = new ArrayList<>();
        l.add(code);
        l.add(type);
        l.add(date_liv);
        l.add(duree_gar + "");
        l.add(marque);
        l.add(details);
        return l;
    }

    public String getCode() {
        return code;
    }

    public boolean isAffacte() {
        return affacte;
    }

    public void setAffacte(boolean affacte) {
        this.affacte = affacte;
    }

    public Affectation getAffectation() {
        return affectation;
    }

    public void setAffectation(Affectation affectation) {
        this.affectation = affectation;
    }
}
