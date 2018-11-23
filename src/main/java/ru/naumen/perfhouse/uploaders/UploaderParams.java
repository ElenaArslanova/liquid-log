package ru.naumen.perfhouse.uploaders;

public class UploaderParams {
    private String influxDb;
    private String host;
    private String user;
    private String password;
    private boolean requiredLogTrace;

    public UploaderParams(String influxDb, String host, String user, String password, boolean requiredLogTrace){
        this.influxDb = influxDb;
        this.host = host;
        this.user = user;
        this.password = password;
        this.requiredLogTrace = requiredLogTrace;
    }

    public String getInfluxDb() {
        return influxDb;
    }

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRequiredLogTrace() {
        return requiredLogTrace;
    }
}
