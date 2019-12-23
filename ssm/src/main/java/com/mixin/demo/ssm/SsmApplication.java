package com.mixin.demo.ssm;

import java.util.logging.Level;
import java.util.logging.Logger;

import brave.sampler.Sampler;
import com.mixin.demo.ssm.cache.RedisSample;
import com.mixin.demo.ssm.dao.UserDao;
import com.mixin.demo.ssm.dao.generated.UserDomainMapper;
import com.mixin.demo.ssm.entity.UserDomain;
import com.mixin.demo.ssm.queue.MyDefaultSender;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@EnableEurekaClient
@SpringBootApplication
@RestController
@MapperScan({"com.mixin.demo.ssm.dao.generated", "com.mixin.demo.ssm.dao"})
@EnableTransactionManagement
public class SsmApplication   implements CommandLineRunner {

    public static void main(String[] args) {
    	SpringApplication.run(SsmApplication.class, args);
	}

	@Value("${server.port}")
	String port;

	@Autowired
	MyDefaultSender sender;

	@Autowired
	RedisSample redisSample;

	@Autowired
	private UserDao userDao;

	@Autowired
	UserDomainMapper userDomainMapper;

	@GetMapping("select")
    public ResponseEntity select(int uid){
		//return ResponseEntity.ok(userDao.selectById(uid));

		UserDomain oneUser = userDomainMapper.selectByPrimaryKey(uid);
		return ResponseEntity.ok(oneUser.getUserName());
}

	@RequestMapping("/hi")
	public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
		sender.send();
		redisSample.set();

		String result = "hi " + name + " ,i am from port:" + port;
		UserDomain oneUser = userDao.find(1001);
		result = ("uu1:"+		oneUser.getUserName());

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

	@Autowired
	DataSource dataSource;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("DATASOURCE = " + dataSource);
	}

}
