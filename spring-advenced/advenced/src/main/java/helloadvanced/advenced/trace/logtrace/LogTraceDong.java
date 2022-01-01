package helloadvanced.advenced.trace.logtrace;

import helloadvanced.advenced.trace.TraceStatus;

@Deprecated
public interface LogTraceDong extends LogTrace {
    void syncTraceId();
    void releaseTraceId();
}
