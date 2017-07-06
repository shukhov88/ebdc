package by.oxagile.model;


import java.util.List;

public class Issue {
    private String summary;
    private String createdInSprint;
    private String Sprint;
    private String storyPoints;

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Issue withCreatedInSprint(String createdInSprint) {
        this.createdInSprint = createdInSprint;
        return this;
    }

    public Issue withSprint(String sprint) {
        Sprint = sprint;
        return this;
    }

    public Issue withStoryPoints(String storyPoints) {
        this.storyPoints = storyPoints;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public String getCreatedInSprint() {
        return createdInSprint;
    }

    public String getSprint() {
        return Sprint;
    }

    public String getStoryPoints() {
        return storyPoints;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "summary='" + summary + '\'' +
                ", createdInSprint='" + createdInSprint + '\'' +
                ", Sprint='" + Sprint + '\'' +
                ", storyPoints='" + storyPoints + '\'' +
                '}';
    }
}


