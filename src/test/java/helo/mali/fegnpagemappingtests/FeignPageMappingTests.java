package helo.mali.fegnpagemappingtests;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

@SpringBootTest(classes = FeignPageMappingTests.Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, value = {
    "feign.compression.request.enabled=true",
    "hystrix.command.default.execution.isolation.strategy=SEMAPHORE",
    "ribbon.OkToRetryOnAllOperations=false" })
@RunWith(SpringJUnit4ClassRunner.class)
public class FeignPageMappingTests {

  @EnableFeignClients(clients = ArtistFeignClient.class)
  @RibbonClient(name = "local", configuration = LocalRibbonClientConfiguration.class)
  @SpringBootApplication(scanBasePackages = "helo.mali.fegnpagemappingtests")
  @Import({FeignClientsConfiguration.class })
  public static class Application {

  }

  @Configuration
  static class LocalRibbonClientConfiguration {

    @Value("${local.server.port}")
    private int port = 0;

    @Bean
    public ILoadBalancer ribbonLoadBalancer() {
      BaseLoadBalancer balancer = new BaseLoadBalancer();
      balancer.setServersList(
          Collections.singletonList(new Server("localhost", this.port)));
      return balancer;
    }
  }
}
