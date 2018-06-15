package com.m1kes.korath.utils.ssh;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SSHUtils {

    public static List<String> remoteLs() throws JSchException, IOException {
        List<String> lines = new ArrayList<>();
        Session session = new SSHConnection().getSession();

        Channel c = session.openChannel("exec");
        ChannelExec ce = (ChannelExec) c;

        ce.setCommand("ls -l");
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


    public static List<String> exeCommand(String command) throws JSchException, IOException {
        List<String> lines = new ArrayList<>();
        Session session = new SSHConnection().getSession();

        Channel c = session.openChannel("exec");
        ChannelExec ce = (ChannelExec) c;

        ce.setCommand(command);
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

    public static List<String> checkMailQueue() throws JSchException, IOException {
        Session session = new SSHConnection().getSession();

        Channel c = session.openChannel("exec");
        ChannelExec ce = (ChannelExec) c;

        ce.setCommand("mailq");
        ce.setErrStream(System.err);

        ce.connect();

        List<String> lines = new ArrayList<>();

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



    public static void remoteMkdir() throws JSchException, IOException {
        Session session = new SSHConnection().getSession();

        Channel c = session.openChannel("exec");
        ChannelExec ce = (ChannelExec) c;

        ce.setCommand("mkdir remotetestdir");
        ce.setErrStream(System.err);

        ce.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(ce.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        ce.disconnect();
        session.disconnect();

        System.out.println("Exit code: " + ce.getExitStatus());

    }

    public static void remoteCopy() throws JSchException, IOException, SftpException {
        Session session = new SSHConnection().getSession();

        Channel c = session.openChannel("sftp");
        ChannelSftp ce = (ChannelSftp) c;

        ce.connect();

        ce.put("/home/myuser/test.txt","test.txt");

        ce.disconnect();
        session.disconnect();
    }

}
