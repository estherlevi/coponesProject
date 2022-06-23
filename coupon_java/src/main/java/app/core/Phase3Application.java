package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import app.core.services.AdminService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class Phase3Application {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(Phase3Application.class, args);
		AdminService adminService = ctx.getBean(AdminService.class);
		CompanyService companyService = ctx.getBean(CompanyService.class);
		CustomerService customerService = ctx.getBean(CustomerService.class);

		try {

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
