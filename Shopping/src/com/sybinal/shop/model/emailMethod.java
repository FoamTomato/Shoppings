package com.sybinal.shop.model;

public class emailMethod {
    private Integer id;

    private String name;

    private String smtp;

    private String imap;

    private String smtpPort;

    private String imapPort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp == null ? null : smtp.trim();
    }

    public String getImap() {
        return imap;
    }

    public void setImap(String imap) {
        this.imap = imap == null ? null : imap.trim();
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort == null ? null : smtpPort.trim();
    }

    public String getImapPort() {
        return imapPort;
    }

    public void setImapPort(String imapPort) {
        this.imapPort = imapPort == null ? null : imapPort.trim();
    }
}