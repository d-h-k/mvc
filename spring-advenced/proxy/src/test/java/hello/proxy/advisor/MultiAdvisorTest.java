package hello.proxy.advisor;

import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

@Slf4j
public class MultiAdvisorTest {

    @Test
    @DisplayName("multiAdvisorTest1 - 매 advisor1,2 마다 프록시를 전부다 생성 >>  이럴필요는 없다는걸 보여주기위함")
    void multiAdvisorTest1() {

        //client -> proxy2(advisor2) -> proxy1(advisor1) -> target
        ServiceInterface target = new ServiceImpl();


        //프록시1 생성
        ProxyFactory proxyFactory1 = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor1 = new
                DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        proxyFactory1.addAdvisor(advisor1);
        ServiceInterface proxy1 = (ServiceInterface) proxyFactory1.getProxy();


        //프록시2 생성, target -> proxy1  력
        ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
        DefaultPointcutAdvisor advisor2 = new
                DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
        proxyFactory2.addAdvisor(advisor2);
        ServiceInterface proxy2 = (ServiceInterface) proxyFactory2.getProxy();
        //실행!
        proxy2.save();
        log.info("어드바이저 하나마다  프록시, 어드바이저마다 프록시객체를 생성할 필요가 없다!!");
    }

    @Slf4j
    static class Advice1 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("111>> advice1 호출!");
            return invocation.proceed();
        }
    }
    @Slf4j
    static class Advice2 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("222 >> advice2 호출!");
            return invocation.proceed();
        }
    }

    @Test
    @DisplayName("multiAdvisorTest2 - 어드바이저가 몇개든 프록시객체는 하나만 만들면 된다")
    void multiAdvisorTest2() {

        //client -> proxy2(advisor2) -> proxy1(advisor1) -> target

        DefaultPointcutAdvisor advisor1 = new
                DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        DefaultPointcutAdvisor advisor2 = new
                DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
        ServiceInterface target = new ServiceImpl();

        //프록시 생성
        ProxyFactory proxyFactory = new ProxyFactory(target);

        proxyFactory.addAdvisor(advisor2);
        log.warn("여기서 순서가 중요한데, 적용되기 원하는 순서대로 프록시를 적용해줘야 한다");
        proxyFactory.addAdvisor(advisor1);

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        //실행!
        proxy.save();
        log.info("프록시 하나에 advice 여러개 다는거 가능해!!");
    }

}
