package com.org.design.Behavioral.ChainOfResponsibility;

public class ChainOfResponsibilityRunner {
    public static void main(String[] args) {
        LogProcessor logger = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));
        logger.log(LogProcessor.INFO, "hello");
    }
}

abstract class LogProcessor {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    private final LogProcessor nextLogProcessor;

    public LogProcessor(LogProcessor nextLogProcessor) {
        this.nextLogProcessor = nextLogProcessor;
    }

    public void log(int logLevel, String message) {
        if (nextLogProcessor != null) {
            nextLogProcessor.log(logLevel, message);
        }
    }
}

class InfoLogProcessor extends LogProcessor {

    public InfoLogProcessor(LogProcessor nextLogProcessor) {
        super(nextLogProcessor);
    }

    @Override
    public void log(int logLevel, String message) {
        if (logLevel == INFO) {
            System.out.println("INFO -> " + message);
        } else {
            super.log(logLevel, message);
        }
    }
}

class DebugLogProcessor extends LogProcessor {

    public DebugLogProcessor(LogProcessor nextLogProcessor) {
        super(nextLogProcessor);
    }

    @Override
    public void log(int logLevel, String message) {
        if (logLevel == DEBUG) {
            System.out.println("DEBUG -> " + message);
        } else {
            super.log(logLevel, message);
        }
    }
}

class ErrorLogProcessor extends LogProcessor {

    public ErrorLogProcessor(LogProcessor nextLogProcessor) {
        super(nextLogProcessor);
    }

    @Override
    public void log(int logLevel, String message) {
        if (logLevel == ERROR) {
            System.out.println("ERROR -> " + message);
        } else {
            super.log(logLevel, message);
        }
    }
}


