package ru.hse.homework.realisation.execution.tasks;

import ru.hse.homework.interfaces.execution.Task;
import ru.hse.homework.realisation.Environment;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ls implements Task {
    public static final String COMMAND = "ls";
    private String[] args;

    @Override
    public void setArgs(String[] args) throws Exception {
        if (args.length > 1) {
            throw new Ls.LsException("Wrong number of args in ls");
        }
        this.args = args;
    }

    private String lsFrom(Path path) throws IOException {
        DirectoryStream<Path> paths = Files.newDirectoryStream(path);
        StringBuilder stringBuilder = new StringBuilder();
        for (Path childPath : paths) {
            stringBuilder.append(childPath.toString());
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    @Override
    public String execute(Environment environment) throws Exception {
        Path path;

        if (args.length == 0) {
            path = environment.getCurrentPath();
        } else {
            Path newPath = Paths.get(args[0]).resolve(environment.getCurrentPath());
            if (Files.exists(newPath)) {
                path = newPath;
            } else {
                return String.format("%s: %s: No such file or directory", COMMAND, args[0]);
            }
        }

        return lsFrom(path);
    }

    @Override
    public String[] getArgs() {
        return new String[0];
    }

    public static class LsException extends Exception {
        LsException(String message) {
            super(message);
        }
    }
}
