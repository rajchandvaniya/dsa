package com.rajch.browserstack;

import java.util.ArrayList;
import java.util.List;

public class Machine {
    private String ip;
    private List<String> logs;

    public Machine(String ip) {
        this.ip = ip;
        this.logs = new ArrayList<>();
    }

    public List<String> getLogs() {
        return logs;
    }

    public String getIp() {
        return ip;
    }

    public void appendLog(String log) {
        this.logs.add(log);
    }

    @Override
    public String toString() {
        return "Machine{" +
                "ip=" + ip +"}";
    }
}
