package helloadvanced.advenced.trace.logtrace;

import helloadvanced.advenced.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
