package hello.proxy.proxtFactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적프록시 사용")
    public void InterfaceProxy() {

        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("JDK동프 >> targetClass={}", target.getClass());
        log.info("JDK동프 >> proxyClass={}", proxy.getClass());

        proxy.save();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();

        log.info("물론 proxy.getClass() 로 인스턴스 정보도 찍을수 있다");
        log.info("proxy.getClass() 인스턴스 정보>>> {}", proxy.getClass());
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB 를 사용하고, 클래스기반의 프록시를 사용할수있어요")
    public void proxyTargetClass() {

        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        //여기가 중요하다
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(new TimeAdvice());


        ServiceInterface serviceInterfaceProxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass());
        log.info("ServiceInterface serviceInterfaceProxy  >> proxyClass={}", serviceInterfaceProxy.getClass());

        serviceInterfaceProxy.save();

        assertThat(AopUtils.isAopProxy(serviceInterfaceProxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(serviceInterfaceProxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(serviceInterfaceProxy)).isTrue();

        log.info("물론 serviceInterfaceProxy.getClass() 로 인스턴스 정보도 찍을수 있다");
        log.info("serviceInterfaceProxy.getClass() 인스턴스 정보>>> {}", serviceInterfaceProxy.getClass());
    }


}
