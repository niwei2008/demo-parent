package com.mixin.demo.ssm;

import java.util.logging.Level;
import java.util.logging.Logger;

import brave.sampler.Sampler;
import com.mixin.demo.ssm.cache.RedisSample;
import com.mixin.demo.ssm.mybatis.UsersMapper;
import com.mixin.demo.ssm.mybatis.entity.table1;
import com.mixin.demo.ssm.queue.MyDefaultSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class SsmApplication {

    public static void main(String[] args) {

		SpringApplication.run(SsmApplication.class, args);

	}

	@Value("${server.port}")
	String port;

	@Autowired
	MyDefaultSender sender;

	@Autowired
	RedisSample redisSample;

	@RequestMapping("/hi")
	public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
//		sender.send();
//		redisSample.set();
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		UsersMapper dao = ac.getBean(UsersMapper.class);
		table1 u1 = dao.find(1);

		String result = "hi " + name + " ,i am from port:" + port;
		result = ("uu1:"+		u1);
		return result;
	}


	//zipkin start
	private static final Logger LOG = Logger.getLogger(SsmApplication.class.getName());

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@RequestMapping("/hi2")
	public String callHome(){
		LOG.log(Level.INFO, "calling trace service-ssm  ");
		return restTemplate.getForObject("http://localhost:8989/miya", String.class);
	}
	@RequestMapping("/info")
	public String info(){
		LOG.log(Level.INFO, "calling trace service-ssm ");

		return "i'm service-ssm";

	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}


}
