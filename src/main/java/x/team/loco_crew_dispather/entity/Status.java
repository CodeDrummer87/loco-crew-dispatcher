package x.team.loco_crew_dispather.entity;

public enum Status {
    WORKING("работает"),
    WEEKEND("на выходных"),
    SECURING("на закреплении"),
    SICK("на больничном"),
    VACATION("в отпуске"),
    STUDY("на учёбе"),
    SESSION("на сессии"),
    BUSINESS_TRIP("в командировке");

    private final String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}