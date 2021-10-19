package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeader extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printHeaderBasic(request);
        printHeaderUtils(request);
        printEtc(request);

        response.getWriter().write("OK");
    }

    private void printEtc(HttpServletRequest request) {
        System.out.println("----- RequestHeader.printEtc");
        System.out.println("Remote 정보 출력");
        System.out.println("request.getRemoteUser() = " + request.getRemoteUser());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());

        System.out.println("Locale 정보 출력");
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getLocalPort() = " + request.getLocalPort());
        System.out.println("----- RequestHeader.printEtc");
    }

    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println();
        System.out.println("----- RequestHeader.printHeaderUtils");
        System.out.println("----- 모든 Header 긁어오기");
        request.getHeaderNames().asIterator().forEachRemaining(headerName ->
                System.out.println(headerName + ": " + request.getHeader(headerName)));
        System.out.println("----- Headers");
        System.out.println();


        System.out.println();
        System.out.println("----- 편리한 Header 조회");

        System.out.println("Host 조회하기");
        System.out.println("request.getServerName() = " + request.getServerName());
        System.out.println("request.getServerPort() = " + request.getServerPort());
        System.out.println();

        System.out.println("Accepted-Language(Locale) 모두 조회하기");
        request.getLocales().asIterator().forEachRemaining(
                locale -> System.out.println("locale = " + locale)
        );
        System.out.println("Accepted-Language(Locale) 최우선 하나만 조회하기");
        System.out.println("request.getLocale() = " + request.getLocale());


        System.out.println("Cookie 조회하기");
        if(request.getCookies()!=null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println("cookie.getValue() = " + cookie.getValue());
            }
        }


        System.out.println("Content 조회하기");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());

        System.out.println("----- 편리한 Header 조회");
        System.out.println("----- RequestHeader.printHeaderUtils");
        System.out.println();
    }

    private void printHeaderBasic(HttpServletRequest request) {
        System.out.println();
        System.out.println("----- RequestHeader.service -----");
        System.out.println("request.getMethod() = " + request.getMethod());
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getProtocol() = " + request.getProtocol());
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        System.out.println("request.getRequestedSessionId() = " + request.getRequestedSessionId());
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure());
        System.out.println("----- RequestHeader.service -----");
        System.out.println();
    }
}
