package com.rajch.browserstack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* Problem Statement: https://leetcode.com/discuss/interview-experience/1480034/BrowserStack-or-SE-Intern-OA/1093866
*
* */
public class Driver {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int noOfMachines = Integer.parseInt(br.readLine().trim());
        String[] machines = br.readLine().trim().split("\\s+");

        // creating a map of machines
        Map<String, Machine> machineMap = new HashMap<>();
        for(String ip: machines) {
            machineMap.put(ip, new Machine(ip));
        }

        // creating a map of ReverseProxies
        Map<String, ReverseProxy> reverseProxyMap = new HashMap<>();

        int noOfRP = Integer.parseInt(br.readLine().trim());
        for(int i=0; i<noOfRP; i++) {
            String domainName = br.readLine().trim();
            int noOfMachinesInRP = Integer.parseInt(br.readLine().trim());
            String[] ips = br.readLine().trim().split("\\s+");
            ReverseProxy rp = new ReverseProxy(domainName, machineMap, ips);
            reverseProxyMap.put(domainName, rp);
        }

        int noOfQueries = Integer.parseInt(br.readLine().trim());
        List<String> queries = new ArrayList<>();
        for(int i=0; i<noOfQueries; i++)
            queries.add(br.readLine().trim());

        execute(queries, reverseProxyMap);

        System.out.println("-----------------LOGS-----------------------");
        for(String ip: machines) {
            System.out.println(ip);
            System.out.println(machineMap.get(ip).getLogs());
            System.out.println("========================================");
        }
    }

    private static void execute(List<String> queries, Map<String, ReverseProxy> reverseProxyMap) {
        // <domain_name>/<path>
        for(String query: queries) {
            String domainName = query.substring(0, query.indexOf("/"));
            System.out.println("Forwarding query: "+query+" to ReverseProxy: "+domainName);
            reverseProxyMap.get(domainName).executeQuery(query);
        }
    }
}


/*
* Sample Input/Output Format:
*
5
ip1 ip2 ip3 ip4 ip5
1
www.rp1.com
2
ip1 ip3
10
www.rp1.com/get/all
www.rp1.com/get/all2
www.rp1.com/get/all3
www.rp1.com/get/all4
www.rp1.com/machine_down?ip=ip1
www.rp1.com/get/all5
www.rp1.com/get/all6
www.rp1.com/machine_up?ip=ip1
www.rp1.com/get/all7
www.rp1.com/get/all8
Forwarding query: www.rp1.com/get/all to ReverseProxy: www.rp1.com
Forwarding request RP: www.rp1.com Machine: ip1 Req: www.rp1.com/get/all
Forwarding query: www.rp1.com/get/all2 to ReverseProxy: www.rp1.com
Forwarding request RP: www.rp1.com Machine: ip3 Req: www.rp1.com/get/all2
Forwarding query: www.rp1.com/get/all3 to ReverseProxy: www.rp1.com
Forwarding request RP: www.rp1.com Machine: ip1 Req: www.rp1.com/get/all3
Forwarding query: www.rp1.com/get/all4 to ReverseProxy: www.rp1.com
Forwarding request RP: www.rp1.com Machine: ip3 Req: www.rp1.com/get/all4
Forwarding query: www.rp1.com/machine_down?ip=ip1 to ReverseProxy: www.rp1.com
Removing Machine from RP: www.rp1.com IP: ip1 Index: 0
Forwarding query: www.rp1.com/get/all5 to ReverseProxy: www.rp1.com
Forwarding request RP: www.rp1.com Machine: ip3 Req: www.rp1.com/get/all5
Forwarding query: www.rp1.com/get/all6 to ReverseProxy: www.rp1.com
Forwarding request RP: www.rp1.com Machine: ip3 Req: www.rp1.com/get/all6
Forwarding query: www.rp1.com/machine_up?ip=ip1 to ReverseProxy: www.rp1.com
Adding Machine to RP: www.rp1.com IP: ip1
Forwarding query: www.rp1.com/get/all7 to ReverseProxy: www.rp1.com
Forwarding request RP: www.rp1.com Machine: ip3 Req: www.rp1.com/get/all7
Forwarding query: www.rp1.com/get/all8 to ReverseProxy: www.rp1.com
Forwarding request RP: www.rp1.com Machine: ip1 Req: www.rp1.com/get/all8
-----------------LOGS-----------------------
ip1
[www.rp1.com/get/all, www.rp1.com/get/all3, www.rp1.com/get/all8]
========================================
ip2
[]
========================================
ip3
[www.rp1.com/get/all2, www.rp1.com/get/all4, www.rp1.com/get/all5, www.rp1.com/get/all6, www.rp1.com/get/all7]
========================================
ip4
[]
========================================
ip5
[]
========================================

Process finished with exit code 0
* */