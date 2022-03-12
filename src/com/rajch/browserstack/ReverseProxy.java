package com.rajch.browserstack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReverseProxy {
    private String domainName;
    private List<Machine> machineList;
    private Map<String, Machine> machineMap;
    private int currentMachine;

    public ReverseProxy(String domainName, Map<String, Machine> machineMap, String[] ips) {
        this.domainName = domainName;
        this.machineList = new ArrayList<>();
        this.currentMachine = 0;
        this.machineMap = machineMap;
        for(String ip: ips) {
            machineList.add(this.machineMap.get(ip));
        }
    }

    public void executeQuery(String query) {
        // Special requests
        // <domain_name>/machine_down?ip=<machine ip>
        // <domain_name>/machine_up?ip=<machine_ip>
        if(query.contains("machine_down")) {
            int ipIndex = query.indexOf("?ip=") + 4;
            String ip = query.substring(ipIndex);
            downMachine(ip);
        } else if(query.contains("machine_up")) {
            int ipIndex = query.indexOf("?ip=") + 4;
            String ip = query.substring(ipIndex);
            upMachine(ip);
        } else {
            Machine machine = machineList.get(currentMachine);
            forward(machine, query);
            currentMachine = (currentMachine+1)%machineList.size();
        }
    }

    private void upMachine(String ip) {
        System.out.println("Adding Machine to RP: "+domainName+" IP: "+ip);
        Machine machine = machineMap.get(ip);
        machineList.add(machine);
    }

    private void downMachine(String ip) {
        // finding index of machine with ip
        int i=0;
        for(i=0; i<machineList.size(); i++) {
            if(machineList.get(i).getIp().equals(ip)) break;
        }
        System.out.println("Removing Machine from RP: "+domainName+" IP: "+ip+" Index: "+i);
        machineList.remove(i);
    }

    private void forward(Machine machine, String req) {
        System.out.println("Forwarding request RP: "+domainName+" Machine: "+machine.getIp()+" Req: "+req);
        machine.appendLog(req);
    }
}
