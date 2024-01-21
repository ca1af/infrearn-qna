package com.example.qnaproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

class MyConfigurationTest {
    /**
     * Configuration 어노테이션이 붙은 빈들은 스프링이 기본적으로 proxy 를 사용해서 관리한다.
     * CommonBean <- 의존하는 빈이 2개 이상일 때 같은 CommonBean 이 리턴되도록 보장한다.
     */
    @Test
    void configuration() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);
        assertThat(bean1.common).isSameAs(bean2.common);
    }

    @Test
    void proxy_common_method() {
        // 확장이 아닌 대체 로 동작한다.
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        // 완전히 같은 녀석들!
        assertThat(bean1.common).isSameAs(bean2.common);
    }

    static class MyConfigProxy extends MyConfig{
        private Common common;

        /**
         * 이 작업으로써 매번 부모클래스의 빈을 호출하는 것이 아닌, 필드에 저장된 실제 클래스의 빈을 캐싱하듯 사용 가능하다.
         * @return this.bean == null ? super.bean() : this.bean;
         */
        @Override
        Common common(){
            if (this.common == null){
                this.common = super.common();
            }
            return this.common;
        }
    }

    /**
     * proxyBeanMethod 는 프록시를 이용한 bean Method 를 사용할것인지 여부를 결정하게 한다.
     * SpringBoot 3.x~ Spring core 5.2 ~ 기능. ( 이 경우 "myConfigurationTest" 는 실패 한다 )
     */
    @Configuration(proxyBeanMethods = false)
    // why proxyBeanMethod ? -> 매번 프록시를 만드는 비용을 줄이기 위해서.
    // -> 중복 빈을 사용하지 않는다면 굳이 사용할 필요가 없으므로
    static class MyConfig {
        @Bean
        Common common(){
            return new Common();
        }

        @Bean
        Bean1 bean1(){
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2(){
            return new Bean2(common());
        }
    }

    static class Bean1{
        private final Common common;

        Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2{
        private final Common common;

        Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common{

    }
}