package com.careernaksha.careernaksha;

public class Personal {
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getFamilymembers() {
        return familymembers;
    }

    public void setFamilymembers(String familymembers) {
        this.familymembers = familymembers;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFamilyincome() {
        return familyincome;
    }

    public void setFamilyincome(String familyincome) {
        this.familyincome = familyincome;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExtracurricular() {
        return extracurricular;
    }

    public void setExtracurricular(String extracurricular) {
        this.extracurricular = extracurricular;
    }

    public String getHighesteducation() {
        return highesteducation;
    }

    public void setHighesteducation(String highesteducation) {
        this.highesteducation = highesteducation;
    }

    public Personal(String gender, String marital, String familymembers, String facebook, String linkedin, String twitter, String occupation, String address, String familyincome, String skills, String extracurricular, String highesteducation) {
        this.gender = gender;
        this.marital = marital;
        this.familymembers = familymembers;
        this.facebook = facebook;
        this.linkedin = linkedin;
        this.twitter = twitter;
        this.occupation = occupation;
        this.address = address;
        this.familyincome = familyincome;
        this.skills = skills;
        this.extracurricular = extracurricular;
        this.highesteducation = highesteducation;
    }

    String gender,marital,familymembers,facebook,linkedin,twitter,occupation
            ,address,familyincome,skills,extracurricular,highesteducation;
}
