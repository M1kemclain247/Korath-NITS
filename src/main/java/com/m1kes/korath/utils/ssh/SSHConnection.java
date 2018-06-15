package com.m1kes.korath.utils.ssh;

import com.jcraft.jsch.*;
import com.m1kes.korath.storage.yaml.Configuration;
import com.m1kes.korath.storage.yaml.SSHConfig;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SSHConnection {

    private JSch jsch = new JSch();
    private Properties config = new Properties();

    {
        config.put("StrictHostKeyChecking", "no");
    }


    private String host = "10.29.12.35";
    private String user = "root";
    private String password = "P@ssnits17";
    private String command1 = "ls";
    private String channeltype = "exec";
    private int port = 22;

    public SSHConnection() {

        Yaml yaml = new Yaml();
        try {

            Resource resource = new ClassPathResource("ssh.yml");
            InputStream resourceInputStream = resource.getInputStream();

            SSHConfig config = yaml.loadAs(resourceInputStream, SSHConfig.class);

            System.out.println("Loading SSH config.yml");

            host = config.getHost();
            user = config.getUsername();
            password = config.getPassword();
            port = config.getPort();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public SSHConnection(String host, String username , String password) {
            this.host = host;
            this.user = username;
            this.password = password;
    }

    public Session getSession() throws JSchException {

        Session session = jsch.getSession(user, host, port);
        session.setPassword(password);
        session.setConfig(config);
        session.connect();
        return session;
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

    public void runCommand(String command) {

        try {
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();

            System.out.println("Connected");

            Channel channel = session.openChannel(channeltype);
            ((ChannelExec) channel).setCommand(command1);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();

            byte[] tmp = new byte[1024];

            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            channel.disconnect();
            session.disconnect();

            System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<String> exeCommand(String command) {
        List<String> lines = new ArrayList<>();
        try {
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();

            System.out.println("Connected");

            Channel channel = session.openChannel(channeltype);
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();

            byte[] tmp = new byte[1024];

            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));
                    lines.add(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            channel.disconnect();
            session.disconnect();

            System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<String> executeCommand(String cmd) throws JSchException, IOException {
        List<String> lines = new ArrayList<>();

        Session session = jsch.getSession(user, host, port);
        session.setPassword(password);
        session.setConfig(config);
        session.connect();

        Channel c = session.openChannel(channeltype);
        ChannelExec ce = (ChannelExec) c;

        ce.setCommand(cmd);
        ce.setErrStream(System.err);

        ce.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(ce.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            lines.add(line);
        }

        ce.disconnect();
        session.disconnect();

        System.out.println("Exit code: " + ce.getExitStatus());
        return lines;
    }

}
