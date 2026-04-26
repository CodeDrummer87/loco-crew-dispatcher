package x.team.loco_crew_dispather.entity;

public enum Status {
    WORKING("работает"),
    ON_CALL("на вызове"),
    WAITING_FOR_REPORT("в ожидании явки"),
    SICK("больничный"),
    VACATION("отпуск"),
    STUDY("учёба"),
    SESSION("сессия"),
    BUSINESS_TRIP("команидировка"),
    RETIRED("пенсия");

    private final String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
