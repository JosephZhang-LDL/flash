package org.snlab.network;

import java.math.BigInteger;
import java.util.Objects;

public class Rule {
    private int src, srcPrefix;
    private BigInteger match;
//    private BigInteger dstIp;
    private int prefix, priority;
    private Port outPort;
    private int bddmatch = 0; // in bdd format
    private int hit;
    private Device device;
    private boolean isLast = false;

    public Rule(Device device, long ipv4, int prefix, Port outPort) {
        this(device, ipv4, prefix, prefix, outPort);
    }


    public Rule(Device device, long ipv4, int prefix, int priority, Port outPort) {
        this.device = device;
        this.match = BigInteger.valueOf(ipv4);
        this.prefix = prefix;
        this.priority = priority;
        this.outPort = outPort;
        this.srcPrefix = 0;
    }

    public Rule(Device device, int src, int srcPrefix, long ipv4, int prefix, Port outPort) {
        this.device = device;
        this.match = BigInteger.valueOf(ipv4);
        this.prefix = prefix;
        this.priority = prefix;
        this.outPort = outPort;
        this.src = src;
        this.srcPrefix = srcPrefix;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    public void setRandomOutPort() {
        this.outPort = this.device.getPorts().stream().skip((int) (this.device.getPorts().size() * Math.random())).findFirst().get();
    }

    public Device getDevice() {
        return device;
    }

    public BigInteger getMatch() {
        return match;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getPrefix() {
        return prefix;
    }

    public Port getOutPort() {
        return outPort;
    }

    public int getPriority() {
        return priority;
    }

    public int getBddmatch() {
        return bddmatch;
    }

    public void setBddmatch(int bddmatch) {
        this.bddmatch = bddmatch;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public boolean isLast() {
        return isLast;
    }

    public int getSrc() {
        return this.src;
    }

    public int getSrcPrefix() {
        return this.srcPrefix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return priority == rule.priority && prefix == rule.prefix && Objects.equals(match, rule.match) && outPort == rule.outPort && device == rule.device;
    }

    @Override
    public int hashCode() {
        return Objects.hash(match, prefix, outPort, device);
    }
}
